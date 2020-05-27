package com.yicloud.trans.service.mysql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mysql.CliDiagnoseInfoMapper;
import com.yicloud.trans.model.mysql.CliDiagnoseInfo;
import com.yicloud.trans.service.mysql.CliDiagnoseInfoService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/27 19:47
 * @FileName: CliDiagnoseInfoServiceImpl
 * @Description: dd
 */
@Service
@DS("master")
public class CliDiagnoseInfoServiceImpl extends ServiceImpl<CliDiagnoseInfoMapper, CliDiagnoseInfo> implements CliDiagnoseInfoService {
}
