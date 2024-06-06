package org.example.keephealthy02.Mapper;

import org.example.keephealthy02.Entity.Sleep;

public interface SleepMapper {
    int insert(Sleep record);

    int insertSelective(Sleep record);
}