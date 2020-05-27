package com.yicloud.trans.service.mysql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mysql.RecipeInfoMapper;
import com.yicloud.trans.model.mysql.RecipeInfo;
import com.yicloud.trans.service.mysql.RecipeInfoService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/27 19:52
 * @FileName: RecipeInfoServiceImpl
 * @Description: ss
 */
@Service
@DS("master")
public class RecipeInfoServiceImpl extends ServiceImpl<RecipeInfoMapper, RecipeInfo> implements RecipeInfoService {
}
