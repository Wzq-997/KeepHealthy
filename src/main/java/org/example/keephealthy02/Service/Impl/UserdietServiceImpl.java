package org.example.keephealthy02.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.keephealthy02.Entity.Food;
import org.example.keephealthy02.Entity.Sportrecord;
import org.example.keephealthy02.Entity.Userdiet;
import org.example.keephealthy02.Mapper.FoodMapper;
import org.example.keephealthy02.Mapper.UserdietMapper;
import org.example.keephealthy02.Service.UserdietService;
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




//    获取所有食谱
    @Override
    public List<Userdiet> getAllUserDiet(String userId) {
        QueryWrapper<Userdiet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<Userdiet> userdiets = userdietMapper.selectList(queryWrapper);
        return userdiets;
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
