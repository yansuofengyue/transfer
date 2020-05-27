package com.yicloud.trans.model.mysql;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;

/**
 * drug_region_alias
 * @author 
 */
@Data
public class DrugRegionAlias implements Serializable {
    private static final long serialVersionUID = -3924341740802024633L;
    private Long id;

    /**
     * 药品规格序号
     */
    private Long speId;

    /**
     * 药品生厂商序号
     */
    private Long manId;

    /**
     * 药品类别编号
     */
    private String catCode;

    /**
     * 药品标准编码
     */
    private String drgCode;

    /**
     * 药品名称
     */
    private String drgName;

    /**
     * 药品编码序号(数据迁移使用)
     */
    private String centerId;

    /**
     * 药品规格
     */
    private String drgSpecification;

    /**
     * 别名序号
     */
    private String drgAliasNum;

    /**
     * 药品生产商名称
     */
    private String drgRegionName;

    /**
     * 药品产地名称
     */
    private String drgFactoryOwner;

    /**
     * 规格父类（大规格序号）
     */
    private Long drgSuperclassId;

    /**
     * 药品包装量
     */
    private Integer drgPackingNum;

    /**
     * 包装单位
     */
    private String drgPackingUnit;

    /**
     * 最小单位
     */
    private String drgMinUnit;

    /**
     * 账本类别
     */
    private Integer drgAccountCategory;

    /**
     * 药品条码
     */
    private String drgBarcode;

    /**
     * 默认给药方式
     */
    private Integer modId;

    /**
     * 服药频次英文名称
     */
    private String freId;

    /**
     * 剂型
     */
    private Integer drgFormulation;

    /**
     * 拼音码
     */
    private String chinaSpell;

    /**
     * 五笔码
     */
    private String fiveStroke;

    /**
     * 所属医疗机构
     */
    private Long hospitalId;

    /**
     * 大小规格属性      0 大规格 	1 小规格
     */
    private Integer drgSpeciProperty;

    /**
     * 基础标识(0是大规格或者是没有小规格的当前规格，1是小规格)
     */
    private Integer drgSign;

    /**
     * 药品类型
     */
    private Integer drgType;

    /**
     * 仙居医保药品上传标识
     */
    private Integer drgUploadSign;

    /**
     * 抗菌药限级
     */
    private String drgAntibiosisGrade;

    /**
     * 停用标志
     */
    private String drgState;

    /**
     * 中心产地别名序号
     */
    private Long centerRegionId;

    /**
     * 剂量
     */
    private BigDecimal drgDose;

    /**
     * 剂量单位
     */
    private String drgDoseUnit;

    /**
     * 体积
     */
    private BigDecimal drgVolume;

    /**
     * 体积单位
     */
    private String drgVolumeUnit;

    /**
     * 精神药等级
     */
    private String drgSpiritGrade;

    /**
     * 规定病种判别 0否1是
     */
    private String drgNarcotics;

    /**
     * 麻醉药判别
     */
    private String drgAnesthetic;

    /**
     * 抗生素判别
     */
    private String drgAntibiotic;

    /**
     * 是否皮药试
     */
    private String drgSkinTest;

    /**
     * 是否大输液
     */
    private String drgTransfusion;

    /**
     * 批准文号
     */
    private String drgApprovalCode;

    /**
     * 基药类型
     */
    private Integer drgBasicType;

    /**
     * 录入时间
     */
//    private LocalDateTime drgCreateDate;

    private BigDecimal drgConcentrer;

    /**
     * 折扣率
     */
    private BigDecimal drgDiscount;

    /**
     * 权重
     */
    private Integer drgWeight;
//
//    private LocalDateTime timeStamp;

}