package com.yicloud.trans.service.mysql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.core.YiUtil;
import com.yicloud.trans.mapper.mssql.JbxxkMapper;
import com.yicloud.trans.mapper.mssql.zd.Gfjb2YbbrMapper;
import com.yicloud.trans.mapper.mysql.FeeTypeMapper;
import com.yicloud.trans.mapper.mysql.NatureMapper;
import com.yicloud.trans.mapper.mysql.PatientsMapper;
import com.yicloud.trans.model.mssql.Jbxxk;
import com.yicloud.trans.model.mssql.zd.Gfjb2Ybbr;
import com.yicloud.trans.model.mysql.Nature;
import com.yicloud.trans.model.mysql.Patients;
import com.yicloud.trans.service.mssql.JbxxkService;
import com.yicloud.trans.service.mysql.PatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/4/19 10:00
 * @FileName: PatientsServiceImpl
 * @Description: \
 */
@Service
@DS("master")
public class PatientsServiceImpl extends ServiceImpl<PatientsMapper, Patients> implements PatientsService {
    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;
    @Autowired
    private PatientsMapper patientsMapper;
    @Autowired
    private JbxxkMapper jbxxkMapper;
    @Autowired
    private Gfjb2YbbrMapper gfjb2YbbrMapper;
    @Autowired
    private FeeTypeMapper feeTypeMapper;
    @Autowired
    private NatureMapper natureMapper;

    @Override
    public Patients createPatients(Long zyh) throws Exception {
        Patients patients = null;
        try {
            Jbxxk jbxxk = jbxxkMapper.selectOne(new QueryWrapper<Jbxxk>().lambda().eq(Jbxxk::getZyh, zyh));
            if (!Optional.ofNullable(jbxxk.getSfzh()).isPresent()) {
                jbxxk.setSfzh("3301042" + jbxxk.getZyh());
                jbxxkMapper.updateById(jbxxk);
            }
            if (!Optional.ofNullable(jbxxk.getMzrq()).isPresent()) {
                jbxxk.setMzrq(YiUtil.localDateToDate(LocalDate.now().plusYears(-10)));
                jbxxkMapper.updateById(jbxxk);
            }
            redisCacheTemplate.opsForSet().add("jbxxkSet", jbxxk);
            if (!redisCacheTemplate.opsForHash().hasKey("patients", jbxxk.getSfzh())) {
                List<Patients> patientsList = patientsMapper.selectList(new QueryWrapper<Patients>().lambda().eq(Patients::getPatIdentityNum, jbxxk.getSfzh()).orderByDesc(Patients::getTimeStamp));
                if (!CollectionUtils.isEmpty(patientsList)) {
                    patients = patientsList.get(0);
                } else {
                    patients = jbxxkTopatients(jbxxk);

                    patientsMapper.insert(patients);
                }
                redisCacheTemplate.opsForHash().put("patients", patients.getPatIdentityNum(), patients);
            }else {
                patients = (Patients) redisCacheTemplate.opsForHash().get("patients", jbxxk.getSfzh());
            }
            if (!redisCacheTemplate.opsForHash().hasKey("jbxxk", jbxxk.getZyh().toString())) {
                redisCacheTemplate.opsForHash().put("jbxxk", jbxxk.getZyh().toString(), jbxxk);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage() + patients.toString());
        }
        return patients;
    }

    private Patients jbxxkTopatients(Jbxxk jbxxk) throws Exception {
        Patients patients = new Patients();
        try {
            patients.setId(jbxxk.getZyh());
            patients.setPatCardNum(String.format("%08d", jbxxk.getZyh()));
            patients.setPatName(jbxxk.getXm());
            String iSex = "9";
            if (Optional.ofNullable(jbxxk.getXb()).isPresent()) {
                iSex = jbxxk.getXb().equals("男") ? "1" : "2";
            }

            patients.setPatSex(iSex);
            if (Optional.ofNullable(jbxxk.getCsny()).isPresent()) {
                patients.setPatBirthday(YiUtil.dateToLocalDate(jbxxk.getCsny()));
            } else {
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
            }else {
                feeId = 1L;
                jbxxk.setFylb("00");
            }
            Gfjb2Ybbr gfjb2Ybbr = gfjb2YbbrMapper.selectList(new LambdaQueryWrapper<Gfjb2Ybbr>().eq(Gfjb2Ybbr::getLbh, jbxxk.getFylb())).get(0);
            String natCode = Optional.ofNullable(gfjb2Ybbr).isPresent() ? gfjb2Ybbr.getYblbh() : "00";
            if (feeId.equals(2L) && natCode.equals("99")) {
                natCode = "72";
            }
            Nature nature = natureMapper.selectOne(new QueryWrapper<Nature>().lambda().eq(Nature::getFeeId, feeId).eq(Nature::getNatCode, natCode));
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
            patients.setFeeName(feeTypeMapper.selectById(feeId).getFeeName());
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
        } catch (Exception e) {
            throw new Exception(jbxxk.getZyh().toString());
        }
        return patients;
    }
}
