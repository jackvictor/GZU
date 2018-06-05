package com.gzu.su.manager.user.controller;

import com.gzu.su.manager.common.response.MapResult;
import com.gzu.su.manager.common.response.PageResult;
import com.gzu.su.manager.user.model.SysFun;
import com.gzu.su.manager.user.model.UserRoleMid;
import com.gzu.su.manager.user.model.vo.RoleForm;
import com.gzu.su.manager.user.service.SysFunService;
import com.gzu.su.manager.user.service.UserRoleMidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 
 * @Class RoleController    
 * @author luo,xiao 
 * @date Mar 28, 2018 13:06:51 PM  
 * @Des 角色管理
 * @since JDK 1.8
 */
@Controller
@RequestMapping(value="/role")
public class RoleController {
	private static Logger log = LoggerFactory.getLogger(RoleController.class);
	@Autowired
	private UserRoleMidService userRoleMidService;
	
	@Autowired
	private SysFunService sysFunService;
	
	
	@RequestMapping(value = "/rlist")
	public String rList(){
		return "user/role_list";
	}
	
	/**
	 *   
	 * @param page 页数
	 * @param limit 个数
	 * @param searchName 角色名字
	 * @return  PageResult<UserRoleMid> 
	 * @author luoxia  
	 * @date Mar 30, 20189:49:48 AM  
	 * @Des 角色列表展示
	 */
	@ResponseBody
    @RequestMapping(value = "/rpage")
    public PageResult<UserRoleMid> rPage(Integer page, Integer limit, String searchName){
        try {
        	Integer startNum = 0;
        	Integer size = limit;
        	if(page > 0){
        		startNum = (page - 1) * size;
        	}
			return userRoleMidService.findByPage(startNum,size,searchName);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
        return null;
    }
	
	/**
	 * 
	 * @param model
	 * @param roleId 主键ID
	 * @param sign 新增 OR 修改
	 * @return String 角色信息RUL
	 * @author luoxia  
	 * @date Mar 30, 201810:48:33 AM  
	 * @Des 页面跳转
	 */
	@RequestMapping(value = "/info")
    public String info(Model model,String roleId,String sign){
		try {
			UserRoleMid role = new UserRoleMid();
			if(!StringUtils.isEmpty(roleId) && !StringUtils.isEmpty(sign)){
				role = userRoleMidService.findRoleByPid(roleId);
			}
			List<SysFun> funs = sysFunService.findAll("");
			model.addAttribute("funs",funs);
			model.addAttribute("role", role);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return "user/role_info";
    }
	
	/**
	 * 
	 * @param roleId 角色ID
	 * @return  List<SysFun> 角色功能集合
	 * @author luoxia  
	 * @date Mar 30, 201810:51:57 AM  
	 * @Des 角色ID获取角色功能集合
	 */
	@ResponseBody
	@RequestMapping(value = "/getAllFun")
    public List<SysFun> getFuns(String roleId){
		try {
			return  sysFunService.findAll(roleId);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return null;
    }	
	
	/**
	 * 
	 * @param role 角色对象
	 * @author luoxia  
	 * @date Mar 30, 201810:58:24 AM  
	 * @Des 保存角色信息
	 */
	@ResponseBody
	@RequestMapping(value="/save")
	public MapResult save(@RequestBody RoleForm role){
		try {
			if(StringUtils.isEmpty(role.getId())){
				userRoleMidService.insert(role);
				return MapResult.ok(200, "保存成功");
			}else{
				int count = userRoleMidService.findUserCountByRoleId(role.getId());
				if(role.getRoleStatus().equals("1") && count > 0){
					return MapResult.error(-1,"该角色已被使用");
				}
				userRoleMidService.updateRole(role);
				return MapResult.ok(200, "更新成功");
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			return MapResult.error(-1, "保存失败");
		}		
	}
	
	/**
	 * 
	 * @param rId 主键ID
	 * @return  
	 * @returnType MapResult  
	 * @author luoxia  
	 * @date Mar 30, 201810:58:56 AM  
	 * @Des 删除角色
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public MapResult delete(String rId){
		try {
			UserRoleMid role = userRoleMidService.findRoleByPid(rId);
			int count = userRoleMidService.findUserCountByRoleId(rId);
			if(role == null){
				return MapResult.error(-1,"用户不存在或已被删除");
			}		
			if(count>0){
				return MapResult.error(-1,"该角色已被使用");
			}
			
			userRoleMidService.deleteRole(rId);
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			return MapResult.error(-1,"删除失败");
		}
		return MapResult.ok(200,"删除成功");
	}
}
