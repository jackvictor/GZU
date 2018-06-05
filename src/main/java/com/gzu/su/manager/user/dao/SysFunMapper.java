package com.gzu.su.manager.user.dao;

import com.gzu.su.manager.user.model.SysFun;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysFunMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysFun record);

    int insertSelective(SysFun record);

    SysFun selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysFun record);

    int updateByPrimaryKey(SysFun record);

    /**
     *
     * @return List<SysFun> 系统功能集合
     * @author mli8
     * @date Mar 30, 20183:34:12 PM
     * @Des 查询所有系统功能
     */
    List<SysFun> findAllFuns();

    /**
     *
     * @param roleId 角色id
     * @param pId 根节点id
     * @return List<SysFun> 系统功能集合
     * @author mli8
     * @date Mar 30, 20183:34:47 PM
     * @Des 根据角色id、根节点id查询该角色权限
     */
    List<SysFun> findByRoleId(@Param("roleId")String roleId, @Param("pId")String pId);
}