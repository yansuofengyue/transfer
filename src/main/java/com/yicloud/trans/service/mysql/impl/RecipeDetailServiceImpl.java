package com.yicloud.trans.service.mysql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mysql.RecipeDetailMapper;
import com.yicloud.trans.model.mysql.RecipeDetail;
import com.yicloud.trans.service.mysql.RecipeDetailService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/27 19:51
 * @FileName: RecipeDetailServiceImpl
 * @Description: dd
 */
@Service
@DS("master")
public class RecipeDetailServiceImpl extends ServiceImpl<RecipeDetailMapper, RecipeDetail> implements RecipeDetailService {
}
