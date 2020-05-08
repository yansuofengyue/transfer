package com.yicloud.trans.service.mssql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mssql.zd.Gfjb1Mapper;
import com.yicloud.trans.mapper.mssql.zd.Gfjb2Mapper;
import com.yicloud.trans.model.mssql.zd.Gfjb2;
import com.yicloud.trans.service.mssql.Gfjb2Service;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/4/19 12:29
 * @FileName: Gfjb2ServiceImpl
 * @Description: 老系统医保人员性质接口实现
 */
@Service
@DS("slave")
public class Gfjb2ServiceImpl extends ServiceImpl<Gfjb2Mapper, Gfjb2> implements Gfjb2Service {
}
