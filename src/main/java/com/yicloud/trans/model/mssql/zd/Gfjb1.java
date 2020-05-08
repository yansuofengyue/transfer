package com.yicloud.trans.model.mssql.zd;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * zd_gfjb1
 * @author 
 */
@Data
@TableName("zd_gfjb1")
public class Gfjb1 implements Serializable {
    private String gfjb;

    private String jbm;

    private static final long serialVersionUID = 1L;
}