package com.systcloud.service;

import com.github.pagehelper.Page;
import com.systcloud.domain.Book;
import com.systcloud.domain.User;
import com.systcloud.entity.pageResult;
import org.springframework.stereotype.Service;


public interface UserService {
    User login(User user);
    pageResult usermanage(Integer pageNum,Integer pageSize);
    Integer logindd(User user);
    Integer logout(User user);
    Integer deleteuseraccount(User user);
    User findaccountt(String id);
    Integer adduser(User user);
    Integer editUser(User user);
}
