package com.yicloud.trans.model.mssql;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * mzyskfk
 * @author 
 */
@Data
public class Mzyskfk implements Serializable {
    private Long id;

    private Long zyh;

    private String ysh;

    private String mzkb;

    private Date mzrq;

    private BigDecimal je;

    private String brzz;

    private Short xyzs;

    private Short cyzs;

    private Short zyzs;

    private String bzZg;

    private String bzKg;

    @TableField("bazdh_1")
    private String bazdh1;
    @TableField("zyzdh_1")
    private String zyzdh1;

    private String sfbz;

    private String bjgbBz;

    private String blZs;

    private String blXbs;

    private String blJws;

    private String blGrs;

    private String blJts;

    private String blTj;

    private Byte bkbz;

    private String jbm;


    private String zfysh;

    private String czfzqk;



    private String zzdbz;

    private static final long serialVersionUID = 1L;
}