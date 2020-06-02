package com.yicloud.trans.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yicloud.trans.model.mssql.zd.*;
import com.yicloud.trans.service.mssql.YpCdService;
import com.yicloud.trans.service.mssql.YpGgService;
import com.yicloud.trans.service.mssql.YpMcService;
import com.yicloud.trans.service.mssql.ZdCdmcService;
import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/26 17:44
 * @FileName: YpCdController
 * @Description: 老系统药品产地
 */
@RestController
@RequestMapping(value = "/ypCd")
public class YpCdController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientsController.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;
    @Autowired
    private YpGgService ypGgService;
    @Autowired
    private YpMcService ypMcService;
    @Autowired
    private YpCdService ypCdService;
    @Autowired
    private ZdCdmcService zdCdmcService;


    @RequestMapping("/list")
    public List<YpCdWrapper> list() throws Exception {

        List<YpCdWrapper> ypCdWrapperList = new ArrayList<>(1024);

        if (redisCacheTemplate.hasKey("ypCdWrapper")) {
            Set<Serializable> serializableSet = redisCacheTemplate.opsForSet().members("ypCdWrapper");
            for (Serializable serializable:serializableSet){
                ypCdWrapperList.add((YpCdWrapper) serializable);
            }
            return ypCdWrapperList;
        } else {
            List<YpCd> ypCdList = ypCdService.list();
            for (YpCd ypCd : ypCdList) {
                YpCdWrapper ypCdWrapper = new YpCdWrapper();
                ypCdWrapper.setYph(ypCd.getYph().toString());
                ypCdWrapper.setGgxh(ypCd.getGgxh());
                ypCdWrapper.setCdid(ypCd.getCdId());
                ZdCdmc cdmc = zdCdmcService.getOne(new QueryWrapper<ZdCdmc>().lambda().eq(ZdCdmc::getId, ypCd.getCdId()));
                ypCdWrapper.setYpCd(cdmc.getMc());
                ypCdWrapper.setCenterId(ypCd.getYph().toString()+ypCd.getGgxh()+ypCd.getCdId().toString());
                YpGg ypGg = ypGgService.getOne(new QueryWrapper<YpGg>().lambda().eq(YpGg::getYph, ypCd.getYph()).eq(YpGg::getGgxh, ypCd.getGgxh()));
                ypCdWrapper.setYpGg(ypGg.getYpgg());
                YpMc ypMc = ypMcService.getOne(new QueryWrapper<YpMc>().lambda().eq(YpMc::getYph, ypCd.getYph()));
                ypCdWrapper.setYpMc(ypMc.getYpmc());
                ypCdWrapperList.add(ypCdWrapper);
                redisCacheTemplate.opsForSet().add("ypCdWrapper", ypCdWrapper);
                String redisKey = "ypCdWrapperHash";
                String redisHas=ypCdWrapper.getYph()+ypCdWrapper.getGgxh()+ypCdWrapper.getCdid().toString();
                if (redisCacheTemplate.opsForHash().hasKey(redisKey,redisHas)){
                    redisCacheTemplate.opsForHash().delete(redisKey,redisHas);
                }
                redisCacheTemplate.opsForHash().put(redisKey,redisHas,ypCdWrapper);
            }
        }
        return ypCdWrapperList;
    }
}
