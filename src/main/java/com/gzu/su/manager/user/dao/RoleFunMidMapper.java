package com.gzu.su.manager.user.dao;

import com.gzu.su.manager.user.model.RoleFunMid;

public interface RoleFunMidMapper {
    int deleteByPrimaryKey(String id);

    int insert(RoleFunMid record);

    int insertSelective(RoleFunMid record);

    RoleFunMid selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RoleFunMid record);

    int updateByPrimaryKey(RoleFunMid record);
}