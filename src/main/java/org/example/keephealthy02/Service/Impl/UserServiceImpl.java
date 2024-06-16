package org.example.keephealthy02.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.keephealthy02.Entity.User;
import org.example.keephealthy02.Mapper.UserMapper;
import org.example.keephealthy02.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getuser(String id) {
        return userMapper.selectById(id);
    }
    @Override
    public User selectById(String id) {
        return userMapper.selectById(id);
    }
    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }
    @Override
    public void update(User user) {
        userMapper.update(user,null);
    }
//    @Override
//    public User getuser(int id) {
//        return userMapper.selectById(id);
//    }
}
