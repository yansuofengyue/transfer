package com.yicloud.trans.mapper.mssql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yicloud.trans.model.mssql.BazdkRegister;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface BazdkRegisterMapper extends BaseMapper<BazdkRegister> {
}