package com.yicloud.trans.model.mssql.zd;

import java.io.Serializable;
import lombok.Data;

/**
 * yp_mc
 * @author 
 */
@Data
public class YpMc implements Serializable {
    private static final long serialVersionUID = -846478625193233730L;
    private Integer yph;

    private String ypmc;

    private String ypbm;

    private Integer lsyph;

    private String bzbm;

    private String txm;

    private Integer lsid;

    private String zfbz;
}