package com.yicloud.trans.service.mysql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mysql.VisitedInfoMapper;
import com.yicloud.trans.model.mysql.VisitedInfo;
import com.yicloud.trans.service.mysql.VisitedInfoService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/27 19:54
 * @FileName: VisitedInfoServiceImpl
 * @Description: ss
 */
@Service
@DS("master")
public class VisitedInfoServiceImpl extends ServiceImpl<VisitedInfoMapper, VisitedInfo> implements VisitedInfoService {
}
