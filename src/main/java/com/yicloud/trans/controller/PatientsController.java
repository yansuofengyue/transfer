package com.yicloud.trans.controller;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yicloud.trans.core.YiUtil;
import com.yicloud.trans.model.mssql.Jbxxk;
import com.yicloud.trans.model.mssql.zd.Gfjb2Ybbr;
import com.yicloud.trans.model.mysql.DrugRegionAlias;
import com.yicloud.trans.model.mysql.Nature;
import com.yicloud.trans.model.mysql.Patients;
import com.yicloud.trans.service.mssql.Gfjb2YbbrService;
import com.yicloud.trans.service.mssql.JbxxkService;
import com.yicloud.trans.service.mysql.FeeTypeService;
import com.yicloud.trans.service.mysql.NatureService;
import com.yicloud.trans.service.mysql.PatientsService;
import jdk.nashorn.internal.ir.ForNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/4/19 10:13
 * @FileName: PatientsController
 * @Description: 人员信息处理接口层
 */
@RestController
@RequestMapping(value = "/patients")
public class PatientsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientsController.class);

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;
    @Autowired
    private JbxxkService jbxxkService;
    @Autowired
    private FeeTypeService feeTypeService;
    @Autowired
    private NatureService natureService;
    @Autowired
    private Gfjb2YbbrService gfjb2YbbrService;
    @Autowired
    private PatientsService patientsService;


    @RequestMapping("/cleaning")
    public String cleaning() throws Exception {
        List<Gfjb2Ybbr> gfjb2YbbrList = gfjb2YbbrService.list(new LambdaQueryWrapper<Gfjb2Ybbr>().eq(Gfjb2Ybbr::getLbh, "00"));
        if (CollectionUtils.isEmpty(gfjb2YbbrList)){
            Gfjb2Ybbr gfjb2Ybbr = new Gfjb2Ybbr();
            gfjb2Ybbr.setLbh("00");
            gfjb2Ybbr.setYblbh("00");
            gfjb2Ybbr.setYblbm("自费");
            gfjb2YbbrService.save(gfjb2Ybbr);
        }
        //1、老系统自费病人和身份证号为空的病人 身份证号处理 身份证格式： 3301042+门诊号，自费病人信息导入老系统
        List<Jbxxk> list = new ArrayList<>(1024000);
        if ( redisCacheTemplate.hasKey("jbxxkSet")){
            Set<Serializable> jbxxkList = redisCacheTemplate.opsForSet().members("jbxxkSet");
            for (Serializable d :jbxxkList){
                list.add((Jbxxk) d);
            }
        }else {
            for (Integer num=1;num<30;num++){
                List<Jbxxk> listT = jbxxkService.list(new QueryWrapper<Jbxxk>().lambda().ge(Jbxxk::getMzrq,
                        DateUtil.format(DateUtil.offset(DateUtil.date(),DateField.DAY_OF_YEAR,num - 30),
                                "yyyy-MM-dd")).lt(Jbxxk::getMzrq,
                        DateUtil.format(DateUtil.offset(DateUtil.date(),DateField.DAY_OF_YEAR,num + 1 - 30),"yyyy" +
                                "-MM" +
                                "-dd")));
               if (!CollectionUtils.isEmpty(listT)){
                   for (Jbxxk jbxxk:listT){
                       if (!Optional.ofNullable(jbxxk.getSfzh()).isPresent()) {
                           jbxxk.setSfzh("3301042" + jbxxk.getZyh());
                           jbxxkService.updateById(jbxxk);
                       }
                   }
                   for (Jbxxk jbxxk:listT){
                       if (!Optional.ofNullable(jbxxk.getMzrq()).isPresent()) {
                           jbxxk.setMzrq(YiUtil.localDateToDate(LocalDate.now().plusYears(-10)));
                           jbxxkService.updateById(jbxxk);
                       }
                   }
                   for (Jbxxk jbxxk:listT){
                       redisCacheTemplate.opsForSet().add("jbxxkSet",jbxxk);
                   }
                   list.addAll(listT);
               }
            }
        }

        //2、老系统患者信息插入新系统中，在新系统中已经存在的患者 使用新系统中门诊号 如果不在则插入，老系统中同一个病人有多个门诊号 插入新系统中区最新的一个
        list = list.stream().sorted(Comparator.comparing(Jbxxk::getMzrq).reversed()).collect(Collectors.toList());
        for (Jbxxk jbxxk : list) {
            try {
                List<Patients> patientsList = patientsService.list(new QueryWrapper<Patients>().lambda().eq(Patients::getPatIdentityNum, jbxxk.getSfzh()).orderByDesc(Patients::getTimeStamp));
                Patients patients = new Patients();
                if (!CollectionUtils.isEmpty(patientsList)){
                    patients = patientsList.get(0);
                }else {
                    patients=jbxxkTopatients(jbxxk);
                    patientsService.save(patients);
                }
                if (!redisCacheTemplate.opsForHash().hasKey("patients",patients.getPatIdentityNum())){
                    redisCacheTemplate.opsForHash().put("patients",patients.getPatIdentityNum(),patients);
                }
                if (!redisCacheTemplate.opsForHash().hasKey("jbxxk",jbxxk.getZyh().toString())){
                    redisCacheTemplate.opsForHash().put("jbxxk",jbxxk.getZyh().toString(),jbxxk);
                }
            } catch (Exception e) {
                return jbxxk.getZyh().toString()+jbxxk.getXm();
            }
        }
        return "papapapap@qq.com";
    }

    private Patients jbxxkTopatients(Jbxxk jbxxk) throws Exception {
        Patients patients = new Patients();
        try {
            patients.setId(jbxxk.getZyh());
            patients.setPatCardNum(String.format("%08d",jbxxk.getZyh()));
            patients.setPatName(jbxxk.getXm());
            String iSex = "9";
            if (Optional.ofNullable(jbxxk.getXb()).isPresent()) {
                iSex = jbxxk.getXb().equals("男") ? "1" : "2";
            }

            patients.setPatSex(iSex);
            if (Optional.ofNullable(jbxxk.getCsny()).isPresent()){
                patients.setPatBirthday(YiUtil.dateToLocalDate(jbxxk.getCsny()));
            }else {
                patients.setPatBirthday(LocalDate.now().plusYears(-50));
            }

            patients.setPatCertifiType("1");
            patients.setPaperState("3");

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
            // 读卡类型 市医保0社保卡1证历本 省医保1社保卡 省一卡通1社保卡
            String natureType = "0";
            if (jbxxk.getGfzh() == null) {
                natureType = "0";
            } else if (jbxxk.getGfzh().startsWith("1")) {
                if (feeId.equals(3L)) {
                    natureType = "1";
                } else if (feeId.equals(2L)) {
                    natureType = "0";
                } else if (feeId.equals(4L)) {
                    natureType = "1";
                }
            } else if (jbxxk.getGfzh().startsWith("0")) {
                if (feeId.equals(2L)) {
                    natureType = "1";
                }
            }
            patients.setFeeId(feeId);
            patients.setFeeName(feeTypeService.getById(feeId).getFeeName());
            patients.setNatureId(nature.getId());
            patients.setNatureName(nature.getNatName());
            patients.setNatureType(natureType);
            patients.setPatIdentityNum(jbxxk.getSfzh());
            patients.setPatCertifiType("");
            patients.setPatCertifiNum(jbxxk.getSfzh());
            patients.setPatPhone(jbxxk.getDwdh());
            patients.setPersonalNo(jbxxk.getBxh());
            patients.setMedicareCard(jbxxk.getGfzh());
            patients.setMedCardInfo(jbxxk.getShbzkh());
        }catch (Exception e){
            throw new Exception(jbxxk.getZyh().toString());
        }
        return patients;
    }
}
