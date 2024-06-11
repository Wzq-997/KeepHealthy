package org.example.keephealthy02.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.keephealthy02.Entity.Food;

@Mapper
public interface FoodMapper extends BaseMapper<Food> {
}