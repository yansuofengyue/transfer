package com.yicloud.trans.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yicloud.trans.core.YiUtil;
import com.yicloud.trans.model.mssql.Jbxxk;
import com.yicloud.trans.model.mssql.zd.Gfjb2Ybbr;
import com.yicloud.trans.model.mysql.Nature;
import com.yicloud.trans.model.mysql.Patients;
import com.yicloud.trans.service.mssql.Gfjb2YbbrService;
import com.yicloud.trans.service.mssql.JbxxkService;
import com.yicloud.trans.service.mysql.FeeTypeService;
import com.yicloud.trans.service.mysql.NatureService;
import com.yicloud.trans.service.mysql.PatientsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
        if (!Optional.ofNullable(gfjb2YbbrList).isPresent()){
            Gfjb2Ybbr gfjb2Ybbr = new Gfjb2Ybbr();
            gfjb2Ybbr.setLbh("00");
            gfjb2Ybbr.setYblbh("00");
            gfjb2Ybbr.setYblbm("自费");
        }
        //1、老系统自费病人和身份证号为空的病人 身份证号处理 身份证格式： 3301042+门诊号，自费病人信息导入老系统
        List<Jbxxk> list1 = jbxxkService.list();
        for (Jbxxk jbxxk:list1){
            if (!Optional.ofNullable(jbxxk.getSfzh()).isPresent()) {
                jbxxk.setSfzh("");
                jbxxkService.updateById(jbxxk);
            }
        }
        List<Jbxxk> list = jbxxkService.list(new QueryWrapper<Jbxxk>().lambda().eq(Jbxxk::getFylb, "00").or().eq(Jbxxk::getSfzh, ""));
        for (Jbxxk jbxxk : list) {
            jbxxk.setSfzh("3301042" + jbxxk.getZyh());
            jbxxkService.updateById(jbxxk);
            patientsService.save(jbxxkTopatients(jbxxk));
        }
        //2、老系统患者信息插入新系统中，在新系统中已经存在的患者 使用新系统中门诊号 如果不在则插入，老系统中同一个病人有多个门诊号 插入新系统中区最新的一个
        list = jbxxkService.list(new QueryWrapper<Jbxxk>().lambda().ne(Jbxxk::getFylb, "00").ne(Jbxxk::getSfzh, "").orderByDesc(Jbxxk::getMzrq));
        for (Jbxxk jbxxk : list) {
            try {
                Patients patients = patientsService.getOne(new QueryWrapper<Patients>().lambda().eq(Patients::getPatIdentityNum, jbxxk.getSfzh()));
                if (!Optional.ofNullable(patients).isPresent()) {
                    patientsService.save(jbxxkTopatients(jbxxk));
                }
            } catch (Exception e) {
                return jbxxk.getZyh().toString();
            }
        }
        return "papapapap@qq.com";
    }

    private Patients jbxxkTopatients(Jbxxk jbxxk) throws Exception {
        Patients patients = new Patients();
        try {
            patients.setPatCardNum(jbxxk.getZyh().toString());
            patients.setPatName(jbxxk.getXm());
            String iSex = "9";
            if (Optional.ofNullable(jbxxk.getXb()).isPresent()) {
                iSex = jbxxk.getXb().equals("男") ? "1" : "2";
            }

            patients.setPatSex(iSex);
            patients.setPatBirthday(YiUtil.dateToLocalDate(jbxxk.getCsny()));
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
