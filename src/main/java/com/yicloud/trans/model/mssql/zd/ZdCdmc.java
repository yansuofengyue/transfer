package com.yicloud.trans.model.mssql.zd;

import java.io.Serializable;
import lombok.Data;

/**
 * zd_cdmc
 * @author 
 */
@Data
public class ZdCdmc implements Serializable {
    private Integer id;

    private String mc;

    private String bm;

    private static final long serialVersionUID = 1L;
}