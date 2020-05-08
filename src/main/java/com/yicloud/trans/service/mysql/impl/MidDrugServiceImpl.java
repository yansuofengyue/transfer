package com.yicloud.trans.service.mysql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicloud.trans.mapper.mysql.MidDrugMapper;
import com.yicloud.trans.model.mysql.MidDrug;
import com.yicloud.trans.service.mysql.MidDrugService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/4/19 16:33
 * @FileName: MidDrugServiceImpl
 * @Description: 新老系统药品转移中间表接口实现
 */
@Service
@DS("transfer")
public class MidDrugServiceImpl extends ServiceImpl<MidDrugMapper, MidDrug> implements MidDrugService {
}
