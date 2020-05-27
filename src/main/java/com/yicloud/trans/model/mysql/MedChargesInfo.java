package com.yicloud.trans.model.mysql;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * med_charges_info
 * @author 
 */
@Data
public class MedChargesInfo implements Serializable {
    /**
     * 医保交易id
     */
    private Long id;

    /**
     * 收费ID
     */
    private Long chargeId;

    /**
     * 患者信息主键id
     */
    private Long patId;

    /**
     * 医保类型
     */
    private Long feeId;

    /**
     * 险种
     */
    private String natureType;

    /**
     * 医疗机构编码
     */
    private String mechanismCode;

    /**
     * 个人编号
     */
    private String personalNo;

    /**
     * 医院端交易唯一号
     */
    private String hisSerialNo;

    /**
     * 医保交易流水号
     */
    private String medSerialNo;

    /**
     * 就诊流水号
     */
    private String visitSerialNo;

    /**
     * 医保结算流水号
     */
    private String settlementNo;

    /**
     * 医保业务周期号
     */
    private String businessCycleNumber;

    /**
     * 结算时间
     */
    private Date tradeTime;

    /**
     * 结算类型（1挂号 2门诊）
     */
    private String tradeType;

    /**
     * 交易状态 -1退费  1正交易
     */
    private String tradeStatus;

    /**
     * 医疗人员类别(杭州市，省，市一卡通)
     */
    private String treatmentCategory;

    /**
     * 总金额
     */
    private BigDecimal total;

    /**
     * 本年账户余额
     */
    private BigDecimal currentYearBalance;

    /**
     * 本年账户支付
     */
    private BigDecimal currentYearPay;

    /**
     * 历年账户余额
     */
    private BigDecimal calendarYearBalance;

    /**
     * 历年账户支付
     */
    private BigDecimal calendarYearPay;

    /**
     * 医保基金支付(市医保) 医保列支费用(省医保)
     */
    private BigDecimal fundPay;

    /**
     * 本次现金支付
     */
    private BigDecimal cashPay;

    /**
     * 自理金额
     */
    private BigDecimal selfCarePay;

    /**
     * 自费金额
     */
    private BigDecimal selfPay;

    /**
     * 超限价自费
     */
    private BigDecimal overrunPay;

    /**
     * 统筹基金支出
     */
    private BigDecimal collectiveFundPay;

    /**
     * 重病基金支出(市医保) 大病保险基金支付(省医保)
     */
    private BigDecimal serDisFundPay;

    /**
     * 起付标准自付
     */
    private BigDecimal selStaPay;

    /**
     * 其他基金支出
     */
    private BigDecimal otherFundPay;

    /**
     *  自付支付
     */
    private BigDecimal selfConceitPay;

    /**
     * 合计支付(省医保)
     */
    private BigDecimal totalPayment;

    /**
     * 公务员补助（省医保）
     */
    private BigDecimal civilServantPay;

    /**
     * 离休基金(省医保)
     */
    private BigDecimal retirePay;

    /**
     * 亲属个账历年支付(省医保)
     */
    private BigDecimal clanYearPay;

    /**
     * 门诊统筹起付线累计(省医保)
     */
    private BigDecimal accumulationPay;

    /**
     * His 端必须将此信息显示到前台
     */
    private String notice;

    /**
     * 结算信息详情
     */
    private String chargeDetailInfo;

    /**
     * 结算交易完成确认标志 0 未确认 1已确认
     */
    private String confirmMark;

    /**
     * 冲销标志 0 正常 1 作废
     */
    private String offSign;

    /**
     * 规定病种(1:是   0：不是)
     */
    private String prescribedDisease;

    /**
     * 医院编号
     */
    private Long hospitalId;

    /**
     * 医疗类别
     */
    private String medicalCategory;

    private Date timeStamp;

    private static final long serialVersionUID = 1L;
}