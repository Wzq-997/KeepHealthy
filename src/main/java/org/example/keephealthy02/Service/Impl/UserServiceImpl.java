package org.example.keephealthy02.Service.Impl;

import org.example.keephealthy02.Entity.User;
import org.example.keephealthy02.Mapper.UserMapper;
import org.example.keephealthy02.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getuser(int id) {
        return userMapper.selectById(id);
    }
}
