package com.scsa.service;

import com.scsa.dao.UserMapper;
import com.scsa.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper usermapper;

    public void setUsermapper(UserMapper usermapper) {
        this.usermapper = usermapper;
    }

    public String queryPwd(String username) {
        return usermapper.queryPwd(username);
    }

    public int insertUser(User user) {
        return usermapper.insertUser(user);
    }
}
