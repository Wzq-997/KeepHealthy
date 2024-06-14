package org.example.keephealthy02.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.keephealthy02.Entity.Healthyanalysis;

@Mapper
public interface HealthyanalysisMapper {
    int insert(Healthyanalysis record);

    int insertSelective(Healthyanalysis record);
}