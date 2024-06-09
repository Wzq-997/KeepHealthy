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
        // Assuming there is a method in FoodMapper to select food by id
        return foodMapper.selectFoodById(id);
    }

    @Override
    public List<Food> getAllFoods() {
        // Assuming there is a method in FoodMapper to select all foods
        return foodMapper.selectAllFoods();
    }

    @Override
    public void updateFood(Food food) {
        // Assuming there is a method in FoodMapper to update food
        foodMapper.updateFood(food);
    }

    @Override
    public void deleteFoodById(String id) {
        // Assuming there is a method in FoodMapper to delete food by id
        foodMapper.deleteFoodById(id);
    }
}
