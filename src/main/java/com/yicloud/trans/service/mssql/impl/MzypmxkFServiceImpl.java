package com.yicloud.trans.service.mssql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mssql.MzypmxkFMapper;
import com.yicloud.trans.model.mssql.MzypmxkF;
import com.yicloud.trans.service.mssql.MzypmxkFService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/28 16:10
 * @FileName: MzypmxkFServiceImpl
 * @Description: ss
 */
@Service
@DS("slave")
public class MzypmxkFServiceImpl extends ServiceImpl<MzypmxkFMapper, MzypmxkF>implements MzypmxkFService {
}
