package com.yicloud.trans.model.mysql;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * pub_patients
 * @author 
 */
@Data
@TableName("pub_patients")
public class Patients implements Serializable {
    private static final long serialVersionUID = 867563847109895784L;
    private Long id;
    /**
     * 病人证号
     */
    private String patCardNum;

    /**
     * 姓名
     */
    private String patName;

    /**
     * 性别(1男2女9未知)
     */
    private String patSex;

    /**
     * 出生年月
     */
    private LocalDate patBirthday;

    /**
     * 费用类别
     */
    private Long feeId;

    private String feeName;

    /**
     * 病人性质
     */
    private Long natureId;

    private String natureName;

    /**
     * (仙居险种)(杭州市医保1病历本0市民卡4电子社保卡)(省医保1读卡)
     */
    private String natureType;

    /**
     * 身份证号
     */
    private String patIdentityNum;

    /**
     * 证件类型
     */
    private String patCertifiType;

    /**
     * 证件号码
     */
    private String patCertifiNum;

    /**
     * 家庭住址
     */
    private String patFamAddress;

    /**
     * 家庭邮编
     */
    private String patPostcode;

    /**
     * 联系人电话
     */
    private String patContactPhone;

    /**
     * 联系人
     */
    private String patContacts;

    /**
     * 联系电话
     */
    private String patPhone;

    /**
     * 就职单位名称
     */
    private String patWorkUnit;

    /**
     * 职业
     */
    private String patOperation;

    /**
     * 国籍
     */
    private String patCountry;

    /**
     * 民族
     */
    private String patNationality;

    /**
     * 婚姻状况
     */
    private String patMatrimony;

    /**
     * 既往史
     */
    private String patHisPrevious;

    /**
     * 过敏史
     */
    private String patHisAllergic;

    /**
     * 家族史
     */
    private String patHisFamily;

    /**
     * 建档日期
     */
    private Date patRecordDate;

    /**
     * 黑名单1，白名单0
     */
    private String patMemGrade;

    /**
     * 预约平台唯一标识
     */
    private Long platfromId;

    /**
     * 个人编号
     */
    private String personalNo;

    /**
     * 医保卡号
     */
    private String medicareCard;

    /**
     * 特殊病种标志(省，市)（0不是2是）
     */
    private String specialDiseaseSign;

    /**
     * 规定病种编码
     */
    private String stipulateDiseaseCode;

    /**
     * 规定病种名称
     */
    private String stipulateDiseaseName;

    private String stipulateDiseaseRecordNo;

    /**
     * 获取医保人员信息出参
     */
    private String medPatOut;

    /**
     * 医保人员社会保障卡信息
     */
    private String medCardInfo;



    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 报告单（1需要未带，2需要已带，3不需要）
     */
    private String paperState;

    /**
     * 本年账户余额
     */
    private BigDecimal currentYearBalance;

    /**
     * 历年账户余额
     */
    private BigDecimal calendarYearBalance;

    /**
     * 黑名单原因
     */
    private String blacklistReasons;

}