package org.example.keephealthy02.Mapper;

import org.example.keephealthy02.Entity.Food;
import java.util.List;

public interface FoodMapper {
    int insert(Food record);

    int insertSelective(Food record);

    Food selectFoodById(String id);

    List<Food> selectAllFoods();

    int updateFood(Food record);

    int deleteFoodById(String id);
}
