package com.yicloud.trans.service.mysql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mysql.PubStaffInfoMapper;
import com.yicloud.trans.model.mysql.PubStaffInfo;
import com.yicloud.trans.service.mysql.PubStaffInfoService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/28 12:52
 * @FileName: PubStaffInfoServiceImpl
 * @Description: ss
 */
@Service
@DS("master")
public class PubStaffInfoServiceImpl extends ServiceImpl<PubStaffInfoMapper, PubStaffInfo>implements PubStaffInfoService {
}
