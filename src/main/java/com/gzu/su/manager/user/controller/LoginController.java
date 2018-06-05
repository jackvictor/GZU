package com.gzu.su.manager.user.controller;

import com.gzu.su.manager.common.response.Constants;
import com.gzu.su.manager.common.response.MapResult;
import com.gzu.su.manager.common.security.SessionInterceptor;
import com.gzu.su.manager.user.model.SysFun;
import com.gzu.su.manager.user.model.UserInfo;
import com.gzu.su.manager.user.service.SysFunService;
import com.gzu.su.manager.user.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: jack.ye
 * @create: 2018/04/12 13:04
 * @description: 登录接口
 **/
@Controller
public class LoginController {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    SysFunService sysFunService;

    public static Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("login/tologin")
    public String toLogin(){
      return "login";
    }

    @RequestMapping(value = "/login/mailpage", method = RequestMethod.GET)
    public String mailpage() {
        return "email";
    }

    @RequestMapping(value = "/login/check", method = RequestMethod.POST)
    @ResponseBody
    public MapResult login(String userName, String password,HttpServletRequest request) {
        try {
            MapResult mapResult = null;
            if (StringUtils.isEmpty(userName)) {
                mapResult = MapResult.error(Constants.ERROR_CODE_VALIDATION,"用户名不能为空");
                return mapResult;
            }

           if (StringUtils.isEmpty(password)) {
                mapResult = MapResult.error(Constants.ERROR_CODE_VALIDATION,"密码不能为空");
                return mapResult;
            }

            String num_login = userInfoService.login(userName, password);
            if ("1".equals(num_login)) {
                return MapResult.ok(Constants.ERROR_CODE_VALIDATION, "用户名不正确");
            } else if ("2".equals(num_login)) {
                return MapResult.ok(Constants.ERROR_CODE_VALIDATION, "密码不正确");
            } else if ("3".equals(num_login)) {
                return MapResult.ok(Constants.ERROR_CODE_VALIDATION,"用户已停用，请联系管理员");
            }

            HttpSession session = request.getSession();
            session.setAttribute(SessionInterceptor.SESSION_KEY,userInfoService.findUserByPid(num_login));
            return MapResult.ok(Constants.SUCESS_CODE, "登录成功");
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return MapResult.ok(Constants.ERROR_CODE_SYSTEM, "登录失败");
        }
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@SessionAttribute(SessionInterceptor.SESSION_KEY) UserInfo userInfo, Model model) {
        try {
            List<SysFun> roleList = sysFunService.findByRoleId(userInfo.getRoleId());
            model.addAttribute("user_account", userInfo);
            model.addAttribute("role_list", roleList);
            return "index";
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return "404";
        }

    }

}
