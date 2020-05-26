package com.yicloud.trans.service.mysql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.core.IDCard;
import com.yicloud.trans.mapper.mssql.JbxxkMapper;
import com.yicloud.trans.mapper.mssql.zd.Gfjb2YbbrMapper;
import com.yicloud.trans.mapper.mssql.zd.YskMapper;
import com.yicloud.trans.mapper.mysql.*;
import com.yicloud.trans.model.mssql.BazdkRegister;
import com.yicloud.trans.model.mssql.Jbxxk;
import com.yicloud.trans.model.mssql.zd.Gfjb2Ybbr;
import com.yicloud.trans.model.mssql.zd.Ysk;
import com.yicloud.trans.model.mysql.*;
import com.yicloud.trans.service.mysql.OrdAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/7 11:14
 * @FileName: OrdAppointmentServiceImpl
 * @Description: 新系统预约挂号记录表接口实现
 */
@Service
@DS("master")
public class OrdAppointmentServiceImpl extends ServiceImpl<OrdAppointmentMapper, OrdAppointment> implements OrdAppointmentService {

    @Autowired
    private OrdAppointmentMapper ordAppointmentMapper;
    @Autowired
    private JbxxkMapper jbxxkMapper;
    @Autowired
    private PatientsMapper patientsMapper;
    @Autowired
    private Gfjb2YbbrMapper gfjb2YbbrMapper;
    @Autowired
    private YskMapper yskMapper;
    @Autowired
    private OrdSchedulingMapper ordSchedulingMapper;
    @Autowired
    private NatureMapper natureMapper;
    @Autowired
    private FeeTypeMapper feeTypeMapper;
    @Autowired
    private OrdSourceDetailMapper ordSourceDetailMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    @Override
    public void transferOrdApp(BazdkRegister bazdkRegister, Ysk ysk) throws ParseException {
        Patients patients = patientsMapper.selectOne(new QueryWrapper<Patients>().lambda().eq(Patients::getPatIdentityNum, bazdkRegister.getEaSfzh()));
        if (!Optional.ofNullable(patients).isPresent()) {
            Jbxxk jbxxk = jbxxkMapper.selectOne(new QueryWrapper<Jbxxk>().lambda().eq(Jbxxk::getSfzh, bazdkRegister.getEaSfzh()).orderByDesc(Jbxxk::getMzrq));
            patients = jbxxkTopatients(bazdkRegister);
            patientsMapper.insert(patients);
            patients.setPatCardNum(String.format("%08d", patients.getId()));
            patientsMapper.updateById(patients);
        }
        OrdAppointment ordAppointment = createOrdApp(bazdkRegister, patients, ysk);
        OrdAppointment ordAppointment1 = ordAppointmentMapper.selectOne(new QueryWrapper<OrdAppointment>().lambda().eq(OrdAppointment::getSchedulingId, ordAppointment.getSchedulingId()).eq(OrdAppointment::getSourceDetailId, ordAppointment.getSourceDetailId()));
        if (!Optional.ofNullable(ordAppointment1).isPresent()) {
            ordAppointmentMapper.insert(ordAppointment);
        }
    }

    private OrdAppointment createOrdApp(BazdkRegister bazdkRegister, Patients patients, Ysk ysk) {
        OrdAppointment ordAppointment = new OrdAppointment();
        ordAppointment.setPatId(patients.getId());
        ordAppointment.setPatCardNum(patients.getPatCardNum());
        ordAppointment.setPatName(patients.getPatName());
        ordAppointment.setPatSex(patients.getPatSex());
        ordAppointment.setPhone(patients.getPatPhone());
        ordAppointment.setPatIdentityNum(patients.getPatIdentityNum());
        ordAppointment.setCancelDate(LocalDateTime.now());
        // 医生姓名 就诊日期 上下午 序号
        OrdSourceDetail sourceDetail = ordSourceDetailMapper.selectOne(new QueryWrapper<OrdSourceDetail>().lambda().
                eq(OrdSourceDetail::getDoctorName, ysk.getYsm().trim()).
                eq(OrdSourceDetail::getSchedulingDate, bazdkRegister.getEaJzrq()).
                eq(OrdSourceDetail::getTimeState, bazdkRegister.getEaXwhbz() + 1).
                eq(OrdSourceDetail::getSerialNumber, bazdkRegister.getEaJzxh()).
                eq(OrdSourceDetail::getStatus,"0"));
        OrdScheduling scheduling = ordSchedulingMapper.selectById(sourceDetail.getSchedulingId());
        ordAppointment.setHospitalId(scheduling.getHospitalId());
        ordAppointment.setSchedulingId(sourceDetail.getSchedulingId());
        ordAppointment.setSourceDetailId(sourceDetail.getId());
        ordAppointment.setSerialNumber(sourceDetail.getSerialNumber());
        ordAppointment.setTimeState(sourceDetail.getTimeState());
        ordAppointment.setDepId(sourceDetail.getDepId());
        ordAppointment.setDepName(sourceDetail.getDepName());
        ordAppointment.setDoctorId(sourceDetail.getDoctorId());
        ordAppointment.setDoctorName(sourceDetail.getDoctorName());
        ordAppointment.setBinId(sourceDetail.getBinId());
        ordAppointment.setBinName(sourceDetail.getBinName());
        ordAppointment.setAppType("0");
        ordAppointment.setVisitDate(sourceDetail.getSchedulingDate());
        ordAppointment.setVisitTime(LocalDateTime.of(sourceDetail.getSchedulingDate(), sourceDetail.getVisitTime()));
        return ordAppointment;
    }

    private Patients jbxxkTopatients(BazdkRegister bazdkRegister) throws ParseException {
        Patients patients = new Patients();
        patients.setPatName(bazdkRegister.getEaXm());
        String iSex = "9";
        if (Optional.ofNullable(bazdkRegister.getEaXb()).isPresent()) {
            iSex = bazdkRegister.getEaXb().equals("男") ? "1" : "2";
        }

        patients.setPatSex(iSex);
        if (IDCard.IDCardValidate(bazdkRegister.getEaSfzh())) {
            Map<String, String> mapP = IDCard.getBirAgeSex(bazdkRegister.getEaSfzh());
            patients.setPatBirthday(LocalDate.parse(mapP.get("birthday")));
            patients.setPatSex(mapP.get("sexCode"));
        }
        Long feeId = 1L;
        Gfjb2Ybbr gfjb2Ybbr = gfjb2YbbrMapper.selectList(new LambdaQueryWrapper<Gfjb2Ybbr>().eq(Gfjb2Ybbr::getLbh, "00")).get(0);
        String natCode = Optional.ofNullable(gfjb2Ybbr).isPresent() ? gfjb2Ybbr.getYblbh() : "00";
        Nature nature = natureMapper.selectOne(new QueryWrapper<Nature>().lambda().eq(Nature::getFeeId, feeId).eq(Nature::getNatCode, natCode));
        // 读卡类型 市医保0社保卡1证历本 省医保1社保卡 省一卡通1社保卡
        String natureType = "0";
        patients.setFeeId(feeId);
        patients.setFeeName(feeTypeMapper.selectById(feeId).getFeeName());
        patients.setNatureId(nature.getId());
        patients.setNatureName(nature.getNatName());
        patients.setNatureType(natureType);
        patients.setPatIdentityNum(bazdkRegister.getEaSfzh());
        patients.setPatCertifiType("");
        patients.setPatCertifiNum(bazdkRegister.getEaSfzh());
        patients.setPatPhone(bazdkRegister.getEaDwdh());
        return patients;
    }
}
