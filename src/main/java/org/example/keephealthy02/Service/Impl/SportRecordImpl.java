package org.example.keephealthy02.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.keephealthy02.Entity.SportType;
import org.example.keephealthy02.Entity.Sportrecord;
import org.example.keephealthy02.Entity.User;
import org.example.keephealthy02.Mapper.SportTypeMapper;
import org.example.keephealthy02.Mapper.SportrecordMapper;
import org.example.keephealthy02.Mapper.UserMapper;
import org.example.keephealthy02.Service.SportRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SportRecordImpl implements SportRecordService {
    @Autowired
    private SportrecordMapper sportrecordMapper;
    @Autowired
    private SportTypeMapper sportTypeMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<Sportrecord> getSport(String userId) {
        QueryWrapper<Sportrecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        return sportrecordMapper.selectList(queryWrapper);
    }

    @Override
    public Integer newSportRecord(double lastTime, String userId, String sportId) {
//        计算消耗的卡路里
        SportType sportType = sportTypeMapper.selectById(sportId);
        double single = sportType.getSingleCalorie();
//        获取用户的基本信息
        User user = userMapper.selectById(userId);
        double weight = user.getWeight();
//        double seconds = lastTime*60;
//       距离，假设10km/h
        double leave = lastTime/3600*10;
//        计算卡路里
        double calor = weight* 30 / (400 / (10 * 1000 / 60)) * leave / 60 / 60;
        Sportrecord sportrecord = new Sportrecord(LocalDate.now(), userId, sportId, calor, lastTime);
//        插入
        return sportrecordMapper.insert(sportrecord);
    }
}