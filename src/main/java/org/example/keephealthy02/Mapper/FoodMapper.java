package org.example.keephealthy02.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.keephealthy02.Entity.Food;

import java.util.List;

@Mapper
public interface FoodMapper extends BaseMapper<Food> {
    int insert(Food record);

    int insertSelective(Food record);

    Food selectFoodById(String id);

    List<Food> selectAllFoods();

    int updateFood(Food record);

    int deleteFoodById(String id);
}