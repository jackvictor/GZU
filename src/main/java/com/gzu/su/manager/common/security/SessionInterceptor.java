package com.gzu.su.manager.common.security;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @Class SessionInterceptor
 * @author mli8
 * @date Mar 21, 20183:16:15 PM
 * @Des
 * @since JDK 1.8
 */
public class SessionInterceptor implements HandlerInterceptor {

	/**
     * 登录session key
     */
    public final static String SESSION_KEY = "USER_INFO";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        if(session == null || session.getAttribute(SESSION_KEY) == null){
            response.sendRedirect("/login/tologin");
            return false;
        }

        return true;
    }

}
