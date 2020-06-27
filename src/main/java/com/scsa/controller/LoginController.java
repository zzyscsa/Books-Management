package com.scsa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class LoginController {

    //跳转登录页面
    @RequestMapping("/jumpLogin")
    public String jumpLogin(HttpSession session) {
        if(session.getAttribute("user")!=null) {
            return "success";
        }
        return "login";
    }

    //登录提交
    @RequestMapping("/login")
    public String login(HttpSession session, String username, String pwd) {
        //往session中记录，这样只要不退出都能够访问书籍系统
        session.setAttribute("user", username);
        return "redirect:/book/allBook";
    }

    //退出登录
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        //session 过期
        session.invalidate();
        return "login";
    }
}
