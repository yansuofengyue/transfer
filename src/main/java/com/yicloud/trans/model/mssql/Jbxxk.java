package com.yicloud.trans.model.mssql;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * jbxxk
 * @author 
 */
@Data
public class Jbxxk implements Serializable {
    @TableId
    private Long zyh;

    private String xm;

    private Date mzrq;

    private String xb;

    private String dwm;

    private String dwh;

    private String fylb;

    private String gfzh;

    private BigDecimal zhje;

    private BigDecimal ljje;

    private String czyh;

    private String ghbz;

    private String sfzh;

    private String hy;

    private String jtdz;

    private String jtyb;

    private String zy;

    private String dwdh;

    private String dwyb;

    private String jg;

    private Date csny;

    private Integer dwid;

    private Integer nl;

    private String kb;

    private String ysh;

    private String xxnl;

    private Short zyhzfbz;

    private String gdbzbz;

    private String jbmc;

    private Date qsrq;

    private Date zzrq;

    private String sph;

    private String icmw;

    private Date ybdjrq;

    private String ybjbh;

    private String bxh;

    private String ybRylb;

    private String ybCbxz;

    private String ybJgbm;

    private String ybGwybz;

    private String jtdh;

    private String hzybBllb;

    private String shbzkh;

    private String mzfpXmDwm;

    private String hzybEybz;

    private String hzybYgys;

    private String hmdrybz;

    private String zjhm;

    private String dbbz;

    private Date scxzrq;

    private static final long serialVersionUID = 1L;
}