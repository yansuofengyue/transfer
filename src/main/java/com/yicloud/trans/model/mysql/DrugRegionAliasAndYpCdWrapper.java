package com.yicloud.trans.model.mysql;

import com.yicloud.trans.model.mssql.zd.YpCdWrapper;
import lombok.Data;

import java.io.Serializable;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/28 8:20
 * @FileName: DrugRegionAliasAndYpCdWrapper
 * @Description: 匹配入参
 */
@Data
public class DrugRegionAliasAndYpCdWrapper implements Serializable {
    private DrugRegionAlias drugRegionAlias;
    private YpCdWrapper ypCdWrapper;
}
