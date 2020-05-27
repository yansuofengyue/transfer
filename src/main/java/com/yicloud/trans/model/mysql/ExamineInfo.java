package com.yicloud.trans.model.mysql;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * cli_examine_info
 * @author 
 */
@Data
@TableName("cli_examine_info")
public class ExamineInfo implements Serializable {
    private Long id;

    /**
     * 病人序号
     */
    private Long patId;

    /**
     * 就诊序号
     */
    private Long visId;

    /**
     * 收费序号
     */
    private Long chargesId;

    /**
     * 病人证号
     */
    private String patCardNum;

    /**
     * 病人姓名
     */
    private String patName;

    /**
     * 检查l流水号码（内部定义）
     */
    private String exaSerialNum;

    /**
     * 退费状态
     */
    private Integer exaUpdateSign;

    /**
     * 单据状态(1.退费)
     */
    private Integer exaState;

    /**
     * 原开单序号
     */
    private Long examineId;

    /**
     * 项目单金额
     */
    private BigDecimal exaFee;

    /**
     * 开单时间
     */
    private Date exaDate;

    /**
     * 开单科室
     */
    private Long depId;

    /**
     * 开单医生
     */
    private Long sffId;

    /**
     * 送检状态
     */
    private String exaInspectState;

    /**
     * 单据类型（0.检查单，1.检验，2.普通项目，3.附加费,4针灸）
     */
    private Integer exaType;

    /**
     * 所属机构
     */
    private Long hospitalId;

    /**
     * 检查时间
     */
    private Date examineExaDate;

    /**
     * 检查摘要
     */
    private String examineSummary;

    /**
     * 病人性质
     */
    private Long natId;

    /**
     * 费用类别
     */
    private Long feeId;

    /**
     * 规定病种标识 0普通1规定病种
     */
    private String remark;

    private Date timeStamp;

    private static final long serialVersionUID = 1L;
}