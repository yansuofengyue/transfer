package com.yicloud.trans.model.mysql;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import lombok.Data;

/**
 * ord_source_detail
 * @author 
 */
@Data
public class OrdSourceDetail implements Serializable {
    private static final long serialVersionUID = 7991001346081032825L;
    private Long id;

    /**
     * 排班ID
     */
    private Long schedulingId;

    /**
     * 排班日期
     */
    private LocalDate schedulingDate;

    /**
     * 号源明细类型 0院内号源和1第三方号源(平台、官网)
     */
    private String type;

    /**
     * 就诊时间点
     */
    private LocalTime visitTime;

    /**
     * 就诊时间段 上下晚
     */
    private String timeState;

    /**
     * 号源序号
     */
    private String serialNumber;

    /**
     * 科室ID
     */
    private Long depId;

    private String depName;

    /**
     * 医生ID
     */
    private Long doctorId;

    private String doctorName;

    /**
     * 挂号类型
     */
    private Long binId;

    private String binName;

    /**
     * 使用状态(1窗口挂号 2平台预约取号 3平台爽约 4院内预约取号 5院内爽约 6退号)
     */
    private String state;

    /**
     * 号源状态(0未使用  已使用1)
     */
    private String status;

    private Date timeStamp;

}