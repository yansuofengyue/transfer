package com.yicloud.trans.model.mysql;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * cli_recipe_info
 * @author 
 */
@Data
@TableName("cli_recipe_info")
public class RecipeInfo implements Serializable {
    @TableId
    private Long id;

    /**
     * 就诊序号
     */
    private Long visId;

    /**
     * 病人序号
     */
    private Long patId;

    /**
     * 病人性质
     */
    private Long natId;

    /**
     * 费用类别
     */
    private Long feeId;

    /**
     * 收费序号
     */
    private Long chargesId;

    /**
     * 医生工号
     */
    private Long sffId;

    /**
     * 处方科室
     */
    private Long depId;

    /**
     * 处方号
     */
    private String rcpNum;

    /**
     * 病人证号
     */
    private String patCardNum;

    /**
     * 病人姓名
     */
    private String patName;

    /**
     * 处方贴数
     */
    private Integer rcpPosts;

    /**
     * 处方类型（2中，3成，1西）
     */
    private Integer rcpType;

    /**
     * 处方类别(0 急诊，1普通，2儿科处方)
     */
    private Integer recCategory;

    /**
     * 处方日期
     */
    private Date rcpDate;

    /**
     * 处方金额
     */
    private BigDecimal recFee;

    /**
     * 原处方序号
     */
    private Long recipeId;

    /**
     * 退费判别(1退费)
     */
    private String rcpRefundSign;

    /**
     * 发药序号
     */
    private Long dispensingId;

    /**
     * 发药判别(0未发药1发药 2退药 .3.取消退药)
     */
    private String dispensingSign;

    /**
     * 代煎代发标志（ (0、不煎发药1、代煎发药 2、代煎代发)）
     */
    private String rcpBoilSign;

    /**
     * 发药日期
     */
    private Date dispensingDate;

    /**
     * 发药工号
     */
    private Long dispensingSffId;

    /**
     * 发药人员
     */
    private String dispensingSffName;

    /**
     * 发药科室
     */
    private Long dispensingDepId;

    /**
     * 退药作工号
     */
    private Long refundSffId;

    /**
     * 退药状态(正常1不正常)
     */
    private Integer rcpUpdateSign;

    /**
     * 药房序号
     */
    private Integer rcpStoreId;

    /**
     * 所属机构
     */
    private Long hospitalId;

    /**
     *  医生嘱托
     */
    private String recDoctorAsks;

    /**
     * 外配处方标识 0 非外配处方 1 外配处方
     */
    private String outerRecipeSign;

    /**
     * 外配处方备案号
     */
    private String outerRecipeNo;

    /**
     * 药房每日结转编号
     */
    private String storeDiraryAccount;

    /**
     * 外配处方标识
     */
    private String outerRepealSign;

    /**
     * 领药证号(8位日期+代煎标识+膏方标识+6位序列号+6位机构号)
     */
    private String receiveNum;

    /**
     * 打印标志 0未打印 1打印
     */
    private String printFlag;

    /**
     * 备注
     */
    private String summary;

    /**
     * 审核工号
     */
    private Long checkSffId;

    /**
     * 审核操作员姓名
     */
    private String checkSffName;

    /**
     * 审核日期
     */
    private Date checkDate;

    /**
     * 配药人员工号
     */
    private Long doseSffId;

    /**
     * 配药人员姓名
     */
    private String doseSffName;

    /**
     * 配药日期
     */
    private Date doseDate;

    /**
     * 审核标志
     */
    private String checkFlag;

    /**
     * 代煎代发日期
     */
    private Date agentDate;

    /**
     * 中药剂型
     */
    private Long rcpSpecialUsage;

    /**
     * 每贴包数
     */
    private Long everyPackes;

    /**
     * 膏方标志
     */
    private String creamFormula;

    /**
     * 浓缩程度
     */
    private Long condense;

    /**
     * 规定病种标识 0普通1规定病种
     */
    private String remark;

    private Date timeStamp;

    /**
     * 强制自费，0为不强制自费，1为强制自费
     */
    private String mustSelfPay;

    private static final long serialVersionUID = 1L;
}