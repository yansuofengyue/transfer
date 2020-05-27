package com.yicloud.trans.service.mysql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mysql.MedChargesInfoMapper;
import com.yicloud.trans.model.mysql.MedChargesInfo;
import com.yicloud.trans.service.mysql.MedChargesInfoService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/27 19:50
 * @FileName: MedChargesInfoServiceImpl
 * @Description: ss
 */
@Service
@DS("master")
public class MedChargesInfoServiceImpl extends ServiceImpl<MedChargesInfoMapper, MedChargesInfo> implements MedChargesInfoService {
}
