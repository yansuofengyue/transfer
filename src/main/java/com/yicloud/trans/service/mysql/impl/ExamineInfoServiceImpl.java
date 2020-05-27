package com.yicloud.trans.service.mysql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mysql.ExamineInfoMapper;
import com.yicloud.trans.model.mysql.ExamineInfo;
import com.yicloud.trans.service.mysql.ExamineInfoService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/27 19:49
 * @FileName: ExamineInfoServiceImpl
 * @Description: ss
 */
@Service
@DS("master")
public class ExamineInfoServiceImpl extends ServiceImpl<ExamineInfoMapper, ExamineInfo> implements ExamineInfoService {
}
