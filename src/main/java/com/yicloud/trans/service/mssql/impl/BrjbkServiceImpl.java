package com.yicloud.trans.service.mssql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mssql.BrjbkMapper;
import com.yicloud.trans.model.mssql.Brjbk;
import com.yicloud.trans.service.mssql.BrjbkService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/29 10:45
 * @FileName: BrjbkServiceImpl
 * @Description: kk
 */
@Service
@DS("slave")
public class BrjbkServiceImpl extends ServiceImpl<BrjbkMapper, Brjbk> implements BrjbkService {
}
