package org.example.keephealthy02.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.keephealthy02.Entity.Sleep;

@Mapper
public interface SleepMapper {
    int insert(Sleep record);

    int insertSelective(Sleep record);
}