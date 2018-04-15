package com.gzu.su.manager.user.dao;

import com.gzu.su.manager.user.model.UserRoleMid;

public interface UserRoleMidMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserRoleMid record);

    int insertSelective(UserRoleMid record);

    UserRoleMid selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserRoleMid record);

    int updateByPrimaryKey(UserRoleMid record);
}