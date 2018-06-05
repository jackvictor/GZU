package com.gzu.su.manager.user.service;

import com.gzu.su.manager.user.model.RoleFunMid;
import java.util.List;

/**
 * 角色权限关联Service
 *
 * @author jack.ye
 */
public interface RoleFunMidService {

	/**
	 * 通过角色ID获取角色权限对象集合
	 *
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<RoleFunMid> findFunsByRoleId(String roleId) throws Exception;
	
}
