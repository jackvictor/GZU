package com.gzu.su.manager.user.controller;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.gzu.su.manager.common.response.MapResult;
import com.gzu.su.manager.common.response.PageResult;
import com.gzu.su.manager.common.security.SessionInterceptor;
import com.gzu.su.manager.common.utils.Md5Utils;
import com.gzu.su.manager.user.model.UserInfo;
import com.gzu.su.manager.user.model.UserRoleMid;
import com.gzu.su.manager.user.model.vo.UserPage;
import com.gzu.su.manager.user.model.vo.UserPwd;
import com.gzu.su.manager.user.service.UserInfoService;
import com.gzu.su.manager.user.service.UserRoleMidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 *
 * @Class UserController
 * @author mli8
 * @date Mar 21, 201810:26:51 AM  
 * @Des 用户管理-用户管理、角色管理、用户角色关联、角色权限关联
 * @since JDK 1.8
 */
@Controller
@RequestMapping(value = "/user")
public class UserController{
    private static Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserRoleMidService userRoleService;

    @RequestMapping(value = "/ulist")
    public String home(@SessionAttribute(SessionInterceptor.SESSION_KEY)UserInfo userInfo, Model model){
        model.addAttribute("user_account", userInfo.getUserId());
        return "user/user_list";
    }

    /**
     *
     * @param page 页数
     * @param limit 个数
     * @param searchName 搜索用户名
     * @author luoxia
     * @date Mar 30, 201810:36:49 AM
     * @Des 分页查询
     */
    @ResponseBody
    @RequestMapping(value = "/page")
    public PageResult<UserPage> page(Integer page, Integer limit, String searchName, @SessionAttribute(SessionInterceptor.SESSION_KEY)UserInfo userInfo){
        try {
            Integer startNum = 0;
            Integer size = limit;
            if(page > 0){
                startNum = (page - 1) * size;
            }
            PageResult<UserPage> pageR = userInfoService.findByPage(startNum,size,searchName,userInfo);
            return pageR;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     *
     * @param model
     * @param pid 主键ID
     * @param sign 新增 OR 修改
     * @return  跳转URL
     * @returnType String
     * @author luoxia
     * @date Mar 30, 201810:38:11 AM
     * @Des 新增/修改跳转页面
     */
    @RequestMapping(value = "/info")
    public String info(Model model,String pid,String sign){
        try {
            UserInfo userInfo = new UserInfo();
            if(!StringUtils.isEmpty(pid) && !StringUtils.isEmpty(sign)){
                userInfo = userInfoService.findUserByPid(pid);
            }
            List<UserRoleMid> roles = userRoleService.findAllRole();
            model.addAttribute("user", userInfo);
            model.addAttribute("roles",roles);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return "user/user_info";
    }

    /**
     *
     * @param model
     * @param uId 主键ID
     * @return  跳转URL
     * @returnType String
     * @author luoxia
     * @date Mar 30, 201810:43:01 AM
     * @Des 查看用户信息
     */
    @RequestMapping(value = "/show")
    public String show(Model model,String uId){
        try {
            UserInfo userInfo = userInfoService.findUserByPid(uId);
            List<UserRoleMid> roles = userRoleService.findAllRole();
            model.addAttribute("user", userInfo);
            model.addAttribute("roles",roles);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return "user/user_show";
    }

    /**
     *
     * @param uId 用户id
     * @author mli8
     * @date Mar 30, 20183:45:31 PM
     * @Des 基础信息页
     */
    @RequestMapping(value = "/account")
    public String account(Model model,String uId){
        try {
            UserInfo userInfo = userInfoService.findUserByPid(uId);
            List<UserRoleMid> roles = userRoleService.findAllRole();
            model.addAttribute("user", userInfo);
            model.addAttribute("roles",roles);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return "user/user_account";
    }

    /**
     *
     * @param uId 用户id
     * @author mli8
     * @date Mar 30, 20183:45:58 PM
     * @Des 修改密码页
     */
    @RequestMapping(value = "/pwd")
    public String pwd(Model model,String uId){
        try {
            UserInfo userInfo = userInfoService.findUserByPid(uId);
            model.addAttribute("user", userInfo);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return "user/user_pwd";
    }

    /**
     *
     * @param user 修改密码对象
     * @author mli8
     * @date Mar 30, 20183:46:19 PM
     * @Des 修改密码
     */
    @RequestMapping(value = "/changpwd")
    @ResponseBody
    public MapResult changpwd(UserPwd user){
        try {
            if(!user.getSnpwd().equals(user.getNpwd())){
                return MapResult.error(-1,"新密码两次输入不一致");
            }
            int change_num = userInfoService.changePwd(user);
            if(change_num == 1){
                return MapResult.error(-1,"原密码不正确");
            }
            return MapResult.ok(200,"密码修改成功");
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return MapResult.error(-1,"保存失败");
        }
    }

    /**
     *
     * @param user 用户对象
     * @author mli8
     * @date Mar 30, 20183:48:08 PM
     * @Des 保存、修改用户
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public MapResult save(HttpServletRequest request,UserInfo user){
        String msg;
        try {
            if(!StringUtils.isEmpty(user.getId())){
                userInfoService.updateUserInfo(user);
                msg = "修改成功";
                //修改自己，刷新session
                if("0".equals(user.getMine())){
                    HttpSession session = request.getSession();
                    session.setAttribute(SessionInterceptor.SESSION_KEY,user);
                }
                return MapResult.ok(0,msg);
            }else{
                if(userInfoService.saveCheckUserName(user.getUserId())){
                    userInfoService.insert(user);
                    msg = "添加成功";
                    return MapResult.ok(0,msg);
                }
                return MapResult.error(-1,"用户名已存在");
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return MapResult.error(-1,"保存失败");
        }
    }

    /**
     *
     * @param uId 用户id
     * @author mli8
     * @date Mar 30, 20183:52:40 PM
     * @Des 根据用户id删除用户
     */
    @RequestMapping(value="/delete")
    @ResponseBody
    public MapResult delete(String uId){
        try {
            UserInfo userInfo = userInfoService.findUserByPid(uId);

            if(userInfo == null){
                return MapResult.error(-1,"用户不存在或已被删除");
            }

            userInfoService.deleteUser(uId);

        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return MapResult.error(-1,"删除失败");
        }
        return MapResult.ok(0,"删除成功");
    }

    /**
     *
     * @param uId 用户id
     * @author mli8
     * @date Mar 30, 20183:52:57 PM
     * @Des 根据用户id重置密码
     */
    @RequestMapping(value = "/resetpwd")
    @ResponseBody
    public MapResult resetpwd(String uId){
        try {
            UserInfo userInfo = userInfoService.findUserByPid(uId);
            if(null == userInfo){
                MapResult.error(-1,"用户异常，请重新登录后再试");
            }
            userInfoService.resetPwd(userInfo);
            return MapResult.ok(200,"密码重置成功");
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return MapResult.error(-1,"密码重置失败");
        }
    }

    public static void main(String[] args) {
        String new_password = "liming";
        String salt = UUID.randomUUID().toString().replace("-","");
        String password = Md5Utils.GetMD5Code(new_password + salt);
        System.out.println(salt+"---"+password);
    }
}
