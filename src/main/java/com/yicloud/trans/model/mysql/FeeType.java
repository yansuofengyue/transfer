package com.yicloud.trans.model.mysql;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * pub_fee_type
 * @author 
 */
@Data
@TableName("pub_fee_type")
public class FeeType implements Serializable {
    private static final long serialVersionUID = 3269770097881502516L;
    /**
     *  
     */
    private Long id;

    /**
     * 费用类别名称
     */
    private String feeName;

    /**
     * 拼音码
     */
    private String chinaSpell;

    /**
     * 五笔码
     */
    private String fiveStroke;

    /**
     * 行政区域
     */
    private String feeAdministrationCode;

    /**
     * 作废判别
     */
    private String feeState;

    /**
     * 医疗保险ID
     */
    private String feeInsuranceId;

    /**
     * 医保读卡方式
     */
    private String feeReadCard;

    private Date timeStamp;

}