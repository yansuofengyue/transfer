package com.yicloud.trans.mapper.mssql;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yicloud.trans.model.mssql.Jbxxk;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
@DS("slave")
public interface JbxxkMapper extends BaseMapper<Jbxxk> {

}