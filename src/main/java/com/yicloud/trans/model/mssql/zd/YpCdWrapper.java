package com.yicloud.trans.model.mssql.zd;

import lombok.Data;

import java.io.Serializable;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/26 17:56
 * @FileName: YpCdWrapper
 * @Description: 扩展
 */
@Data
public class YpCdWrapper implements Serializable {
    private static final long serialVersionUID = -5790580072300057621L;
    private String centerId;
    private String yph;
    private String ggxh;
    private Integer cdid;
    private String ypMc;
    private String ypGg;
    private String ypCd;
}
