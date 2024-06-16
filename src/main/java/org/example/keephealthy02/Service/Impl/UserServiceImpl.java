package org.example.keephealthy02.Service.Impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.keephealthy02.Entity.User;
import org.example.keephealthy02.Mapper.UserMapper;
import org.example.keephealthy02.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getuser(int id) {
        return userMapper.selectById(id);
    }
}
