package com.yicloud.trans.service.mssql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mssql.zd.JbkMapper;
import com.yicloud.trans.model.mssql.zd.Jbk;
import com.yicloud.trans.service.mssql.JbkService;
import org.springframework.stereotype.Service;

@Service
@DS("slave")
public class JbkServiceImpl extends ServiceImpl<JbkMapper, Jbk>implements JbkService {
}
