package com.yicloud.trans.service.mssql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mssql.MzyskfkMapper;
import com.yicloud.trans.model.mssql.Mzyskfk;
import com.yicloud.trans.service.mssql.MzyskfkService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/28 11:25
 * @FileName: MzyskfkServiceImpl
 * @Description: shix
 */
@Service
@DS("slave")
public class MzyskfkServiceImpl extends ServiceImpl<MzyskfkMapper, Mzyskfk>implements MzyskfkService {
}
