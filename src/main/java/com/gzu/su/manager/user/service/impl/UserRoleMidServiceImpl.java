package com.gzu.su.manager.user.service.impl;

import com.gzu.su.manager.common.response.Constants;
import com.gzu.su.manager.common.response.PageResult;
import com.gzu.su.manager.user.dao.RoleFunMidMapper;
import com.gzu.su.manager.user.dao.UserRoleMidMapper;
import com.gzu.su.manager.user.model.RoleFunMid;
import com.gzu.su.manager.user.model.UserRoleMid;
import com.gzu.su.manager.user.model.vo.RoleForm;
import com.gzu.su.manager.user.service.UserRoleMidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 
 * @Class UserRoleMidServiceImpl    
 * @author mli8  
 * @date Mar 30, 20183:43:28 PM  
 * @Des 用户角色接口实现类
 * @since JDK 1.8
 */
@Service(value = "userRoleMidService")
public class UserRoleMidServiceImpl implements UserRoleMidService {

	@Autowired
	UserRoleMidMapper userRoleMidMapper;
	
	@Autowired
	RoleFunMidMapper roleFunMidMapper;
		
	
	@Override
	public List<UserRoleMid> findAllRole() {
		List<UserRoleMid> roles = userRoleMidMapper.findAllRoles();
		return roles;
	}

	@Override
	public PageResult<UserRoleMid> findByPage(Integer startNum, Integer size, String rName) throws Exception {
		PageResult<UserRoleMid> result = new PageResult<UserRoleMid>();
		//格式化
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		List<UserRoleMid> list = userRoleMidMapper.findByPage(startNum,size,rName);
		for (UserRoleMid userRoleMid : list) {
			userRoleMid.setCreateTime(new Date());
			userRoleMid.setUpdateTime(new Date());
		}
		result.setData(list); 
		result.setCount(userRoleMidMapper.findByPageCount(rName));
		return result;
	}

	@Override
	public UserRoleMid findRoleByPid(String pid) throws Exception {
		UserRoleMid role = userRoleMidMapper.selectByPrimaryKey(pid);
		return role;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public int insert(RoleForm roleForm) throws Exception {
		UserRoleMid role = new UserRoleMid();
		String uuId =  UUID.randomUUID().toString().replace("-","");
		//赋值
		role.setId(uuId);
		role.setRoleName(roleForm.getRoleName());
		role.setRoleDesc(roleForm.getRoleDesc());
		role.setCreateTime(new Date());
		role.setUpdateTime(new Date());
		role.setRoleStatus(roleForm.getRoleStatus());
		//插入角色表
		userRoleMidMapper.insert(role);
		List<String> list = roleForm.getCheckedFuns();
		list.add(Constants.SYS_FUN_ID);
		list.add(Constants.SYS_FUN_INDEX);
		if(!StringUtils.isEmpty(role.getId())){
			if(list!=null){
				RoleFunMid roleFunMid = new RoleFunMid();
				roleFunMid.setRoleId(role.getId());
				for (String funId : list) {		
					String uId =  UUID.randomUUID().toString().replace("-","");
					roleFunMid.setId(uId);
					roleFunMid.setFunId(funId);
					roleFunMid.setCreateTime(new Date());
					roleFunMid.setUpdateTime(new Date());
					//插入角色权限关联表
					roleFunMidMapper.insert(roleFunMid);
				}
			}
		}
		return 0;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public int updateRole(RoleForm roleForm) throws Exception {		
		if(!StringUtils.isEmpty(roleForm.getId())){
			UserRoleMid role = new UserRoleMid();
			//删除该角色原有权限记录
			List<RoleFunMid> list = roleFunMidMapper.getFunsByRoleId(roleForm.getId());
			if(list!=null){
				for (RoleFunMid roleFunMid : list) {
					roleFunMidMapper.deleteByPrimaryKey(roleFunMid.getId());
				}
			}	
			
			//更新角色表数据
			UserRoleMid userRoleMid = userRoleMidMapper.selectByPrimaryKey(roleForm.getId());
			role.setId(roleForm.getId());
			role.setRoleName(roleForm.getRoleName());
			role.setRoleDesc(roleForm.getRoleDesc());
			role.setRoleStatus(roleForm.getRoleStatus());
			role.setCreateTime(userRoleMid.getCreateTime());
			role.setUpdateTime(new Date());
			
			userRoleMidMapper.updateByPrimaryKey(role);
			
			//插入角色功能表数据
			List<String> funList = roleForm.getCheckedFuns();
			funList.add(Constants.SYS_FUN_ID);
			funList.add(Constants.SYS_FUN_INDEX);
			if(!StringUtils.isEmpty(role.getId())){
				if(list!=null){
					RoleFunMid roleFunMid = new RoleFunMid();					
					roleFunMid.setRoleId(role.getId());
					for (String funId : funList) {	
						String uuId =  UUID.randomUUID().toString().replace("-","");
						roleFunMid.setId(uuId);
						roleFunMid.setFunId(funId);
						roleFunMid.setCreateTime(new Date());
						roleFunMid.setUpdateTime(new Date());
						roleFunMidMapper.insert(roleFunMid);
					}
				}
			}
		}
		return 0;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public int deleteRole(String rId) throws Exception {
		//删除关联表
		List<RoleFunMid> list = roleFunMidMapper.getFunsByRoleId(rId);
		if(list!=null){
			for (RoleFunMid funs : list) {
				roleFunMidMapper.deleteByPrimaryKey(funs.getId());
			}
		}
		//删除角色表
		userRoleMidMapper.deleteByPrimaryKey(rId);
		
		return 0;
	}

	@Override
	public int findUserCountByRoleId(String rId) throws Exception {
		return userRoleMidMapper.findUserCountByRoleId(rId);
	}

}
