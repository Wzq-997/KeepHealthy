package org.example.keephealthy02.Service.Impl;

import org.example.keephealthy02.Entity.Result;
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
    public Result register(User user) {
        if (userMapper.selectByPrimaryKey(user.getId()) != null) {
            return Result.error("用户已存在");
        }
        userMapper.insert(user);
        return Result.success(user);
    }

    @Override
    public Result login(String name, String password) {
        // Note: If login is by name, then adjust the method and mapper accordingly
        User user = userMapper.selectByPrimaryKey(name);
        if (user == null) {
            return Result.error("用户不存在");
        }
        if (!user.getPassword().equals(password)) {
            return Result.error("密码错误");
        }
        return Result.success(user);
    }

    @Override
    public Result getUserById(String id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return Result.error("用户不存在");
        }
        return Result.success(user);
    }

    @Override
    public Result updateUser(User user) {
        int result = userMapper.updateByPrimaryKeySelective(user);
        if (result == 0) {
            return Result.error("更新用户信息失败");
        }
        return Result.success(user);
    }

    @Override
    public Result deleteUser(String id) {
        int result = userMapper.deleteByPrimaryKey(id);
        if (result == 0) {
            return Result.error("删除用户失败");
        }
        return Result.success();
    }
}
