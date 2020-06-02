package com.yicloud.trans.model.mysql;

import java.io.Serializable;
import lombok.Data;

/**
 * mid_drug
 * @author 
 */
@Data
public class MidDrug implements Serializable {
    private Long id;

    /**
     * 新系统药品ID
     */
    private Long drugRegionId;

    /**
     * 新系统药品名称
     */
    private String drugRegionName;

    /**
     * 新系统药规格信息
     */
    private String drugRegionSpecification;

    private Long speId;

    private Long manId;

    /**
     * 老系统药品号
     */
    private String yph;

    /**
     * 老系统药品序号
     */
    private String ggxh;

    /**
     * 老系统产地ID
     */
    private Integer cdId;

    /**
     * 老系统药品名称
     */
    private String ypmc;

    /**
     * 老系统规格信息
     */
    private String ypgg;


    private static final long serialVersionUID = 1L;
}