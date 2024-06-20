package org.example.keephealthy02.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.keephealthy02.Entity.SportType;
import org.example.keephealthy02.Entity.Sportrecord;
import org.example.keephealthy02.Entity.User;
import org.example.keephealthy02.Mapper.SportTypeMapper;
import org.example.keephealthy02.Mapper.SportrecordMapper;
import org.example.keephealthy02.Mapper.UserMapper;
import org.example.keephealthy02.Mapper.sportsWithTypeMapper;
import org.example.keephealthy02.Service.SportRecordService;
import org.example.keephealthy02.Vo.Friends;
import org.example.keephealthy02.Vo.SingleCar;
import org.example.keephealthy02.Vo.sportsWithType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
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
    @Autowired
    private sportsWithTypeMapper sportsWM;
    @Override
    public List<sportsWithType> getSport(String userId) {
//        QueryWrapper<Sportrecord> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_id",userId);

        return sportsWM.getSportsWithType(userId);
    }

    @Override
    public Integer newSportRecord(double lastTime, String userId, String sportId) {
//        计算消耗的卡路里
        QueryWrapper<SportType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sport_id",sportId);
        SportType sportType = sportTypeMapper.selectOne( queryWrapper);
        double single = sportType.getSingleCalorie();


//        System.out.println(single);
//        获取用户的基本信息
        QueryWrapper<User> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("id",userId);
        User user = userMapper.selectOne(queryWrapper2);
//        double seconds = lastTime*60;
//       距离，假设10km/h
//        计算卡路里
        double calor = (lastTime/7) * 74;


        LocalDate dd = LocalDate.now();

//        查看今日是否已经有了相同的运动记录
        QueryWrapper<Sportrecord> query1 = new QueryWrapper<>();
        query1.eq("date",dd).eq("sport_id",sportId).eq("user_id",userId);
        Sportrecord sportrecords = sportrecordMapper.selectOne(query1);
//        没有则直接插入新的
        if(sportrecords==null)
        {
            Sportrecord sportrecord = new Sportrecord(LocalDate.now(), userId, sportId, calor, lastTime);
            System.out.println(sportrecord);
            return sportrecordMapper.insert(sportrecord);
        }
//        有要更新数据
        else {
            double sumCar = calor+sportrecords.getConsumeCalories();
            double sumLast = lastTime+sportrecords.getLastTime();
            query1.eq("sport_id",sportId).eq("date",dd).eq("user_id",userId);
            sportrecordMapper.delete(query1);
            Sportrecord sr = new Sportrecord(dd, userId, sportId, sumCar, sumLast);
            int insert = sportrecordMapper.insert(sr);
            return insert;
        }
    }

    @Override
//    用户获取当天的运动记录
    public List<SingleCar> getTodayRecord(String userId,LocalDate date)
    {
//        QueryWrapper<Sportrecord> queryWrapper2 = new QueryWrapper<>();
//        queryWrapper2.eq("user_id",userId).eq("date",date);
//        List<Sportrecord> sportrecords = sportrecordMapper.selectList(queryWrapper2);
        List<SingleCar> allsingle = sportsWM.getAllsingle(userId, date);
        return allsingle;
    }

    @Override
    public List<Friends> getFriends() {
        return sportsWM.getFriends();
    }


}