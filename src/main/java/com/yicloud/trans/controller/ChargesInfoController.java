package com.yicloud.trans.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.corba.se.impl.encoding.CodeSetConversion;
import com.yicloud.trans.model.mssql.Brjbk;
import com.yicloud.trans.model.mssql.Jbxxk;
import com.yicloud.trans.model.mssql.MzypmxkF;
import com.yicloud.trans.model.mssql.Yjmxk;
import com.yicloud.trans.model.mssql.zd.Gfjb2Ybbr;
import com.yicloud.trans.model.mysql.*;
import com.yicloud.trans.service.mssql.BrjbkService;
import com.yicloud.trans.service.mssql.Gfjb2YbbrService;
import com.yicloud.trans.service.mssql.YjmxkService;
import com.yicloud.trans.service.mysql.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/29 10:38
 * @FileName: ChargesInfoController
 * @Description: dd
 */

@RestController
@Transactional(rollbackFor = Exception.class)
@RequestMapping(value = "/chargesInfo")
public class ChargesInfoController {
    @Autowired
    private ChargesInfoService chargesInfoService;
    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;
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

    @PostMapping("/cleaning")
    @ResponseBody
    public String cleaning(@RequestParam Date startDate, @RequestParam Date endDate) throws Exception {

        String msg="";
        String strStartDate = DateUtil.format(startDate, "yyyy-MM-dd");
        String strEndDate = DateUtil.format(endDate, "yyyy-MM-dd");
        List<Brjbk> brjbkList = new ArrayList<>(2048);
        String redisKey = "brjbk_" + strStartDate + strEndDate;
        if (redisCacheTemplate.hasKey(redisKey)) {
            Set<Serializable> BrjbkList = redisCacheTemplate.opsForSet().members(redisKey);
            for (Serializable d : BrjbkList) {
                brjbkList.add((Brjbk) d);
            }
        } else {
            brjbkList = brjbkService.list(new QueryWrapper<Brjbk>().lambda().ge(Brjbk::getSfrq, strStartDate).le(Brjbk::getSfrq, strEndDate));
            for (Brjbk brjbk : brjbkList) {
                redisCacheTemplate.opsForSet().add(redisKey, brjbk);
            }
        }
        for (Brjbk brjbk : brjbkList){
            ChargesInfo chargesInfo = new ChargesInfo();
            MedChargesInfo medChargesInfo = new MedChargesInfo();
            List<ExamineDetail> examineDetailList = new ArrayList<>(24);
            ExamineInfo examineInfo = new ExamineInfo();
            try {
                if (!redisCacheTemplate.opsForHash().hasKey("jbxxk", brjbk.getZyh().toString())){
                    continue;
                }
                if (brjbk.getSfbhId().equals(3091882L)){
                    System.out.println(brjbk.toString());
                }
                chargesInfo.setId(brjbk.getSfbhId());
                chargesInfo.setBillDate(brjbk.getSfrq());
                Jbxxk jbxxk = (Jbxxk) redisCacheTemplate.opsForHash().get("jbxxk", brjbk.getZyh().toString());
                if (!Optional.ofNullable(jbxxk).isPresent()) {
                    msg = "人员信息不存在";
                }
                Patients patients = (Patients) redisCacheTemplate.opsForHash().get("patients", jbxxk.getSfzh());

                chargesInfo.setPatId(patients.getId());
                chargesInfo.setBillTotalFee(brjbk.getFyhj());
                chargesInfo.setBillCashFee(brjbk.getBzylzfje());
                chargesInfo.setBillSelfFee(brjbk.getBzylzfje());
                chargesInfo.setBillFavorFee(brjbk.getYhje());
                chargesInfo.setSffId(9999L);
                chargesInfo.setDayId(0L);
                chargesInfo.setDayDate(DateUtil.date());
                chargesInfo.setHospitalId(330005L);
                chargesInfo.setBillType("1");
                chargesInfo.setPatName(patients.getPatName());
                chargesInfo.setPatCardNum(patients.getPatCardNum());
                if (Optional.ofNullable(brjbk.getTfbz()).isPresent()){
                    if (brjbk.getTfbz()>0){
                        chargesInfo.setBillOldId(Long.valueOf(brjbk.getTfbz()));
                    }
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
                }
                Gfjb2Ybbr gfjb2Ybbr = gfjb2YbbrService.list(new LambdaQueryWrapper<Gfjb2Ybbr>().eq(Gfjb2Ybbr::getLbh, jbxxk.getFylb())).get(0);
                String natCode = Optional.ofNullable(gfjb2Ybbr).isPresent() ? gfjb2Ybbr.getYblbh() : "00";
                Nature nature = natureService.getOne(new QueryWrapper<Nature>().lambda().eq(Nature::getFeeId, feeId).eq(Nature::getNatCode, natCode));
                FeeType feeType = feeTypeService.getById(feeId);
                chargesInfo.setNatureId(nature.getId());
                chargesInfo.setNatureName(nature.getNatName());
                chargesInfo.setFeeId(feeType.getId());
                chargesInfo.setFeeName(feeType.getFeeName());
                //项目明细
                List<Yjmxk> yjmxkList = yjmxkService.list(new QueryWrapper<Yjmxk>().lambda().eq(Yjmxk::getSfbhId,brjbk.getSfbhId()));
                for (Yjmxk yjmxk:yjmxkList){
                    ExamineDetail examineDetail = new ExamineDetail();
                    examineDetail.setId(yjmxk.getId());
                    examineDetail.setExamineId(brjbk.getSfbhId());
                    examineDetail.setItemId(yjmxk.getSfxmId());
                    examineDetail.setExdPrice(yjmxk.getDj());
                    examineDetail.setExdQuantity(yjmxk.getSl());
                    examineDetail.setSffId(9999L);
                    examineDetail.setDepId(5L);
                    examineDetailList.add(examineDetail);
                }

                //项目费用
                if (!CollectionUtils.isEmpty(yjmxkList)){
                    examineInfo.setId(brjbk.getSfbhId());
                    examineInfo.setPatId(patients.getId());
                    examineInfo.setPatCardNum(patients.getPatCardNum());
                    examineInfo.setPatName(patients.getPatName());
                    examineInfo.setNatId(chargesInfo.getNatureId());
                    examineInfo.setFeeId(chargesInfo.getFeeId());
                    examineInfo.setChargesId(chargesInfo.getId());
                    examineInfo.setVisId(0L);
                    if (Optional.ofNullable(brjbk.getTfbz()).isPresent()){
                        if (brjbk.getTfbz().equals("1")){
                            examineInfo.setExaState(1);
                        }else {
                            examineInfo.setExaState(0);
                        }
                        if (brjbk.getTfbz()>1){
                            examineInfo.setExamineId(Long.valueOf(brjbk.getTfbz()));
                        }
                    }else {
                        examineInfo.setExaState(0);
                        examineInfo.setExamineId(null);
                    }
                    examineInfo.setExaDate(brjbk.getSfrq());
                    examineInfo.setSffId(9999L);
                    examineInfo.setExaFee(brjbk.getFyhj());
                    examineInfo.setHospitalId(330005L);
                }
                //医保结算
                if (!feeId.equals(1L)){
                    medChargesInfo.setId(brjbk.getSfbhId());
                    medChargesInfo.setPatId(patients.getId());
                    medChargesInfo.setFeeId(chargesInfo.getFeeId());
                    medChargesInfo.setHospitalId(330005L);
                    medChargesInfo.setPersonalNo(jbxxk.getBxh());
                    medChargesInfo.setMedSerialNo(brjbk.getJylsh());
                    medChargesInfo.setSettlementNo(brjbk.getJylsh2());
                    medChargesInfo.setBusinessCycleNumber(brjbk.getYwzqh());
                    medChargesInfo.setTradeTime(brjbk.getSfrq());
                    medChargesInfo.setTradeType("2");
                    if (!Optional.ofNullable(brjbk.getTfbz()).isPresent()){
                        medChargesInfo.setTradeStatus("1");
                    }else {
                        if (brjbk.getTfbz()>1){
                            medChargesInfo.setTradeStatus("-1");
                        }else {
                            medChargesInfo.setTradeStatus(brjbk.getTfbz().toString());
                        }
                    }
                    medChargesInfo.setTreatmentCategory(nature.getNatCode());
                    medChargesInfo.setTotal(brjbk.getFyhj());
                    medChargesInfo.setCurrentYearBalance(brjbk.getBnzhye());
                    medChargesInfo.setCurrentYearPay(brjbk.getBnzhzcje());
                    medChargesInfo.setCalendarYearBalance(brjbk.getLnzhye());
                    medChargesInfo.setCalendarYearPay(brjbk.getLnzhzfZl());
                    medChargesInfo.setCashPay(brjbk.getBzylzfje());
                    medChargesInfo.setSelfCarePay(brjbk.getTjzl());
                    medChargesInfo.setSelfPay(brjbk.getTzzl());
                    if (Optional.ofNullable(brjbk.getGdbzbz()).isPresent()){
                        medChargesInfo.setPrescribedDisease(brjbk.getGdbzbz());
                    }else {
                        medChargesInfo.setPrescribedDisease("0");
                    }
                    medChargesInfo.setConfirmMark("1");
                }

                chargesInfoService.saveOrUpdate(chargesInfo);
                if (Optional.ofNullable(medChargesInfo).isPresent()){
                    medChargesInfoService.saveOrUpdate(medChargesInfo);
                }
                if (!CollectionUtils.isEmpty(examineDetailList)){
                    examineInfoService.saveOrUpdate(examineInfo);
                    examineDetailService.saveOrUpdateBatch(examineDetailList);
                }

            }catch (Exception exception){
                throw new Exception(brjbk.toString());
            }

        }
        return "ddd";
    }
}
