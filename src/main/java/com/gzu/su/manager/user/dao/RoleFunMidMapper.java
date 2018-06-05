package com.gzu.su.manager.user.dao;

import com.gzu.su.manager.user.model.RoleFunMid;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleFunMidMapper {
    int deleteByPrimaryKey(String id);

    int insert(RoleFunMid record);

    int insertSelective(RoleFunMid record);

    RoleFunMid selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RoleFunMid record);

    int updateByPrimaryKey(RoleFunMid record);

    /**
     *
     * @param roleId 角色ID
     * @return  角色功能关联信息List
     * @returnType List<RoleFunMid>
     * @author luoxia
     * @date Mar 30, 201810:19:06 AM
     * @Des 获取角色功能关联集合
     */
    List<RoleFunMid> getFunsByRoleId(@Param("rId")String roleId);
}