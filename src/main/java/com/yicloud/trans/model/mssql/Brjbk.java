package com.yicloud.trans.model.mssql;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * brjbk
 * @author 
 */
@Data
public class Brjbk implements Serializable {
    @TableId
    private Long sfbhId;

    private Long zyh;

    private Integer jzdhId;

    private Date sfrq;

    private String fphm;

    private String czgh;

    private BigDecimal yjkje;

    private Integer fplx;

    private Integer zfbz;

    @TableField("je_1")
    private BigDecimal je1;
    @TableField("je_2")
    private BigDecimal je2;
    @TableField("je_3")
    private BigDecimal je3;

    private BigDecimal fyhj;

    private BigDecimal zfje;

    private BigDecimal bzylzfje;

    private String rq;

    private String fybz;

    private BigDecimal szpje;

    private BigDecimal tzpje;

    private BigDecimal zhjzje;

    private BigDecimal kfje;

    private String shbz;

    private BigDecimal grzfje;

    private BigDecimal ylypzl;

    private BigDecimal tjzl;

    private BigDecimal tzzl;

    private BigDecimal qfzfje;

    private BigDecimal qfbzzhzfje;

    private BigDecimal qfbzxjzfje;

    private BigDecimal bnzhzcje;

    private BigDecimal tczfje;

    private BigDecimal jzjzfje;

    private BigDecimal gwybzzfje;

    private String gfzh;

    private String lbh;

    private Integer dwid;

    private String xm;

    private String jylsh;

    private BigDecimal qfyszhzfje;

    private BigDecimal qfysxjzfje;

    private BigDecimal fd1zf;

    private BigDecimal fd2zf;

    private BigDecimal fd3zf;

    private BigDecimal fd4zf;

    private BigDecimal fd5zf;

    private BigDecimal cfdxgrzfje;

    private String gdbzbz;

    private Byte ryxz;

    private String jbmc;

    private Date qsrq;

    private Date zzrq;

    private String sph;

    private Integer tfbz;

    private BigDecimal yhje;

    private BigDecimal zk;

    private BigDecimal bnzhye;

    private BigDecimal lnzhye;

    private String ywzqh;

    private String yyjylsh;

    private String ybjbh;

    private BigDecimal lxjj;

    private BigDecimal mztcje;

    private BigDecimal zntcjj;

    private BigDecimal lfjj;

    private BigDecimal lxjsjj;

    private BigDecimal byjmje;

    private Integer xtid;

    private String ybbrmzhbz;

    private String hzsmkJylsh;

    private String hzsmkJyfs;

    private BigDecimal hzsmkJyje;

    private BigDecimal zzmztcje;

    private BigDecimal lnjmjj;

    private String lnjmBz;

    private String fpph;

    private BigDecimal snetjj;

    private BigDecimal nmgjj;





    private String yblbh;

    private BigDecimal cqznjj;

    private BigDecimal cqgflxjj;

    private BigDecimal cqlflxjj;

    private String ybCqh;

    private BigDecimal xnhjj;

    private BigDecimal setjjj;

    private String ybjbhCy;

    private BigDecimal lmjj;

    private BigDecimal lnzhzfZl;

    private String ysh;

    @TableField("jylsh_2")
    private String jylsh2;

    private BigDecimal scjjzfZf;

    private BigDecimal scjjzfZl;

    private String cxjmddbz;

    private String eaJrkJylsh;

    private String eaJrkLx;

    private String eaJrkNo;

    private BigDecimal eaJrkJyje;


    private String ybFjxx;

    private String csbz;



    private static final long serialVersionUID = 1L;
}