package org.example.keephealthy02.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.keephealthy02.Entity.SportType;
import org.example.keephealthy02.Entity.User;
import org.example.keephealthy02.Mapper.UserMapper;
import org.example.keephealthy02.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getuser(String id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        return userMapper.selectOne(queryWrapper);
    }
    @Override
    public User selectById(String id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        return userMapper.selectOne(queryWrapper);
    }
    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }
    @Override
    public void update(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",user.getId());
        userMapper.update(user,queryWrapper);
    }

}
