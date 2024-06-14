package org.example.keephealthy02.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.keephealthy02.Entity.Userdiet;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserdietMapper {
    int insert(Userdiet record);

    int insertSelective(Userdiet record);

    int deleteById(@Param("userid") String userid, @Param("date") Date date);

    int update(Userdiet record);

    Userdiet selectById(@Param("userid") String userid, @Param("date") Date date);

    List<Userdiet> selectAll();
}
