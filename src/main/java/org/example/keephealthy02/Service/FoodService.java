package org.example.keephealthy02.Service;

import org.example.keephealthy02.Entity.Food;
import java.util.List;

public interface FoodService {
    void addFood(Food food);

    void addFoodSelective(Food food);

    Food getFoodById(String id);

    List<Food> getAllFoods();

    void updateFood(Food food);

    void deleteFoodById(String id);
}
