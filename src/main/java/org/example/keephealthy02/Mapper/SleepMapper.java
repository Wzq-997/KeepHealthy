package org.example.keephealthy02.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.keephealthy02.Entity.Sleep;

import java.util.Date;
import java.util.List;

@Mapper
public interface SleepMapper {
    int insert(Sleep record);

    int insertSelective(Sleep record);

    Sleep selectSleepByUserIdAndDate(@Param("userid") String userid, @Param("date") Date date);

    List<Sleep> selectAllSleeps();

    int updateSleep(Sleep record);

    int deleteSleepByUserIdAndDate(@Param("userid") String userid, @Param("date") Date date);
}
