package com.gzu.su.manager.user.service;

import com.gzu.su.manager.user.model.SysFun;
import java.util.List;

/**
 *
 * @Class SysFunService
 * @author mli8
 * @date Mar 30, 20183:40:36 PM
 * @Des 系统功能接口
 * @since JDK 1.8
 */
public interface SysFunService {

	/**
	 *
	 * @param roleId 角色id
	 * @return List<SysFun>
	 * @author mli8
	 * @date Mar 30, 20183:38:24 PM
	 * @Des 根据角色id查询所有权限集合
	 */
	public List<SysFun> findAll(String roleId) throws Exception;

	/**
	 *
	 * @param roleId 角色id
	 * @return List<SysFun>
	 * @author mli8
	 * @date Mar 30, 20183:39:06 PM
	 * @Des 根据角色id查询该角色权限集合
	 */
	public List<SysFun> findByRoleId(String roleId) throws Exception;
}
