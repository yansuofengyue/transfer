package com.yicloud.trans.mapper.mssql.zd;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yicloud.trans.model.mssql.zd.Gfjb2Ybbr;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
@DS("slave")
public interface Gfjb2YbbrMapper extends BaseMapper<Gfjb2Ybbr> {
}