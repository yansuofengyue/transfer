package com.yicloud.trans.mapper.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yicloud.trans.model.mysql.MidDrug;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface MidDrugMapper extends BaseMapper<MidDrug> {

}