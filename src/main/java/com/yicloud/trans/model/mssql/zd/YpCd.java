package com.yicloud.trans.model.mssql.zd;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * yp_cd
 * @author 
 */
@Data
public class YpCd  implements Serializable {
    private static final long serialVersionUID = -973775354796754888L;
    @TableId
    private Integer yph;
    @TableId
    private String ggxh;
    @TableId
    private Integer cdId;

    private BigDecimal pfj;

    private BigDecimal lsj;

    private BigDecimal jhj;

    private String pzwh;

    private String cdmc;

    private BigDecimal dblsj;

    private String pbbz;

}