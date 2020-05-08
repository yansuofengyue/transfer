package com.yicloud.trans.model.mysql;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * ord_appointment
 * @author 
 */
@Data
public class OrdAppointment implements Serializable {
    private static final long serialVersionUID = 5376473597774978265L;
    private Long id;

    /**
     * 排班编号
     */
    private Long schedulingId;

    /**
     * 号源明细ID
     */
    private Long sourceDetailId;

    /**
     * 号源编号
     */
    private String serialNumber;

    /**
     * 号源时段
     */
    private String timeState;

    /**
     * 科室编码
     */
    private Long depId;

    /**
     * 科室名称
     */
    private String depName;

    /**
     * 医生编码
     */
    private Long doctorId;

    /**
     * 医生姓名
     */
    private String doctorName;

    /**
     * 挂号类型编码
     */
    private Long binId;

    /**
     * 挂号类型名称
     */
    private String binName;

    /**
     * 本次预约在平台的唯一标识
     */
    private String appId;

    /**
     * 预约序号
     */
    private String appNo;

    /**
     * 预约方式(0平台预约，1 院内预约，2窗口挂号预约，3官网预约)
     */
    private String appWay;

    /**
     * 预约状态(0已预约 1已过期 2已取号 3患者取消 4院内取消)
     */
    private String appStatus;

    /**
     * 预约类型(0普通预约 1当日预约 )
     */
    private String appType;

    /**
     * 平台患者编码
     */
    private String patNo;

    /**
     * 院内患者ID
     */
    private Long patId;

    /**
     * 病历号
     */
    private String patCardNum;

    /**
     * 患者姓名
     */
    private String patName;

    /**
     * 患者性别
     */
    private String patSex;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 身份证号码
     */
    private String patIdentityNum;

    /**
     * 医保卡类型
     */
    private String cardType;

    /**
     * 医保卡号
     */
    private String cardNo;

    /**
     * 服务商编号
     */
    private String spNo;

    /**
     * 服务商编号
     */
    private String spName;

    /**
     * 就诊时间点
     */
    private LocalDate visitDate;

    /**
     * 医院在平台的唯一号
     */
    private Long orgId;

    /**
     * 操作员序号
     */
    private String oper;

    /**
     * 取号密码
     */
    private String password;

    /**
     * 取号代码
     */
    private String takeCode;

    /**
     * 取号时间 默认提前30分钟取号
     */
    private LocalDateTime takeTime;

    /**
     * 就诊时间
     */
    private LocalDateTime visitTime;

    private Date docTime;

    private Date payTime;

    private Date medTime;

    /**
     * 所属机构
     */
    private Long hospitalId;

    /**
     * 操作员工号
     */
    private Long sffId;

    /**
     * 操作员姓名
     */
    private String sffName;

    /**
     * 生成时间
     */
    private LocalDateTime createDate;

    /**
     * 更新状态 0未更新1已更新
     */
    private String uploadSign;

    /**
     * 更新时间
     */
    private LocalDateTime uploadDate;

    /**
     * 取消时间
     */
    private LocalDateTime cancelDate;

    /**
     * 取消预约操作员
     */
    private Long cancelSffId;

    private LocalDateTime timeStamp;

}