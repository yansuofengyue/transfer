package com.yicloud.trans.service.mssql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mssql.zd.YpCdMapper;
import com.yicloud.trans.model.mssql.zd.YpCd;
import com.yicloud.trans.service.mssql.YpCdService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/4/22 13:36
 * @FileName: YpCdService
 * @Description: 老系统药品产地信息表接口实现
 */
@Service
@DS("slave")
public class YpCdServiceImpl extends ServiceImpl<YpCdMapper, YpCd> implements YpCdService {
}
