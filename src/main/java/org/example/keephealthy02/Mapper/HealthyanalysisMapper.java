package org.example.keephealthy02.Mapper;

import org.example.keephealthy02.Entity.Healthyanalysis;

public interface HealthyanalysisMapper {
    int insert(Healthyanalysis record);

    int insertSelective(Healthyanalysis record);
}