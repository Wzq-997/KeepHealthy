package org.example.keephealthy02.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.keephealthy02.Entity.SportType;
import org.example.keephealthy02.Entity.Sportrecord;
import org.example.keephealthy02.Mapper.SportTypeMapper;
import org.example.keephealthy02.Service.SportTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportTypeImpl implements SportTypeService {
    @Autowired
    private SportTypeMapper sportTypeMapper;
    @Override
    public List<SportType> getAllSportType() {
        return sportTypeMapper.selectList(null);
    }

    @Override
    public SportType getOne(String sportId) {
        QueryWrapper<SportType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sport_id",sportId);
        return sportTypeMapper.selectOne(queryWrapper);
    }
}
