package com.scsa.dao;

import com.scsa.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    //根据username查询
    String queryPwd(@Param("username") String username);

    //插入一个新用户
    int insertUser(User user);
}
