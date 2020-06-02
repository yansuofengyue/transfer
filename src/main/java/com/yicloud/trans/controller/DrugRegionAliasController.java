package com.yicloud.trans.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yicloud.trans.model.mssql.Jbxxk;
import com.yicloud.trans.model.mssql.zd.YpGg;
import com.yicloud.trans.model.mssql.zd.YpMc;
import com.yicloud.trans.model.mysql.DrugRegionAlias;
import com.yicloud.trans.model.mysql.MidDrug;
import com.yicloud.trans.service.mssql.YpGgService;
import com.yicloud.trans.service.mssql.YpMcService;
import com.yicloud.trans.service.mysql.DrugRegionAliasService;
import com.yicloud.trans.service.mysql.MidDrugService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    private MidDrugService midDrugService;
    @Autowired
    private YpMcService ypMcService;
    @Autowired
    private YpGgService ypGgService;
    @Autowired
    private DrugRegionAliasService drugRegionAliasService;

    @RequestMapping("/list")
    public List<? extends Serializable> drugRegionAliasList(String drgState){
        List<DrugRegionAlias> list = new ArrayList<>(2048);
        if ( redisCacheTemplate.hasKey("drugRegionAlias")){
            Set<Serializable> drugRegionAliasList = redisCacheTemplate.opsForSet().members("drugRegionAlias");
            for (Serializable d :drugRegionAliasList){
                list.add((DrugRegionAlias) d);
            }
        }else {
            list = drugRegionAliasService.list();
            for (DrugRegionAlias drugRegionAlias:list){
                redisCacheTemplate.opsForSet().add("drugRegionAlias",drugRegionAlias);
            }
        }
        for (DrugRegionAlias drugRegionAlias:list){
            if (Optional.ofNullable(drugRegionAlias.getCenterId()).isPresent()){
                if (!drugRegionAlias.getCenterId().trim().isEmpty()){
                    String yph = drugRegionAlias.getCenterId().substring(0,4);
                    if ("0000".equals(yph)){
                        continue;
                    }
                    String ggxh = drugRegionAlias.getCenterId().substring(4,6);
                    Integer cdId = Integer.valueOf(drugRegionAlias.getCenterId().substring(6).trim());
                    List<MidDrug> midDrugs = midDrugService.list(new QueryWrapper<MidDrug>().lambda().eq(MidDrug::getDrugRegionId,drugRegionAlias.getId()).eq(MidDrug::getYph,yph).eq(MidDrug::getGgxh,ggxh).eq(MidDrug::getCdId,cdId));
                    if (!CollectionUtils.isEmpty(midDrugs)){
                        continue;
                    }
                    MidDrug midDrug = new MidDrug();
                    midDrug.setDrugRegionId(drugRegionAlias.getId());
                    midDrug.setDrugRegionName(drugRegionAlias.getDrgName());
                    midDrug.setDrugRegionSpecification(drugRegionAlias.getDrgSpecification());
                    midDrug.setSpeId(drugRegionAlias.getSpeId());
                    midDrug.setManId(drugRegionAlias.getManId());


                    midDrug.setYph(yph);
                    midDrug.setGgxh(ggxh);
                    midDrug.setCdId(cdId);
                    YpMc ypMc = ypMcService.getOne(new QueryWrapper<YpMc>().lambda().eq(YpMc::getYph,yph));
                    if (Optional.ofNullable(ypMc).isPresent()){
                        midDrug.setYpmc(ypMcService.getOne(new QueryWrapper<YpMc>().lambda().eq(YpMc::getYph,yph)).getYpmc());
                    }
                    YpGg ypGg = ypGgService.getOne(new QueryWrapper<YpGg>().lambda().eq(YpGg::getYph,yph).eq(YpGg::getGgxh,ggxh));
                    if (Optional.ofNullable(ypGg).isPresent()){
                        midDrug.setYpgg(ypGg.getYpgg());
                    }
                    midDrugService.saveOrUpdate(midDrug);
                }
            }
        }
        return list;
    }
}
