package com.systcloud.dao;

import com.github.pagehelper.Page;
import com.systcloud.domain.User;

public interface UserMapper {

    User logind(User user);
    //查找所有用户信息
    Page<User> usermanage();
    Integer logindd(User user);
    Integer logout(User user);
    Integer deleteuserById(User user);
    User findaccount(String id);
    Integer addUser(User user);
    Integer edituser(User user);
}
