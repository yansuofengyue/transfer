package com.yicloud.trans.service.mysql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mysql.OrdSchedulingMapper;
import com.yicloud.trans.model.mysql.OrdScheduling;
import com.yicloud.trans.service.mysql.OrdSchedulingService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/7 11:18
 * @FileName: OrdSchedulingServiceImpl
 * @Description: 新系统排班记录表接口实现
 */
@Service
@DS("master")
public class OrdSchedulingServiceImpl extends ServiceImpl<OrdSchedulingMapper, OrdScheduling> implements OrdSchedulingService {
}
