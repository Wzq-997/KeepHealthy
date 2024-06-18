package org.example.keephealthy02.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.keephealthy02.Entity.Sleep;

import java.sql.Date;
import java.util.List;

@Mapper
public interface SleepMapper extends BaseMapper<Sleep> {
    int insert(Sleep record);

    int insertSelective(Sleep record);

    Sleep selectSleepByUserIdAndDate(@Param("userid") String userid, @Param("date") Date date);

    List<Sleep> selectAllSleeps();

    int updateSleep(Sleep record);

    int deleteSleepByUserIdAndDate(@Param("userid") String userid, @Param("date") Date date);
}