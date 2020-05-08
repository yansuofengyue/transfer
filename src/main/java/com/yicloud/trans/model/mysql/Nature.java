package com.yicloud.trans.model.mysql;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * pub_nature
 * @author 
 */
@Data
@TableName("pub_nature")
public class Nature implements Serializable {
    private Long id;

    /**
     * 性质名称
     */
    private String natName;

    /**
     * 性质代码
     */
    private String natCode;

    /**
     * 险种
     */
    private String natType;

    /**
     * 拼音码
     */
    private String chinaSpell;

    /**
     * 五笔码
     */
    private String fiveStroke;

    /**
     * 医保类别序号
     */
    private Long feeId;

    private Date timeStamp;

    private static final long serialVersionUID = 1L;
}