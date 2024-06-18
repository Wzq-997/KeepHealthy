package org.example.keephealthy02.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.keephealthy02.Entity.User;
import org.example.keephealthy02.Entity.Userdiet;

import java.sql.Date;
import java.util.List;

@Mapper
public interface UserdietMapper extends BaseMapper<Userdiet> {
    int insert(Userdiet record);

    int insertSelective(Userdiet record);

    int deleteById(@Param("userid") String userid, @Param("date") Date date);

    int update(Userdiet record);

    Userdiet selectById(@Param("userid") String userid, @Param("date") Date date);

    List<Userdiet> selectAll();

}