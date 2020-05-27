package com.yicloud.trans.model.mysql;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * cli_visited_info
 * @author 
 */
@Data
@TableName("cli_visited_info")
public class VisitedInfo implements Serializable {
    /**
     * 就诊序号
     */
    private Long id;

    /**
     * 就诊医生
     */
    private Long sffId;

    /**
     * 转方医生ID
     */
    private Long turnSffId;

    /**
     * 就诊日期
     */
    private Date visDate;

    /**
     * 病人证号
     */
    private String patCardNum;

    /**
     * 就诊病人序号
     */
    private Long patId;

    /**
     * 就诊病人姓名
     */
    private String patName;

    /**
     * 费用类别
     */
    private Long feeId;

    /**
     * 费用性质
     */
    private Long natId;

    /**
     * 就诊人性别
     */
    private Integer patSex;

    /**
     * 年龄
     */
    private Integer patAge;

    /**
     * 家庭住址或者单位
     */
    private String patAddress;

    /**
     * 联系电话
     */
    private String patContacts;

    /**
     * 主诉
     */
    private String visSymptom;

    /**
     * 现病史
     */
    private String visPresentHis;

    /**
     * 既往史
     */
    private String visPreviousHis;

    /**
     * 家族史
     */
    private String visFamilyHis;

    /**
     * 收缩压
     */
    private Integer visSystolic;

    /**
     * 舒张压
     */
    private Integer visDiastole;

    /**
     * 诊断疾病(作废20190814)
     */
    private Long disId;

    /**
     * 就诊科室
     */
    private Long depId;

    /**
     * 病人体温
     */
    private BigDecimal visTemperature;

    /**
     * 身高
     */
    private BigDecimal visHeight;

    /**
     * 体重
     */
    private BigDecimal visWeight;

    /**
     * 脉搏
     */
    private Integer visSphygmus;

    /**
     * 心率
     */
    private Integer visHeartRate;

    /**
     * 其他
     */
    private String visBloodGlucose;

    /**
     * 腰围
     */
    private BigDecimal visWaistline;

    /**
     * 就诊机构
     */
    private Long hospitalId;

    /**
     * 初复诊标识(0初诊1复诊)
     */
    private String visFirstSign;

    /**
     * 预约登记序号(挂号)
     */
    private Long orderId;

    /**
     * 0诊间1方便门诊2处方录入
     */
    private String type;

    /**
     * 就诊结束标志
     */
    private Integer visOff;

    /**
     * 结算主表ID集
     */
    private String chargeInfo;

    private Date timeStamp;

    private static final long serialVersionUID = 1L;
}