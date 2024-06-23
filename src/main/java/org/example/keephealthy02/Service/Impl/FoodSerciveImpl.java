package org.example.keephealthy02.Service.Impl;

import org.example.keephealthy02.Entity.Food;
import org.example.keephealthy02.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodSerciveImpl implements FoodService {
    @Autowired
    private org.example.keephealthy02.Mapper.FoodMapper foodMapper;
    @Override
    public List<Food> getAll() {
        List<Food> all = foodMapper.selectList(null);
        return all;
    }
}
