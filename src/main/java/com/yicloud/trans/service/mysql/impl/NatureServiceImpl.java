package com.yicloud.trans.service.mysql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mysql.NatureMapper;
import com.yicloud.trans.model.mysql.Nature;
import com.yicloud.trans.service.mysql.NatureService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/4/19 12:11
 * @FileName: NatureServiceImpl
 * @Description: java版系统医保性质接口实现
 */
@Service
@DS("master")
public class NatureServiceImpl extends ServiceImpl<NatureMapper, Nature> implements NatureService {
}
