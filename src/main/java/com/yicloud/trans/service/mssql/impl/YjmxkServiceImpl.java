package com.yicloud.trans.service.mssql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mssql.YjmxkMapper;
import com.yicloud.trans.model.mssql.Yjmxk;
import com.yicloud.trans.service.mssql.YjmxkService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/29 13:21
 * @FileName: YjmxkServiceImpl
 * @Description: ss
 */
@Service
@DS("slave")
public class YjmxkServiceImpl extends ServiceImpl<YjmxkMapper, Yjmxk> implements YjmxkService {
}
