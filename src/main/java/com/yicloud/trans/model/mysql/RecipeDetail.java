package com.yicloud.trans.model.mysql;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * cli_recipe_detail
 * @author 
 */
@Data
@TableName("cli_recipe_detail")
public class RecipeDetail implements Serializable {
    private Long id;
    /**
     * 处方主表
     */
    private Long recipeId;

    /**
     * 药品规格序号
     */
    private Long speId;

    /**
     * 药品产地序号
     */
    private Long manId;

    /**
     * 费用归并
     */
    private Long subId;

    /**
     * 序号
     */
    private Integer redNum;

    /**
     * 药品名称
     */
    private String drgName;

    /**
     * 包装单位
     */
    private String drgPackingUnit;

    /**
     * 单价
     */
    private BigDecimal redPrice;

    /**
     * 数量
     */
    private BigDecimal redQuantity;

    /**
     * 单次剂量
     */
    private BigDecimal redOnceDose;

    /**
     * 剂量单位
     */
    private String redDoseUnit;

    /**
     * 用药天数
     */
    private Integer redUseDay;

    /**
     * 频率
     */
    private String freEnName;

    /**
     * 金额
     */
    private BigDecimal redSumFee;

    /**
     * 给药方式
     */
    private Integer modId;

    /**
     * 用药频次
     */
    private Long freqId;

    /**
     * 医疗机构
     */
    private Long hospitalId;

    /**
     * 分组序号
     */
    private Integer redGroupNum;

    /**
     * 药房批次信息
     */
    private String redBatch;

    /**
     * 强制自费标识
     */
    private Integer redSelfPaying;

    /**
     * 医生嘱托
     */
    private String redSummary;

    /**
     * 皮试标志
     */
    private String redSkinTest;

    /**
     * 代理发药(0是发药，1是代煎)
     */
    private String agentFlag;

    /**
     * 协定方ID
     */
    private Long treId;

    /**
     * 医保等级
     */
    private Integer chargeLevel;

    /**
     * 自付比例
     */
    private BigDecimal redSelfScale;

    /**
     * 超帖数
     */
    private Integer beyondPosts;

    /**
     * 超数量
     */
    private BigDecimal beyondQuantity;

    /**
     * 代发日期
     */
    private Date agentDate;

    /**
     * 退药数量
     */
    private BigDecimal drgDiscount;

    private Date timeStamp;

    private static final long serialVersionUID = 1L;
}