package com.yicloud.trans.model.mssql;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * bazdk
 * @author 
 */
@Data
public class Bazdk implements Serializable {
    private static final long serialVersionUID = 4180748550508551394L;
    @TableId
    private Long id;

    private Integer zyh;

    private String mzkb;

    private Byte mzlxId;

    private String gh;

    private String ysh;

    private BigDecimal je;

    private Byte ghbz;

    private Integer jzdhId;

    private String jzkh;

    private Integer thid;

    private Integer rcJs;

    private Byte msf;

    private String jbh;

    private Byte xh;

    private Date mzrq;

    private String bz;

    private BigDecimal bnzhzcje;

    private BigDecimal lnzhzcje;

    private BigDecimal grxjzfje;

    private BigDecimal gwybzzfje;

    private String jylsh;

    private Byte ryxz;

    private String lbh;

    private BigDecimal mzqfxje;

    private BigDecimal mzqfxlnzh;

    private BigDecimal tczfje;

    private String gdbzbz;

    private String ywzqh;

    private String yyjylsh;

    private BigDecimal lxjj;

    private BigDecimal mztcje;

    private BigDecimal zntcjj;

    private BigDecimal lfjj;

    private BigDecimal lxjsjj;

    private BigDecimal grzfje;



    private BigDecimal zdjbbz;

    private Integer xtid;

    private String xwh;

    private String ybjbh;

    private String hzsmkJylsh;

    private String hzsmkJyfs;

    private BigDecimal hzsmkJyje;

    private BigDecimal zzmztcje;

    private BigDecimal lnjmjj;

    private String lnjmBz;

    private String jzkph;

    private BigDecimal snetjj;

    private BigDecimal nmgjj;

    private BigDecimal qfzfje;

    private BigDecimal qfbzzhzfje;

    private BigDecimal qfbzxjzfje;

    private BigDecimal qfyszhzfje;

    private BigDecimal qfysxjzfje;

    private BigDecimal cfdxgrzfje;




    private String yblbh;

    private BigDecimal cqznjj;

    private BigDecimal cqgflxjj;

    private BigDecimal cqlflxjj;

    private String ybCqh;

    private BigDecimal xnhjj;



    @TableField("jylsh_2")
    private String jylsh2;

    private String cxjmddbz;

    private String eaJrkJylsh;

    private String eaJrkLx;

    private String eaJrkNo;

    private BigDecimal eaJrkJyje;

    private BigDecimal bnzhye;

    private BigDecimal lnzhye;

    private String yybz;

    private Integer mzyskfkId;

    private String szysh;

    private Date zlsj;

    private String czbz;


}