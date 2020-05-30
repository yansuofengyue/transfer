package com.yicloud.trans.model.mssql.zd;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

@Data
public class Jbk implements Serializable {
   private String jbh;
   private String jbm;
   @TableField("icd10")
   private String icd10;

}
