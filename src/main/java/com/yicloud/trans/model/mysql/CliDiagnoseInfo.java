package com.yicloud.trans.model.mysql;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * cli_diagnose_info
 * @author 
 */
@Data
public class CliDiagnoseInfo implements Serializable {
    private Long id;

    /**
     * 就诊序号
     */
    private Long visId;

    /**
     * 疾病序号
     */
    private Long disId;

    /**
     * ICD
     */
    private String diaIcd;

    /**
     * 诊断名称
     */
    private String diaName;

    /**
     * 诊断说明
     */
    private String diaSummary;

    /**
     * 0西医诊断1中医诊断2中医证型
     */
    private String diaType;

    /**
     * 诊断判别（确诊，疑诊）
     */
    private String diaSign;

    /**
     * 所属机构
     */
    private Long hospitalId;

    /**
     * 发病时间
     */
    private Date diaTime;

    /**
     * 标识
     */
    private String mainFlag;

    private Date timeStamp;

    private static final long serialVersionUID = 1L;
}