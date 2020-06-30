package com.scsa.controller;

import com.scsa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;

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
    public String login(HttpSession session, String username, String pwd, Model model) {
        //判断该用户名密码和数据库中的是否一致
        String s = userService.queryPwd(username);
        if(s==null) {
            model.addAttribute("error", "用户名不存在");
            return "login";
        }
        if(!s.equals(pwd)) {
            model.addAttribute("error","密码错误");
            return "login";
        }

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
