package com.gzu.su.manager.user.service.impl;

import com.gzu.su.manager.common.response.Constants;
import com.gzu.su.manager.user.dao.RoleFunMidMapper;
import com.gzu.su.manager.user.dao.SysFunMapper;
import com.gzu.su.manager.user.model.RoleFunMid;
import com.gzu.su.manager.user.model.SysFun;
import com.gzu.su.manager.user.service.SysFunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @Class SysFunServiceImpl    
 * @author mli8  
 * @date Mar 30, 20183:41:35 PM  
 * @Des 系统功能接口实现类
 * @since JDK 1.8
 */
@Service(value = "sysFunService")
public class SysFunServiceImpl implements SysFunService {
	
	@Autowired
	SysFunMapper sysFunMapper;
	
	@Autowired
	RoleFunMidMapper roleFunMidMapper;

	@Override
	public List<SysFun> findAll(String rId) throws Exception {
		List<SysFun> sysFuns = sysFunMapper.findAllFuns();
		for (SysFun sysFun : sysFuns) {
			sysFun.setCheck(false);
		}
		if(!StringUtils.isEmpty(rId)){
			List<RoleFunMid> rfm = roleFunMidMapper.getFunsByRoleId(rId);
			
			List<String> rfms = new ArrayList<String>();
			for (RoleFunMid roleFunMid : rfm) {
				rfms.add(roleFunMid.getFunId());
			}
			
			for (SysFun sysFun : sysFuns) {
				if(rfms.contains(sysFun.getId())){
					sysFun.setCheck(true);
				}
			}
		}
		return sysFuns;
	}

	@Override
	public List<SysFun> findByRoleId(String roleId) throws Exception {
		return sysFunMapper.findByRoleId(roleId, Constants.SYS_FUN_ID);
	}

}
