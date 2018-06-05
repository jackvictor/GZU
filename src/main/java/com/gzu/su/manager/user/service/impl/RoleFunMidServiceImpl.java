package com.gzu.su.manager.user.service.impl;


import com.gzu.su.manager.user.dao.RoleFunMidMapper;
import com.gzu.su.manager.user.model.RoleFunMid;
import com.gzu.su.manager.user.service.RoleFunMidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * @Class RoleFunMidServiceImpl    
 * @author mli8  
 * @date Mar 30, 20183:41:14 PM  
 * @Des 角色权限关联接口实现类
 * @since JDK 1.8
 */
@Service("roleFunMidService")
public class RoleFunMidServiceImpl implements RoleFunMidService {
	
	@Autowired
	RoleFunMidMapper roleFunMidMapper;
	
	@Override
	public List<RoleFunMid> findFunsByRoleId(String roleId) throws Exception {
		
		return roleFunMidMapper.getFunsByRoleId(roleId);
	}	

}
