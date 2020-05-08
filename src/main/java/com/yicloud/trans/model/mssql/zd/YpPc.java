package com.yicloud.trans.model.mssql.zd;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * yp_pc
 * @author 
 */
@Data
public class YpPc  implements Serializable {
    private Integer yph;

    private String ggxh;

    private Integer cdId;

    private String pcxh;

    private Integer lsid;

    private String scph;

    private Date yxq;

    private BigDecimal jj;

    private BigDecimal kcsl;

    private Integer dzbz;

    private Integer pbbz;

    private String pzwh;

    private static final long serialVersionUID = 1L;
}