package com.yicloud.trans.service.mysql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mysql.OrdSourceDetailMapper;
import com.yicloud.trans.model.mysql.OrdSourceDetail;
import com.yicloud.trans.service.mysql.OrdSourceDetailService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/7 11:20
 * @FileName: OrdSourceDetailServiceImpl
 * @Description: 新系统号源明细记录表实现
 */
@Service
@DS("master")
public class OrdSourceDetailServiceImpl extends ServiceImpl<OrdSourceDetailMapper, OrdSourceDetail> implements OrdSourceDetailService {
}
