package com.yicloud.trans.service.mysql.impl;

import com.yicloud.trans.model.mysql.Patients;
import com.yicloud.trans.service.mysql.PatientsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/6/1 8:57
 * @FileName: PatientsServiceImplTest
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class PatientsServiceImplTest {

    @Autowired
    private PatientsService patientsService;
    @Test
    void createPatients() throws Exception {
       Patients patients =  patientsService.createPatients(127347L);
        System.out.println(patients.toString());
    }
}