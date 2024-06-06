package org.example.keephealthy02.Mapper;

import org.example.keephealthy02.Entity.Food;

public interface FoodMapper {
    int insert(Food record);

    int insertSelective(Food record);
}