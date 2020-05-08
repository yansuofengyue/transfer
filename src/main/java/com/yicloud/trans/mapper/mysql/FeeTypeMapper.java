package com.yicloud.trans.mapper.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yicloud.trans.model.mysql.FeeType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface FeeTypeMapper extends BaseMapper<FeeType> {

}