package com.yicloud.trans.service.mssql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mssql.JbxxkMapper;
import com.yicloud.trans.model.mssql.Jbxxk;
import com.yicloud.trans.service.mssql.JbxxkService;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/4/19 10:07
 * @FileName: JbxxkServiceImpl
 * @Description: 桐君堂人员信息接口实现类
 */
@Service
@DS("slave")
public class JbxxkServiceImpl extends ServiceImpl<JbxxkMapper, Jbxxk> implements JbxxkService {
}
