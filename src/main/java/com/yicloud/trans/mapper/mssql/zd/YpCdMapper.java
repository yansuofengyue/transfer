package com.yicloud.trans.mapper.mssql.zd;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yicloud.trans.model.mssql.zd.YpCd;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface YpCdMapper extends BaseMapper<YpCd> {
}