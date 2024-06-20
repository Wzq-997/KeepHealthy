package org.example.keephealthy02.Service;

import org.example.keephealthy02.Entity.Sportrecord;
import org.example.keephealthy02.Vo.Friends;
import org.example.keephealthy02.Vo.SingleCar;
import org.example.keephealthy02.Vo.sportsWithType;

import java.time.LocalDate;
import java.util.List;

public interface SportRecordService {

    //    获取用户运动记录
    List<sportsWithType> getSport(String userId);

    //  用户记录运动情况,即插入一条新的用户运动记录
    Integer newSportRecord(double lastTime,String userId,String sportId);

//    获取用户当天运动记录
    List<SingleCar> getTodayRecord(String userId, LocalDate date);

    List<Friends> getFriends();

}
