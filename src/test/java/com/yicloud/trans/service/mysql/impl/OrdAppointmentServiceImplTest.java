package com.yicloud.trans.service.mysql.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yicloud.trans.mapper.mssql.zd.YskMapper;
import com.yicloud.trans.model.mssql.BazdkRegister;
import com.yicloud.trans.model.mssql.zd.Ysk;
import com.yicloud.trans.model.mysql.OrdScheduling;
import com.yicloud.trans.model.mysql.Patients;
import com.yicloud.trans.service.mssql.BazdkRegisterService;
import com.yicloud.trans.service.mssql.YskService;
import com.yicloud.trans.service.mysql.OrdAppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.text.ParseException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/7 14:23
 * @FileName: OrdAppointmentServiceImplTest
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class OrdAppointmentServiceImplTest {
    @Autowired
    private OrdAppointmentService ordAppointmentService;
    @Autowired
    private BazdkRegisterService bazdkRegisterService;
    @Autowired
    private YskService yskService;

    @Test
    void transferOrdApp() throws ParseException {
        List<BazdkRegister> bazdkRegisterList = bazdkRegisterService.list(new QueryWrapper<BazdkRegister>().lambda().ge(BazdkRegister::getEaJzrq,"2020-05-10"));
        HashMap<String,BazdkRegister> bazdkRegisterHashMap = new HashMap<>();
        List<BazdkRegister> bazdkRegisters = new ArrayList<>();
        bazdkRegisterList.forEach(obj->{
            String sKey = obj.getEaYsh()+"_"+obj.getEaXwhbz()+"_"+obj.getEaMzlxId()+""+obj.getEaJzrq()+""+obj.getEaJzxh();
            if (!bazdkRegisterHashMap.containsKey(sKey)){
                bazdkRegisterHashMap.put(sKey,obj);
                bazdkRegisters.add(obj);
            }
        });
        for (BazdkRegister bazdkRegister:bazdkRegisters){
            Ysk ysk = yskService.getOne(new QueryWrapper<Ysk>().lambda().eq(Ysk::getYsh, bazdkRegister.getEaYsh()));
            ordAppointmentService.transferOrdApp(bazdkRegister,ysk);
        }
    }
}