package org.example.keephealthy02.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.example.keephealthy02.Entity.User;
import org.example.keephealthy02.Mapper.UserMapper;
import org.example.keephealthy02.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class UserServiceImpl implements Service<User> {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectById(String id) {
        return userMapper.selectById(id);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int update(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }
}
