package com.yicloud.trans.model.mysql;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * med_charges_info
 * @author 
 */
@Data
@TableName("chg_charges_info")
public class ChargesInfo implements Serializable {
    /**
     * 收费ID
     */
    @TableId
    private Long id;

    /**
     * 收费日期
     */
    private Date billDate;

    /**
     * 患者ID
     */
    private Long patId;

    /**
     * 病人姓名
     */
    private String patName;

    /**
     * 病人性质
     */
    private Long natureId;

    /**
     * 性质名称
     */
    private String natureName;

    /**
     * 费用类别
     */
    private Long feeId;

    /**
     * 费用类别名称
     */
    private String feeName;

    /**
     * 病人证号
     */
    private String patCardNum;

    /**
     * 费用总金额
     */
    private BigDecimal billTotalFee;

    /**
     * 收现金
     */
    private BigDecimal billCashFee;

    /**
     * 找现金
     */
    private BigDecimal billOddFee;

    /**
     * 实收金额
     */
    private BigDecimal billSelfFee;

    /**
     * 优惠金额
     */
    private BigDecimal billFavorFee;

    /**
     * 病种代码
     */
    private Long disId;

    /**
     * 操作工号
     */
    private Long sffId;

    /**
     * 操作源姓名
     */
    private String sffName;

    /**
     * 日报序号
     */
    private Long dayId;

    /**
     * 日报日期
     */
    private Date dayDate;

    /**
     * 作废工号
     */
    private Long sffRefundId;

    /**
     * 作废操作员姓名
     */
    private String sffRefundName;

    /**
     * 原收费序号
     */
    private Long billOldId;

    /**
     * 重打判别
     */
    private String billThump;

    /**
     * 退费判别 0正常 1退费
     */
    private String billRefundSign;

    /**
     * 发票打印信息
     */
    private String billPrintInfo;

    /**
     * 冲销标志
     */
    private String billOffSign;

    /**
     * 收费类型 0挂号 1收费
     */
    private String billType;

    /**
     * 所属医疗机构
     */
    private Long hospitalId;

    /**
     * 就诊病人id
     */
    private Long visitId;

    /**
     * 就诊流水号
     */
    private String visitSerialNo;

    /**
     * 医保交易流水号
     */
    private String centerSerialNo;

    /**
     * 医保业务周期号
     */
    private String businessCycleNumber;

    /**
     * 开单医生工号
     */
    private Long doctorSffId;

    /**
     * 支付方式 0现金 2支付宝 1微信,11微医支付,12微脉
     */
    private String paymentType;

    /**
     * 时间戳
     */
//    private Date timeStamp;

    /**
     * 数据源
     */
    private String dataSource;

    /**
     * 特殊病种标志(省，市)（0不是2是）
     */
    private String specialDiseaseSign;

    /**
     * 支付状态，现金支付默认为成功，电子支付0为初始，1为成功，2为退费
     */
    private String payState;
}