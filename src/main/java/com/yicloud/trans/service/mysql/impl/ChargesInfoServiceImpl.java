package com.yicloud.trans.service.mysql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mysql.ChargesInfoMapper;
import com.yicloud.trans.model.mysql.ChargesInfo;
import com.yicloud.trans.service.mysql.ChargesInfoService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/27 19:45
 * @FileName: ChargesInfoServiceImpl
 * @Description: 实现
 */
@Service
@DS("master")
public class ChargesInfoServiceImpl extends ServiceImpl<ChargesInfoMapper, ChargesInfo> implements ChargesInfoService {
}
