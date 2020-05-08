package com.yicloud.trans.service.mssql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mssql.zd.YpMcMapper;
import com.yicloud.trans.model.mssql.zd.YpMc;
import com.yicloud.trans.service.mssql.YpMcService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/4/22 13:43
 * @FileName: YpMcServiceImpl
 * @Description: 老系统药品名称信息接口实现
 */
@Service
@DS("slave")
public class YpMcServiceImpl extends ServiceImpl<YpMcMapper, YpMc> implements YpMcService {
}
