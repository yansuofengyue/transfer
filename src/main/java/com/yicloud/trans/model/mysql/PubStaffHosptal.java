package com.yicloud.trans.model.mysql;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * pub_staff_hosptal
 * @author 
 */
@Data
public class PubStaffHosptal implements Serializable {
    private Long id;

    private Long sffId;

    /**
     * 医保上传医生ID
     */
    private Long uploadSffId;

    /**
     * 所属科室
     */
    private Long depId;

    /**
     * 专业类别
     */
    private String sffSpeciality;

    /**
     * 是否需要模糊查询
     */
    private String sffDuty;

    /**
     * 医生职称
     */
    private String sffProfessional;

    /**
     * 参加工作时间
     */
    private Date sffWorkingTimes;

    /**
     * 医生状态(0-有效 1-无效)(预约挂号用)
     */
    private String sffState;

    /**
     * 所属医院
     */
    private Long hospitalId;

    /**
     * 行政职务
     */
    private String sffAdministrativeRank;

    /**
     * 医师执业证书编码
     */
    private String sffPracticeCode;

    /**
     * 医师级别
     */
    private String sffDocGrade;

    /**
     * 执业范围
     */
    private String sffPracticeScope;

    /**
     * 执业科别
     */
    private String sffPracticeDivision;

    /**
     * 医师资格证书编码
     */
    private String sffQualificationCode;

    /**
     * 备注
     */
    private String sffSummary;

    /**
     * 医生权限
     */
    private Long roleId;

    private String sffType;

    /**
     * 接口中要求上传操作员账号的交易，操作员账号域必须传在医保中心统一平台上注册且审批通过后分配的统一账号，不可以是医院自己的收费员账号
     */
    private String sffAccount;

    private Date timeStamp;

    /**
     * 医生处方权标志 0无处方权 1有处方权
     */
    private String sffRecipeRight;

    /**
     * 医生擅长信息
     */
    private String goodat;

    /**
     * 预约挂号--医生上传状态 0未上传1已上传
     */
    private String uploadType;

    private static final long serialVersionUID = 1L;
}