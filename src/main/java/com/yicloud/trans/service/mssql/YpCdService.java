package com.yicloud.trans.service.mssql;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yicloud.trans.controller.PatientsController;
import com.yicloud.trans.model.mssql.Jbxxk;
import com.yicloud.trans.model.mssql.zd.Gfjb2Ybbr;
import com.yicloud.trans.model.mssql.zd.YpCd;
import com.yicloud.trans.model.mysql.Patients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/4/22 13:35
 * @FileName: YpCdService
 * @Description: 老系统药品产地信息表接口
 */
public interface YpCdService extends IService<YpCd> {

}
