package com.yicloud.trans.service.mysql;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yicloud.trans.model.mssql.BazdkRegister;
import com.yicloud.trans.model.mssql.zd.Ysk;
import com.yicloud.trans.model.mysql.OrdAppointment;

import java.text.ParseException;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/7 11:13
 * @FileName: OrdAppointmentService
 * @Description: 新系统预约记录表
 */
public interface OrdAppointmentService extends IService<OrdAppointment> {
    /**
     * @TODO: 2020/5/7
     * 老系统预约数据转移到新系统
     * @param bazdkRegister 老系统预约记录
     * @return
     */
    void transferOrdApp(BazdkRegister bazdkRegister, Ysk ysk) throws ParseException;
}
