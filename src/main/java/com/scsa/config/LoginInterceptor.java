package com.scsa.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//拦截器
public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果是登录页面，放行
        if(request.getRequestURI().contains("login")) {
            return true;
        }
        HttpSession session = request.getSession();
        //如果已经登录了，放行
        if(session.getAttribute("user")!=null) {
            return true;
        }
        //未登录，跳转至登录页面
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        return false;
    }
}
