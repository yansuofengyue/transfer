package com.yicloud.trans.model.mssql;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * ea_bazdk_register
 * @author 
 */
@Data
@TableName("ea_bazdk_register")
@Component
public class BazdkRegister implements Serializable {
    private static final long serialVersionUID = -3761944043040786498L;
    private Integer eaId;

    private Integer eaZyh;

    private String eaXm;

    private String eaXb;

    private String eaSfzh;

    private Date eaJzrq;

    private String eaMzkb;

    private Integer eaMzlxId;

    private Integer eaYsh;

    private Integer eaXwhbz;

    private String eaLbh;

    private String eaGfzh;

    private String eaNl;

    private Date eaCsrq;

    private String eaZy;

    private String eaJg;

    private String eaGzdw;

    private String eaDwdh;

    private String eaJtdz;

    private String eaSfzhYyr;

    private String eaDjgh;

    private Date eaDjrq;

    private String eaYybz;

    private String eaZlkh;

    private String eaDrbz;

    private Date eaDrrq;

    private String eaDrgh;

    private Integer eaLsid;

    private Integer eaThbz;

    private String eaThjzkhsybz;

    private String eaQxbz;

    private Date eaQxrq;

    private String eaQxgh;

    private String eaXq;

    private Short eaJzxh;

    private String eaQxyy;

    private String eaSjhm;

    private String eaZlks;

    private String eaDjrxm;

    private Integer eaXtid;

    private String eaTakeCode;

    private Integer eaPatientId;

    private String eaUpdateBz;

    private Integer syncState;

    private Date eaSfrq;

    private Date eaFyrq;

    private Date eaUpdateTime;

    private Byte eaFbid;

    private Date yyghSendTime;

    private String eaFwsbm;

    private String eaFwsgh;

    private String eaYyjgdm;

    private String eaYyjgmc;

    private String eaKlx;

    private String eaKhm;

    private String eaBzsm;


}