package com.yicloud.trans.mapper.mssql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yicloud.trans.model.mssql.Mzyskfk;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author chen
 */
@Component
@Mapper
public interface MzyskfkMapper extends BaseMapper<Mzyskfk> {
}