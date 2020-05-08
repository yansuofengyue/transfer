package com.yicloud.trans.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yicloud.trans.model.mssql.Jbxxk;
import com.yicloud.trans.model.mysql.Patients;
import com.yicloud.trans.service.mssql.JbxxkService;
import com.yicloud.trans.service.mysql.PatientsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/4/19 10:13
 * @FileName: PatientsController
 * @Description: 人员信息处理接口层
 */
@RestController
@RequestMapping(value = "/")
public class PatientsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientsController.class);

    @Autowired
    private PatientsService patientsService;
    @Autowired
    private JbxxkService jbxxkService;

    @RequestMapping("test")
    public String test(){
        List<Jbxxk> list = jbxxkService.list(new QueryWrapper<Jbxxk>().lambda().eq(Jbxxk::getFylb, "00").or().eq(Jbxxk::getSfzh,""));
        list.forEach(u -> LOGGER.info("当前用户数据:{}", u));
        return "papapapap@qq.com";
    }
}
