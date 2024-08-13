package com.systcloud.service.serviceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import com.systcloud.dao.UserMapper;

import com.systcloud.domain.User;
import com.systcloud.entity.pageResult;
import com.systcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    private UserMapper userMapper;
    @Override
    public User login(User user) {
        return userMapper.logind(user);
    }

    @Override
    public pageResult usermanage(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<User> page = userMapper.usermanage();
        return  new pageResult(page.getTotal(), page.getResult());
    }

    @Override
    public Integer logindd(User user) {
        return userMapper.logindd(user);
    }

    @Override
    public Integer logout(User user) {
        return userMapper.logout(user);
    }

    @Override
    public Integer deleteuseraccount(User user) {
        return userMapper.deleteuserById(user);
    }

    @Override
    public User findaccountt(String id) {
        return  userMapper.findaccount(id);
    }

    @Override
    public Integer adduser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public Integer editUser(User user) {
        return userMapper.edituser(user);
    }

}
