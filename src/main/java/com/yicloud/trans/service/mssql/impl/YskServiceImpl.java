package com.yicloud.trans.service.mssql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mssql.zd.YskMapper;
import com.yicloud.trans.model.mssql.zd.Ysk;
import com.yicloud.trans.service.mssql.YskService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/4/22 13:46
 * @FileName: YskServiceImpl
 * @Description: 老系统医生信息表接口实现
 */

@Service
@DS("slave")
public class YskServiceImpl extends ServiceImpl<YskMapper, Ysk> implements YskService {
}
