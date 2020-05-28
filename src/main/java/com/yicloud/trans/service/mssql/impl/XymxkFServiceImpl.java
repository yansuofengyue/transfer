package com.yicloud.trans.service.mssql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mssql.XymxkFMapper;
import com.yicloud.trans.model.mssql.XymxkF;
import com.yicloud.trans.service.mssql.XymxkFService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/28 17:16
 * @FileName: XymxkFServiceImpl
 * @Description: ss
 */
@Service
@DS("slave")
public class XymxkFServiceImpl extends ServiceImpl<XymxkFMapper, XymxkF> implements XymxkFService {
}
