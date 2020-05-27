package com.yicloud.trans.mapper.mysql;

import com.yicloud.trans.model.mysql.PubStaffHosptal;

public interface PubStaffHosptalMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PubStaffHosptal record);

    int insertSelective(PubStaffHosptal record);

    PubStaffHosptal selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PubStaffHosptal record);

    int updateByPrimaryKey(PubStaffHosptal record);
}