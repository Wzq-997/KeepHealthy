package org.example.keephealthy02.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.example.keephealthy02.Entity.Healthyanalysis;

@Mapper
public interface HealthyanalysisMapper extends BaseMapper<Healthyanalysis> {
    public Integer SelectHealthyWithDate();
}