package org.example.keephealthy02.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.keephealthy02.Entity.Food;
import org.example.keephealthy02.Entity.Sportrecord;
import org.example.keephealthy02.Entity.Userdiet;
import org.example.keephealthy02.Mapper.FoodMapper;
import org.example.keephealthy02.Mapper.UserDietwithFoodMapper;
import org.example.keephealthy02.Mapper.UserdietMapper;
import org.example.keephealthy02.Service.UserdietService;
import org.example.keephealthy02.Vo.UserDietwithFood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserdietServiceImpl implements UserdietService {
    @Autowired
    private UserdietMapper userdietMapper;
    @Autowired
    private FoodMapper foodMapper;

    @Autowired
    private UserDietwithFoodMapper usm;



//    获取所有食谱
    @Override
    public List<UserDietwithFood> getAllUserDiet(String userId) {
        List<UserDietwithFood> userDietwithFood = usm.getUserDietwithFood(userId);
        return userDietwithFood;
    }

//    插入一个
    @Override
    public Integer insertOne(String userId, String foodId,int quantity) {
        LocalDate date = LocalDate.now();
        QueryWrapper<Food> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",foodId);
        Food foodId1 = foodMapper.selectOne(queryWrapper);
        double calories = foodId1.getCalories()*quantity;
        Userdiet userdiet = new Userdiet(userId,foodId,date,calories);
        int insert = userdietMapper.insert(userdiet);
        return insert;
    }
}
