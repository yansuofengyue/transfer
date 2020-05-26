package com.yicloud.trans.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yicloud.trans.model.mssql.Jbxxk;
import com.yicloud.trans.model.mysql.DrugRegionAlias;
import com.yicloud.trans.service.mysql.DrugRegionAliasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/26 11:54
 * @FileName: DrugRegionAliasController
 * @Description: 药品controller
 */
@RestController
@RequestMapping(value = "/drugRegionAlias")
public class DrugRegionAliasController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientsController.class);

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    @Autowired
    private DrugRegionAliasService drugRegionAliasService;

    @RequestMapping("/list")
    public List<DrugRegionAlias> drugRegionAliasList(String drgState){
        List<DrugRegionAlias> list=new ArrayList<>(2048);
        if (redisCacheTemplate.opsForSet().size("drugRegionAlias")>0){
            list = (List<DrugRegionAlias>) redisCacheTemplate.opsForSet().pop("drugRegionAlias",redisCacheTemplate.opsForSet().size("drugRegionAlias"));
             list = drugRegionAliasService.list(new QueryWrapper<DrugRegionAlias>().lambda().eq(DrugRegionAlias::getDrgType, "1").eq(DrugRegionAlias::getDrgState,drgState));
        }else {
            list = drugRegionAliasService.list(new QueryWrapper<DrugRegionAlias>().lambda().eq(DrugRegionAlias::getDrgType, "1").eq(DrugRegionAlias::getDrgState,drgState));
            for (DrugRegionAlias drugRegionAlias:list){
                redisCacheTemplate.opsForSet().add("drugRegionAlias",drugRegionAlias);
            }
        }

        return list;
    }
}
