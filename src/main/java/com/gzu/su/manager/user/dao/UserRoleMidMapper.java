package com.gzu.su.manager.user.dao;

import com.gzu.su.manager.user.model.UserRoleMid;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface    UserRoleMidMapper {
    /**
     * 根据角色id删除角色
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 添加角色
     *
     * @param record
     * @return
     */
    int insert(UserRoleMid record);

    /**
     * 判断字段添加角色
     *
     * @param record
     * @return
     */
    int insertSelective(UserRoleMid record);

    /**
     * 根据id查询角色
     *
     * @param id
     * @return
     */
    UserRoleMid selectByPrimaryKey(String id);

    /**
     * 选择性更新角色
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(UserRoleMid record);

    /**
     * 更新角色
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(UserRoleMid record);

    /**
     * 查询所有角色
     *
     * @return
     */
    List<UserRoleMid> findAllRoles();

    /**
     * 分页查询
     *
     * @param startNum
     * @param size
     * @param rName
     * @return
     * @throws Exception
     */
    List<UserRoleMid> findByPage(@Param("startNum")Integer startNum, @Param("size")Integer size, @Param("rName")String rName) throws Exception;

    /**
     * 获取页面总数
     *
     * @param rName
     * @return
     * @throws Exception
     */
    Integer findByPageCount(@Param("rName")String rName) throws Exception;

    /**
     * 获取拥有该角色ID的用户个数
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    Integer findUserCountByRoleId(@Param("rId")String roleId) throws Exception;
}