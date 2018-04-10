package com.gzu.su.manager.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: jack.ye
 * @create: 2018/04/09 21:09
 * @description: 登录接口
 **/
@Controller
public class LoginController {

    @RequestMapping(value = "/login/tologin")
    public String toLogin(Model model,String userId) {
        return "login";
    }
}
