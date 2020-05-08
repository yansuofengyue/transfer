package com.yicloud.trans.service.mysql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mysql.DrugRegionAliasMapper;
import com.yicloud.trans.model.mysql.DrugRegionAlias;
import com.yicloud.trans.service.mysql.DrugRegionAliasService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/4/19 16:40
 * @FileName: DrugRegionAliasServiceImpl
 * @Description: java版系统药品产地信息表接口实现
 */
@Service
@DS("master")
public class DrugRegionAliasServiceImpl extends ServiceImpl<DrugRegionAliasMapper,DrugRegionAlias> implements DrugRegionAliasService {
}
