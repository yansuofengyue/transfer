package com.yicloud.trans.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yicloud.trans.model.mssql.Jbxxk;
import com.yicloud.trans.model.mssql.zd.Gfjb2Ybbr;
import com.yicloud.trans.model.mssql.zd.YpCdWrapper;
import com.yicloud.trans.model.mysql.DrugRegionAlias;
import com.yicloud.trans.model.mysql.DrugRegionAliasAndYpCdWrapper;
import com.yicloud.trans.model.mysql.MidDrug;
import com.yicloud.trans.model.mysql.Patients;
import com.yicloud.trans.service.mysql.DrugRegionAliasService;
import com.yicloud.trans.service.mysql.MidDrugService;
import org.apache.ibatis.annotations.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/27 18:04
 * @FileName: MidDrugController
 * @Description: controller
 */

@RestController
@RequestMapping(value = "/midDrug")
public class MidDrugController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientsController.class);

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DrugRegionAliasService drugRegionAliasService;

    @Autowired
    private MidDrugService midDrugService;

    @PostMapping("/match")
    @ResponseBody
    public String match(@RequestBody DrugRegionAliasAndYpCdWrapper drugRegionAliasAndYpCdWrapper) throws Exception {
        MidDrug midDrug = new MidDrug();
        midDrug.setDrugRegionId(drugRegionAliasAndYpCdWrapper.getDrugRegionAlias().getId());
        midDrug.setDrugRegionName(drugRegionAliasAndYpCdWrapper.getDrugRegionAlias().getDrgName());
        midDrug.setDrugRegionSpecification(drugRegionAliasAndYpCdWrapper.getDrugRegionAlias().getDrgSpecification());

        midDrug.setYph(drugRegionAliasAndYpCdWrapper.getYpCdWrapper().getYph());
        midDrug.setGgxh(drugRegionAliasAndYpCdWrapper.getYpCdWrapper().getGgxh());
        midDrug.setCdId(drugRegionAliasAndYpCdWrapper.getYpCdWrapper().getCdid());
        midDrug.setYpmc(drugRegionAliasAndYpCdWrapper.getYpCdWrapper().getYpMc());
        midDrug.setYpgg(drugRegionAliasAndYpCdWrapper.getYpCdWrapper().getYpGg());
        midDrugService.saveOrUpdate(midDrug);
        return "papapapap@qq.com";
    }
}
