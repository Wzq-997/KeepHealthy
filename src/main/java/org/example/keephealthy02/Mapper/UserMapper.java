package org.example.keephealthy02.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.keephealthy02.Entity.User;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
    int deleteByPrimaryKey(String id);
    int insert(User record);
    int insertSelective(User record);
    User selectByPrimaryKey(String id);
    int updateByPrimaryKeySelective(User record);
    int updateByPrimaryKeyWithBLOBs(User record);
    int updateByPrimaryKey(User record);
}
