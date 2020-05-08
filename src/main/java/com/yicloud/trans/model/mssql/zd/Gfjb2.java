package com.yicloud.trans.model.mssql.zd;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * zd_gfjb2
 * @author 
 */
@Data
@TableName("zd_gfjb2")
public class Gfjb2 implements Serializable {
    @TableId
    private String lbh;

    private String lbm;

    private String gfjb;

    private BigDecimal cwf;

    private BigDecimal ypf;

    private BigDecimal dxf;

    private BigDecimal zcyf;

    private BigDecimal cyf;

    private BigDecimal qtf;

    private String jsms;

    private BigDecimal ypfMz;

    private BigDecimal dxfMz;

    private BigDecimal zcyfMz;

    private BigDecimal cyfMz;

    private BigDecimal qtfMz;

    private BigDecimal cwfMz;

    private String jsmsMz;

    private String lbbz;

    private Object decwf;

    private String pbbz;

    private static final long serialVersionUID = 1L;
}