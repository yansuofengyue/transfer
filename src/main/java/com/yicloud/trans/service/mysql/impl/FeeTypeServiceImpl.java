package com.yicloud.trans.service.mysql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mysql.FeeTypeMapper;
import com.yicloud.trans.model.mysql.FeeType;
import com.yicloud.trans.service.mysql.FeeTypeService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/4/19 11:49
 * @FileName: FeeTypeServiceImpl
 * @Description: java版系统医保分类表接口实现
 */
@Service
@DS("master")
public class FeeTypeServiceImpl extends ServiceImpl<FeeTypeMapper, FeeType> implements FeeTypeService {
}
