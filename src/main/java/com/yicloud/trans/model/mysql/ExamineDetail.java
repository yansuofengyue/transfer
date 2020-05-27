package com.yicloud.trans.model.mysql;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * cli_examine_detail
 * @author 
 */
@Data
@TableName("cli_examine_detail")
public class ExamineDetail implements Serializable {
    private Long id;

    private Long examineId;

    /**
     * 序号
     */
    private Integer exdNum;

    /**
     * 收费项目序号
     */
    private Long itemId;

    /**
     * 收费项目名称
     */
    private String itemName;

    /**
     * 单价
     */
    private BigDecimal exdPrice;

    /**
     * 数量
     */
    private Integer exdQuantity;

    /**
     * 归并科目
     */
    private Long subId;

    /**
     * 收费项目等级
     */
    private Integer chargeLevel;

    /**
     * 自负比例
     */
    private BigDecimal exdSelfScale;

    /**
     * 执行医生工号
     */
    private Long sffId;

    /**
     * 执行科室
     */
    private Long depId;

    /**
     * 优惠单价
     */
    private BigDecimal exdFavorablePrice;

    /**
     * 优惠金额
     */
    private BigDecimal exdFavorFee;

    /**
     * 项目序号（组套的项目）
     */
    private Long blsId;

    /**
     * 项目单位
     */
    private String itemUnit;

    /**
     * 强制自费标识
     */
    private Integer redSelfPaying;

    /**
     * 限量
     */
    private Integer itemLimQuantity;

    private Date timeStamp;

    private static final long serialVersionUID = 1L;
}