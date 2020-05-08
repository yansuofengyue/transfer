package com.yicloud.trans.mapper.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yicloud.trans.model.mysql.OrdSourceDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface OrdSourceDetailMapper extends BaseMapper<OrdSourceDetail> {

}