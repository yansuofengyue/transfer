package com.yicloud.trans.service.mssql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mssql.zd.ZdCdmcMapper;
import com.yicloud.trans.model.mssql.zd.ZdCdmc;
import com.yicloud.trans.service.mssql.ZdCdmcService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/27 17:55
 * @FileName: ZdCdmcServiceImpl
 * @Description: 实现
 */

@Service
@DS("slave")
public class ZdCdmcServiceImpl extends ServiceImpl<ZdCdmcMapper, ZdCdmc> implements ZdCdmcService {
}
