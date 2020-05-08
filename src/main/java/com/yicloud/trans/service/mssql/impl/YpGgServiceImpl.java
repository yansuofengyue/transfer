package com.yicloud.trans.service.mssql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mssql.zd.YpGgMapper;
import com.yicloud.trans.model.mssql.zd.YpGg;
import com.yicloud.trans.service.mssql.YpGgService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/4/22 15:46
 * @FileName: YpGgServiceImpl
 * @Description: 药品规格信息接口实现
 */
@Service
@DS("slave")
public class YpGgServiceImpl extends ServiceImpl<YpGgMapper, YpGg> implements YpGgService {
}
