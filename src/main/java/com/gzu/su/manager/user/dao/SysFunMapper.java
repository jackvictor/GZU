package com.gzu.su.manager.user.dao;

import com.gzu.su.manager.user.model.SysFun;

public interface SysFunMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysFun record);

    int insertSelective(SysFun record);

    SysFun selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysFun record);

    int updateByPrimaryKey(SysFun record);
}