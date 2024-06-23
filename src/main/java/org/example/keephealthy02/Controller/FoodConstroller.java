package org.example.keephealthy02.Controller;

import org.example.keephealthy02.Entity.Food;
import org.example.keephealthy02.Entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Food")
public class FoodConstroller {
    /**
     * 注入FoodService，用于与食品数据的交互
     */
    @Autowired
    private org.example.keephealthy02.Service.FoodService foodService;

    /**
     * 处理获取所有食品信息的请求
     *
     * @return Result对象，包含所有食品的信息
     */
    @RequestMapping("/getFood")
    public Result getFood()
    {
        // 通过foodService获取所有食品信息
        List<Food> all = foodService.getAll();
        // 返回成功结果，包含所有食品的信息
        return Result.success(all);
    }

}
