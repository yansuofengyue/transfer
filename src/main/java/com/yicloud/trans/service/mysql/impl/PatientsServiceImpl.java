package com.yicloud.trans.service.mysql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mysql.PatientsMapper;
import com.yicloud.trans.model.mysql.Patients;
import com.yicloud.trans.service.mysql.PatientsService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/4/19 10:00
 * @FileName: PatientsServiceImpl
 * @Description: \
 */
@Service
@DS("master")
public class PatientsServiceImpl extends ServiceImpl<PatientsMapper, Patients> implements PatientsService {

}
