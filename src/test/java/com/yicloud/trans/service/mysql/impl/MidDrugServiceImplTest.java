package com.yicloud.trans.service.mysql.impl;

import com.yicloud.trans.controller.PatientsController;
import com.yicloud.trans.model.mysql.MidDrug;
import com.yicloud.trans.service.mysql.MidDrugService;
import com.yicloud.trans.service.mysql.OrdAppointmentService;
import com.yicloud.trans.service.mysql.OrdSchedulingService;
import com.yicloud.trans.service.mysql.OrdSourceDetailService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/7 11:39
 * @FileName: MidDrugServiceImplTest
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class MidDrugServiceImplTest {

    @Autowired
    private MidDrugService midDrugService;
    @Autowired
    private OrdAppointmentService ordAppointmentService;
    @Autowired
    private OrdSchedulingService ordSchedulingService;
    @Autowired
    private OrdSourceDetailService ordSourceDetailService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientsController.class);

    @Test
    void testTransfer(){

    }
}