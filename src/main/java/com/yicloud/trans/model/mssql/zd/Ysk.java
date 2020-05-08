package com.yicloud.trans.model.mssql.zd;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * zd_ysk
 * @author 
 */
@Data
@TableName("zd_ysk")
public class Ysk implements Serializable {
    private Integer ysh;

    private String bmh;

    private String ysm;

    private String ysbm;

    private Byte zdq;

    private Byte cfq;

    private String sh;

    private Byte mzq;

    private Byte mcq;

    private String jb;

    private String zxbz;

    private String bz;

    private String xb;

    private Date yyghUpdateTime;

    private Date yyghSendTime;

    private Byte yyghSyncState;

    private String grjs;

    private String szybKbbm;

    private String szybKbmc;

    private String szybYsfwbm;

    private String szybKbbmNew;

    private String szybKbmcNew;

    private String szybYszc;

    private String xlgbz;

    private static final long serialVersionUID = 1L;
}