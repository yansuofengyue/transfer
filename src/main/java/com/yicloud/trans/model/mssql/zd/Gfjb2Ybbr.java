package com.yicloud.trans.model.mssql.zd;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * zd_gfjb2_ybbr
 * @author 
 */
@Data
@TableName("zd_gfjb2_ybbr")
public class Gfjb2Ybbr implements Serializable {
    @TableId
    private String lbh;

    private String yblbh;

    private String yblbm;

    private static final long serialVersionUID = 1L;
}