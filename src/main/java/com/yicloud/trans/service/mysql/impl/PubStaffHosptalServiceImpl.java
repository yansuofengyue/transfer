package com.yicloud.trans.service.mysql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mysql.PubStaffHosptalMapper;
import com.yicloud.trans.model.mysql.PubStaffHosptal;
import com.yicloud.trans.service.mysql.PubStaffHosptalService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/28 11:31
 * @FileName: PubStaffHosptalServiceImpl
 * @Description: ss
 */
@Service
@DS("master")
public class PubStaffHosptalServiceImpl extends ServiceImpl<PubStaffHosptalMapper, PubStaffHosptal>implements PubStaffHosptalService {
}
