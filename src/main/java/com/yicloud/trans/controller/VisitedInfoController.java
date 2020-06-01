package com.yicloud.trans.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yicloud.trans.model.mssql.Jbxxk;
import com.yicloud.trans.model.mssql.Mzyskfk;
import com.yicloud.trans.model.mssql.zd.Jbk;
import com.yicloud.trans.model.mysql.*;
import com.yicloud.trans.service.mssql.JbkService;
import com.yicloud.trans.service.mssql.JbxxkService;
import com.yicloud.trans.service.mssql.MzyskfkService;
import com.yicloud.trans.service.mysql.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/28 11:14
 * @FileName: VisitedInfoController
 * @Description: 就诊记录controller
 */
@RestController
@RequestMapping(value = "/visitedInfo")
public class VisitedInfoController {
    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;
    @Autowired
    private VisitedInfoService visitedInfoService;
    @Autowired
    private MzyskfkService mzyskfkService;
    @Autowired
    private PubStaffInfoService pubStaffInfoService;
    @Autowired
    private JbkService jbkService;
    @Autowired
    private PatientsService patientsService;
    @Autowired
    private CliDiagnoseInfoService cliDiagnoseInfoService;

    @PostMapping("/cleaning")
    @ResponseBody
    public String cleaning(@RequestParam Date startDate, @RequestParam Date endDate) throws Exception {
        VisitedInfo visitedInfo = new VisitedInfo();
        String strStartDate = DateUtil.format(startDate, "yyyy-MM-dd");
        String strEndDate = DateUtil.format(endDate, "yyyy-MM-dd");
        List<Mzyskfk> mzyskfkList = new ArrayList<>(2048);
        String redisKey ="Mzyskfk_" + strStartDate + strEndDate;
        if (redisCacheTemplate.hasKey(redisKey)) {
            Set<Serializable> MzyskfkList = redisCacheTemplate.opsForSet().members(redisKey);
            for (Serializable d : MzyskfkList) {
                mzyskfkList.add((Mzyskfk) d);
            }
        } else {
            mzyskfkList = mzyskfkService.list(new QueryWrapper<Mzyskfk>().lambda().ge(Mzyskfk::getMzrq, startDate).le(Mzyskfk::getMzrq, endDate));
            for (Mzyskfk mzyskfk : mzyskfkList) {
                redisCacheTemplate.opsForSet().add(redisKey, mzyskfk);
            }
        }
        String msg=null;
        for (Mzyskfk mzyskfk : mzyskfkList) {
            try {
                msg="";
                visitedInfo.setId(mzyskfk.getId());
                if (mzyskfk.getId().equals(799372L)) {
                    System.out.println(mzyskfk);
                }

                PubStaffInfo pubStaffInfo = pubStaffInfoService.getOne(new QueryWrapper<PubStaffInfo>().lambda().eq(PubStaffInfo::getSffLoginNum, mzyskfk.getYsh()));
                if (Optional.ofNullable(pubStaffInfo).isPresent()) {
                    visitedInfo.setSffId(pubStaffInfo.getId());
                } else {
                    msg="医生工号为：" + mzyskfk.getYsh() + "信息未维护在新系统.";
                }

                if (Optional.ofNullable(mzyskfk.getZfysh()).isPresent() && !mzyskfk.getZfysh().trim().isEmpty() && !"3009".equals(mzyskfk.getZfysh())) {
                    PubStaffInfo pubStaffInfo1 = pubStaffInfoService.getOne(new QueryWrapper<PubStaffInfo>().lambda().eq(PubStaffInfo::getSffLoginNum, mzyskfk.getZfysh()));
                    if (Optional.ofNullable(pubStaffInfo1).isPresent()) {
                        visitedInfo.setTurnSffId(pubStaffInfo1.getId());
                    } else {
                        msg="转方医生工号为：" + mzyskfk.getZfysh() + "信息未维护在新系统";
                    }
                }

                //诊断
                Jbk jbk=null;
                if (Optional.ofNullable(mzyskfk.getBazdh1()).isPresent()){
                     jbk = jbkService.getOne(new QueryWrapper<Jbk>().lambda().eq(Jbk::getJbh,mzyskfk.getBazdh1()));
                }else if (Optional.ofNullable(mzyskfk.getZyzdh1()).isPresent()){
                     jbk = jbkService.getOne(new QueryWrapper<Jbk>().lambda().eq(Jbk::getJbh,mzyskfk.getZyzdh1()));
                }
                CliDiagnoseInfo cliDiagnoseInfo=null;
                if (Optional.ofNullable(jbk).isPresent()){
                    cliDiagnoseInfo=new CliDiagnoseInfo();
                    cliDiagnoseInfo.setDiaName(jbk.getJbm());
                    cliDiagnoseInfo.setDiaIcd(jbk.getIcd10());
                    cliDiagnoseInfo.setVisId(visitedInfo.getId());
                    cliDiagnoseInfo.setHospitalId(330005L);
                    cliDiagnoseInfo.setMainFlag("1");
                }
                visitedInfo.setVisDate(mzyskfk.getMzrq());
                Jbxxk jbxxk = (Jbxxk) redisCacheTemplate.opsForHash().get("jbxxk", mzyskfk.getZyh().toString());
                Patients patients=null;
                if (!Optional.ofNullable(jbxxk).isPresent()) {
                    patients = patientsService.createPatients(mzyskfk.getZyh());
                }else {
                    patients = (Patients) redisCacheTemplate.opsForHash().get("patients", jbxxk.getSfzh());
                }
                if (!Optional.ofNullable(patients).isPresent()) {
                    msg="患者信息不存在！";
                }

                visitedInfo.setPatCardNum(patients.getPatCardNum());
                visitedInfo.setPatId(patients.getId());
                visitedInfo.setPatName(patients.getPatName());
                visitedInfo.setFeeId(patients.getFeeId());
                visitedInfo.setNatId(patients.getNatureId());
                visitedInfo.setPatSex(Integer.valueOf(patients.getPatSex()));
                visitedInfo.setPatAddress(patients.getPatFamAddress());
                visitedInfo.setPatContacts(patients.getPatPhone());
                visitedInfo.setHospitalId(330005L);
                visitedInfo.setDepId(5L);
                visitedInfoService.saveOrUpdate(visitedInfo);
                if (Optional.ofNullable(cliDiagnoseInfo).isPresent()){
                    cliDiagnoseInfoService.saveOrUpdate(cliDiagnoseInfo);
                }
            }catch (Exception exception){
                throw new Exception(msg+mzyskfk.toString());
            }
        }
        return "papapapap@qq.com";
    }


}
