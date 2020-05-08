package com.yicloud.trans.service.mssql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mssql.BazdkRegisterMapper;
import com.yicloud.trans.model.mssql.BazdkRegister;
import com.yicloud.trans.service.mssql.BazdkRegisterService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/7 11:07
 * @FileName: BazdkRegisterServiceImpl
 * @Description: 老系统预约挂号信息表接口实现
 */
@Service
@DS("slave")
public class BazdkRegisterServiceImpl extends ServiceImpl<BazdkRegisterMapper, BazdkRegister> implements BazdkRegisterService {
}
