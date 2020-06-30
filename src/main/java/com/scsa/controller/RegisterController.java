package com.scsa.controller;

import com.scsa.pojo.User;
import com.scsa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping("/jumpRegister")
    public String jumpRegister() {
        return "register";
    }

    @RequestMapping("/register")
    public String register(HttpSession session, User user, Model model) {
        String s = userService.queryPwd(user.getUsername());
        if(s!=null) {
            model.addAttribute("error", "用户名已存在");
            return "register";
        }
        userService.insertUser(user);
        session.setAttribute("user", user.getUsername());
        return "redirect:/book/allBook";
    }

}
