package org.example.keephealthy02.Controller;

import org.example.keephealthy02.Entity.Food;
import org.example.keephealthy02.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @PostMapping("/add")
    public void addFood(@RequestBody Food food) {
        foodService.addFood(food);
    }

    @PostMapping("/addSelective")
    public void addFoodSelective(@RequestBody Food food) {
        foodService.addFoodSelective(food);
    }

    @GetMapping("/{id}")
    public Food getFoodById(@PathVariable String id) {
        return foodService.getFoodById(id);
    }

    @GetMapping("/all")
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }

    @PutMapping("/update")
    public void updateFood(@RequestBody Food food) {
        foodService.updateFood(food);
    }

    @DeleteMapping("/{id}")
    public void deleteFoodById(@PathVariable String id) {
        foodService.deleteFoodById(id);
    }
}
