package com.yicloud.trans.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yicloud.trans.model.mssql.zd.YpCd;
import com.yicloud.trans.model.mssql.zd.YpCdWrapper;
import com.yicloud.trans.model.mssql.zd.YpGg;
import com.yicloud.trans.model.mssql.zd.YpMc;
import com.yicloud.trans.model.mysql.DrugRegionAlias;
import com.yicloud.trans.service.mssql.YpCdService;
import com.yicloud.trans.service.mssql.YpGgService;
import com.yicloud.trans.service.mssql.YpMcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
    private YpGgService ypGgService;
    @Autowired
    private YpMcService ypMcService;
    @Autowired
    private YpCdService ypCdService;

    @RequestMapping("/list")
    public List<YpCdWrapper> list() throws Exception {
        List<YpCdWrapper> ypCdWrapperList = new ArrayList<>(1024);
        List<YpCd> ypCdList = ypCdService.list();
        for (YpCd ypCd:ypCdList){
            YpCdWrapper ypCdWrapper = new YpCdWrapper();
            ypCdWrapper.setYpCd(ypCd);
            YpGg ypGg = ypGgService.getOne(new QueryWrapper<YpGg>().lambda().eq(YpGg::getYph, ypCd.getYph()).eq(YpGg::getGgxh,ypCd.getGgxh()));
            ypCdWrapper.setYpGg(ypGg);
            YpMc ypMc = ypMcService.getOne(new QueryWrapper<YpMc>().lambda().eq(YpMc::getYph, ypCd.getYph()));
            ypCdWrapper.setYpMc(ypMc);
            ypCdWrapperList.add(ypCdWrapper);
        }
        return ypCdWrapperList;
    }
}
