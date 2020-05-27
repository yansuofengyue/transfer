package com.yicloud.trans.service.mysql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mysql.ExamineDetailMapper;
import com.yicloud.trans.model.mysql.ExamineDetail;
import com.yicloud.trans.service.mysql.ExamineDetailService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/27 19:48
 * @FileName: ExamineDetailServiceImpl
 * @Description: ss
 */
@Service
@DS("master")
public class ExamineDetailServiceImpl extends ServiceImpl<ExamineDetailMapper, ExamineDetail> implements ExamineDetailService {
}
