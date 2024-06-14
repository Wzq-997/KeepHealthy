package org.example.keephealthy02.Service.Impl;

import org.example.keephealthy02.Entity.Food;
import org.example.keephealthy02.Mapper.FoodMapper;
import org.example.keephealthy02.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodMapper foodMapper;

    @Override
    public void addFood(Food food) {
        foodMapper.insert(food);
    }

    @Override
    public void addFoodSelective(Food food) {
        foodMapper.insertSelective(food);
    }

    @Override
    public Food getFoodById(String id) {
        return foodMapper.selectFoodById(id);
    }

    @Override
    public List<Food> getAllFoods() {
        return foodMapper.selectAllFoods();
    }

    @Override
    public void updateFood(Food food) {
        foodMapper.updateFood(food);
    }

    @Override
    public void deleteFoodById(String id) {
        foodMapper.deleteFoodById(id);
    }
}
