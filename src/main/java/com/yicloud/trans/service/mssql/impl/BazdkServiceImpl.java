package com.yicloud.trans.service.mssql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mssql.BazdkMapper;
import com.yicloud.trans.model.mssql.Bazdk;
import com.yicloud.trans.service.mssql.BazdkService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/29 13:59
 * @FileName: BazdkServiceImpl
 * @Description: ss
 */
@Service
@DS("slave")
public class BazdkServiceImpl extends ServiceImpl<BazdkMapper, Bazdk> implements BazdkService {
}
