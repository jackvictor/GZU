package com.gzu.su.manager.user.service;



import com.gzu.su.manager.common.response.PageResult;
import com.gzu.su.manager.user.model.UserRoleMid;
import com.gzu.su.manager.user.model.vo.RoleForm;

import java.util.List;

/**
 *
 * @Class UserRoleMidService
 * @author luoxia
 * @date Mar 30, 201810:28:09 AM
 * @Des 用户角色表Service
 * @since JDK 1.8
 */
public interface UserRoleMidService {

	/**
	 *
	 * @return  角色集合
	 * @returnType List<UserRoleMid>
	 * @author luoxia
	 * @date Mar 30, 201810:28:28 AM
	 * @Des 获取所有角色信息集合
	 */
	public List<UserRoleMid> findAllRole();

	/**
	 *
	 * @param startNum 起始页
	 * @param size 个数
	 * @throws Exception
	 * @returnType PageResult<UserRoleMid>
	 * @author luoxia
	 * @date Mar 30, 201810:28:54 AM
	 * @Des 分页查询
	 */
	public PageResult<UserRoleMid> findByPage(Integer startNum, Integer size, String rName) throws Exception;

	/**
	 *
	 * @param pid 主键ID
	 * @return UserRoleMid 角色对象
	 * @throws Exception
	 * @author luoxia
	 * @date Mar 30, 201810:29:41 AM
	 * @Des 通过主键ID获取角色对象
	 */
	public UserRoleMid findRoleByPid(String pid) throws Exception;

	/**
	 *
	 * @param role 用户角色对象
	 * @throws Exception
	 * @author luoxia
	 * @date Mar 30, 201810:30:19 AM
	 * @Des 新增角色信息
	 */
	public int insert(RoleForm role) throws Exception;

	/**
	 *
	 * @param role 角色对象
	 * @throws Exception
	 * @author luoxia
	 * @date Mar 30, 201810:31:03 AM
	 * @Des 修改角色信息
	 */
	public int updateRole(RoleForm role) throws Exception;

	/**
	 *
	 * @param rId 角色主键ID
	 * @throws Exception
	 * @author luoxia
	 * @date Mar 30, 201810:31:51 AM
	 * @Des 删除角色
	 */
	public int deleteRole(String rId) throws Exception;

	/**
	 *
	 * @param rId 角色ID
	 * @return int user个数
	 * @throws Exception
	 * @author luoxia
	 * @date Mar 30, 20183:52:20 PM
	 * @Des 获取拥有该角色ID的用户个数
	 */
	public int findUserCountByRoleId(String rId) throws Exception;
}
