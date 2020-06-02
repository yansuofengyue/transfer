package com.yicloud.trans.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yicloud.trans.model.mssql.Bazdk;
import com.yicloud.trans.model.mssql.Brjbk;
import com.yicloud.trans.model.mssql.Jbxxk;
import com.yicloud.trans.model.mssql.Yjmxk;
import com.yicloud.trans.model.mssql.zd.Gfjb2Ybbr;
import com.yicloud.trans.model.mysql.*;
import com.yicloud.trans.service.mssql.*;
import com.yicloud.trans.service.mysql.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.*;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/29 14:01
 * @FileName: BazdkController
 * @Description: ss
 */
@RestController
@RequestMapping(value = "/bazdk")
public class BazdkController {
    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;
    @Autowired
    private BazdkService bazdkService;
    @Autowired
    private BrjbkService brjbkService;
    @Autowired
    private FeeTypeService feeTypeService;
    @Autowired
    private NatureService natureService;
    @Autowired
    private Gfjb2YbbrService gfjb2YbbrService;
    @Autowired
    private MedChargesInfoService medChargesInfoService;
    @Autowired
    private YjmxkService yjmxkService;
    @Autowired
    private ExamineInfoService examineInfoService;
    @Autowired
    private ExamineDetailService examineDetailService;
    @Autowired
    private ChargesInfoService chargesInfoService;
    @Autowired
    private PubStaffInfoService pubStaffInfoService;
    @Autowired
    private PatientsService patientsService;
    @Autowired
    private JbxxkService jbxxkService;


    @PostMapping("/cleaning")
    @ResponseBody
    public String cleaning(@RequestParam Date startDate, @RequestParam Date endDate) throws Exception {
        String msg="";
        String strStartDate = DateUtil.format(startDate, "yyyy-MM-dd");
        String strEndDate = DateUtil.format(endDate, "yyyy-MM-dd");
        List<Bazdk> bazdkList = new ArrayList<>(2048);
        String redisKey = "bazd" + strStartDate + strEndDate;
        if (redisCacheTemplate.hasKey(redisKey)) {
            Set<Serializable> bazdks = redisCacheTemplate.opsForSet().members(redisKey);
            for (Serializable d : bazdks) {
                bazdkList.add((Bazdk) d);
            }
        } else {
            bazdkList = bazdkService.list(new QueryWrapper<Bazdk>().lambda().ge(Bazdk::getMzrq, strStartDate).le(Bazdk::getMzrq, strEndDate));
            for (Bazdk bazdk : bazdkList) {
                if (!Optional.ofNullable(bazdk.getJylsh()).isPresent()){
                    bazdk.setJylsh(bazdk.getId().toString());
                }
                if (!Optional.ofNullable(bazdk.getJylsh2()).isPresent()){
                    bazdk.setJylsh2(bazdk.getId().toString());
                }
                if (!Optional.ofNullable(bazdk.getYwzqh()).isPresent()){
                    bazdk.setYwzqh(bazdk.getId().toString());
                }
                redisCacheTemplate.opsForSet().add(redisKey, bazdk);
            }
        }
        for (Bazdk bazdk : bazdkList) {
            ChargesInfo chargesInfo = new ChargesInfo();
            MedChargesInfo medChargesInfo = new MedChargesInfo();
            List<ExamineDetail> examineDetailList = new ArrayList<>(24);
            ExamineInfo examineInfo = new ExamineInfo();
            try {
                if (bazdk.getId().equals(1911004L)){
                    System.out.println(bazdk.toString());
                }
                chargesInfo.setId(bazdk.getId());
                chargesInfo.setBillDate(bazdk.getMzrq());
                Jbxxk jbxxk = (Jbxxk) redisCacheTemplate.opsForHash().get("jbxxk", bazdk.getZyh().toString());
                Patients patients=null;
                if (!Optional.ofNullable(jbxxk).isPresent()) {
                    jbxxk = jbxxkService.getOne(new QueryWrapper<Jbxxk>().lambda().eq(Jbxxk::getZyh, bazdk.getZyh()));
                    patients = patientsService.createPatients(bazdk.getZyh());
                    patientsService.saveOrUpdate(patients);
                }else {
                    patients = (Patients) redisCacheTemplate.opsForHash().get("patients", jbxxk.getSfzh());
                }
                if (!Optional.ofNullable(patients).isPresent()) {
                    msg="患者信息不存在！";
                }

                Long feeId = 1L;
                if (jbxxk.getFylb().startsWith("H")) {
                    feeId = 2L;
                } else if (jbxxk.getFylb().startsWith("S")) {
                    feeId = 3L;
                } else if (jbxxk.getFylb().startsWith("T")) {
                    feeId = 4L;
                } else if (jbxxk.getFylb().startsWith("0")) {
                    feeId = 1L;
                }else {
                    feeId = 1L;
                }
                Gfjb2Ybbr gfjb2Ybbr = gfjb2YbbrService.list(new LambdaQueryWrapper<Gfjb2Ybbr>().eq(Gfjb2Ybbr::getLbh, jbxxk.getFylb())).get(0);
                String natCode = Optional.ofNullable(gfjb2Ybbr).isPresent() ? gfjb2Ybbr.getYblbh() : "00";
                Nature nature = natureService.getOne(new QueryWrapper<Nature>().lambda().eq(Nature::getFeeId, feeId).eq(Nature::getNatCode, natCode));
                FeeType feeType = feeTypeService.getById(feeId);

                chargesInfo.setPatId(patients.getId());
                chargesInfo.setPatCardNum(patients.getPatCardNum());
                chargesInfo.setPatName(patients.getPatName());
                chargesInfo.setFeeId(feeType.getId());
                chargesInfo.setNatureId(nature.getId());
                chargesInfo.setFeeName(feeType.getFeeName());
                chargesInfo.setNatureName(nature.getNatName());
                chargesInfo.setBillTotalFee(bazdk.getJe());
                chargesInfo.setBillSelfFee(bazdk.getGrxjzfje());
                chargesInfo.setBillCashFee(bazdk.getGrxjzfje());
                chargesInfo.setSffId(9999L);
                chargesInfo.setDayDate(DateUtil.date());
                chargesInfo.setDayId(0L);
                if (Optional.ofNullable(bazdk.getGhbz()).isPresent()){
                    if (bazdk.getGhbz().equals(0)){
                        chargesInfo.setBillRefundSign("1");
                    }else if (Optional.ofNullable(bazdk.getThid()).isPresent()){
                        if (bazdk.getThid()>0){
                            Bazdk bazdk1 = bazdkService.getOne(new QueryWrapper<Bazdk>().lambda().eq(Bazdk::getId,bazdk.getThid()));
                            chargesInfo.setBillRefundSign("1");
                            chargesInfo.setBillOldId(bazdk1.getId());
                        }else {
                            chargesInfo.setBillRefundSign("0");
                        }
                    }else {
                        chargesInfo.setBillRefundSign("0");
                    }
                }

                chargesInfo.setBillType("0");
                chargesInfo.setHospitalId(330005L);
                chargesInfo.setCenterSerialNo(bazdk.getJylsh());
                chargesInfo.setBusinessCycleNumber(bazdk.getYwzqh());
                PubStaffInfo pubStaffInfo = pubStaffInfoService.getOne(new QueryWrapper<PubStaffInfo>().lambda().eq(PubStaffInfo::getSffLoginNum, bazdk.getYsh()));
                if (Optional.ofNullable(pubStaffInfo).isPresent()) {
                    chargesInfo.setSffId(pubStaffInfo.getId());
                } else {
                    msg="医生工号为：" + bazdk.getYsh() + "信息未维护在新系统.";
                }

                //项目费用

                    examineInfo.setId(bazdk.getId());
                    examineInfo.setPatId(patients.getId());
                    examineInfo.setPatCardNum(patients.getPatCardNum());
                    examineInfo.setPatName(patients.getPatName());
                    examineInfo.setNatId(chargesInfo.getNatureId());
                    examineInfo.setFeeId(chargesInfo.getFeeId());
                    examineInfo.setChargesId(chargesInfo.getId());
                    examineInfo.setVisId(0L);
                    if (Optional.ofNullable(bazdk.getGhbz()).isPresent()){
                        if (bazdk.getGhbz().equals("0")){
                            examineInfo.setExaState(1);
                        }else  if (Optional.ofNullable(bazdk.getThid()).isPresent()){
                            if (bazdk.getThid()>0){
                                Bazdk bazdk1 = bazdkService.getOne(new QueryWrapper<Bazdk>().lambda().eq(Bazdk::getId,bazdk.getThid()));
                                examineInfo.setExamineId(bazdk1.getId());
                            }
                            examineInfo.setExaState(0);
                        }
                    }else {
                        examineInfo.setExaState(0);
                        examineInfo.setExamineId(null);
                    }
                    examineInfo.setExaDate(bazdk.getMzrq());
                    examineInfo.setSffId(9999L);
                    examineInfo.setHospitalId(330005L);
                    examineInfo.setExaFee(bazdk.getJe());
                    examineInfo.setExaSerialNum(bazdk.getId().toString());

                //医保结算
                if (!feeId.equals(1L)){
                    medChargesInfo.setId(bazdk.getId());
                    medChargesInfo.setPatId(patients.getId());
                    medChargesInfo.setFeeId(chargesInfo.getFeeId());
                    medChargesInfo.setHospitalId(330005L);
                    medChargesInfo.setPersonalNo(jbxxk.getBxh());
                    medChargesInfo.setMedSerialNo(bazdk.getJylsh());
                    medChargesInfo.setSettlementNo(bazdk.getJylsh2());
                    medChargesInfo.setBusinessCycleNumber(bazdk.getYwzqh());
                    medChargesInfo.setTradeTime(bazdk.getMzrq());
                    medChargesInfo.setTradeType("1");
                    if (!Optional.ofNullable(bazdk.getGhbz()).isPresent()){
                        medChargesInfo.setTradeStatus("1");
                    }else {
                        if (bazdk.getGhbz().equals(0)){
                            medChargesInfo.setTradeStatus("-1");
                        }else {
                            medChargesInfo.setTradeStatus("1");
                        }
                    }
                    medChargesInfo.setTreatmentCategory(nature.getNatCode());
                    medChargesInfo.setTotal(chargesInfo.getBillTotalFee());
                    medChargesInfo.setCurrentYearBalance(bazdk.getBnzhye());
                    medChargesInfo.setCurrentYearPay(bazdk.getBnzhzcje());
                    medChargesInfo.setCalendarYearBalance(bazdk.getLnzhye());
                    medChargesInfo.setCalendarYearPay(bazdk.getLnzhzcje());
                    medChargesInfo.setCashPay(bazdk.getGrxjzfje());
                    medChargesInfo.setSelfPay(bazdk.getGrzfje());
                    if (Optional.ofNullable(bazdk.getGdbzbz()).isPresent()){
                        medChargesInfo.setPrescribedDisease(bazdk.getGdbzbz());
                    }else {
                        medChargesInfo.setPrescribedDisease("0");
                    }
                    medChargesInfo.setConfirmMark("1");
                }

                chargesInfoService.saveOrUpdate(chargesInfo);
                if (Optional.ofNullable(medChargesInfo.getId()).isPresent()){
                    medChargesInfoService.saveOrUpdate(medChargesInfo);
                }
                examineInfoService.saveOrUpdate(examineInfo);

            }catch (Exception e){
                throw  new Exception(bazdk.toString());
            }

        }
        return "yiyiyi";
    }
}
