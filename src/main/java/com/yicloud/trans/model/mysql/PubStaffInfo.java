package com.yicloud.trans.model.mysql;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * pub_staff_info
 * @author 
 */
@Data
public class PubStaffInfo implements Serializable {
    private Long id;

    /**
     * 职工姓名
     */
    private String sffName;

    /**
     * 职工性别
     */
    private String sffSex;

    /**
     * 证件号码
     */
    private String sffCardInfo;

    /**
     * 证件类型
     */
    private String sffCardType;

    /**
     * 拼音码
     */
    private String chinaSpell;

    /**
     * 五笔码
     */
    private String fiveStroke;

    /**
     * 出生年月
     */
    private Date sffBirthday;

    /**
     * 职工工号
     */
    private String sffLoginNum;

    /**
     * 默认输入法(WB：五笔，PY:拼音)
     */
    private String sffInputCode;

    /**
     * 登录密码
     */
    private String password;

    private Date timeStamp;

    private static final long serialVersionUID = 1L;
}