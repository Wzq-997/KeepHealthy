package org.example.keephealthy02.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.keephealthy02.Entity.User;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper  extends BaseMapper<User> {
//    public User selectById(String id);
}