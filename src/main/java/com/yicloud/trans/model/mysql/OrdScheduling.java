package com.yicloud.trans.model.mysql;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * ord_scheduling
 * @author 
 */
@Data
public class OrdScheduling implements Serializable {
    private static final long serialVersionUID = -4062128257433151250L;
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 就诊机构 医院 pub_hospital
     */
    private Long hospitalId;

    /**
     * 所属科室 pub_departments
     */
    private Long depId;

    /**
     * 科室名称
     */
    private String depName;

    /**
     * 挂号类型编号
     */
    private Long binId;

    /**
     * 挂号类型名称
     */
    private String binName;

    /**
     * 医生唯一号 pub_staff_info
     */
    private Long doctorId;

    /**
     * 医生姓名
     */
    private String doctorName;

    /**
     * 星期几 周日1 依次为 2 3 4 5 6 7
     */
    private String week;

    /**
     * 排班时段 早中晚 1 2 3
     */
    private String timeState;

    /**
     * 排班时间（安排医生在那天看病）
     */
    private Date schedulingTime;

    /**
     * 门诊开始时间
     */
    private Date startTime;

    /**
     * 门诊结束时间
     */
    private Date endTime;

    /**
     * 开放第三方起始号源
     */
    private Integer appointmentAmount;

    /**
     * 开放第三方预约数量
     */
    private Integer appointmentBegin;

    /**
     * 递增数 默认0
     */
    private Integer increase;

    /**
     * 号源总数
     */
    private Integer amount;

    /**
     * 退号数量
     */
    private Integer amountReduce;

    /**
     * 爽约数量
     */
    private Integer amountCancel;

    /**
     * 加号数量
     */
    private Integer amountAdd;

    /**
     * 已挂号数量
     */
    private Integer amountUse;

    /**
     * 剩余号源
     */
    private Integer amountSurplus;

    /**
     * 当前号
     */
    private Integer amountCurrent;

    /**
     * 就诊地址
     */
    private String clinicAddress;

    /**
     * 排班状态  0-正常 1-停诊 2结束
     */
    private String state;

    /**
     * 1注销0正常
     */
    private String status;

    /**
     * 停诊原因
     */
    private String stopServiceReason;

    private Date timeStamp;

    /**
     * 平均就诊时间
     */
    private Integer averageVisitTime;

    /**
     * 模板ID
     */
    private Long templateId;

}