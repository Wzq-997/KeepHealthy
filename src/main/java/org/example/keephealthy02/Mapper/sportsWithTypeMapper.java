package org.example.keephealthy02.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.keephealthy02.Vo.Friends;
import org.example.keephealthy02.Vo.SingleCar;
import org.example.keephealthy02.Vo.sportsWithType;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface sportsWithTypeMapper {
    List<sportsWithType>  getSportsWithType(String userId);
//    用户获取当天的运动记录
    List<SingleCar> getAllsingle(String userId, LocalDate date);

//    获取前六个好友，按消耗卡路里排序
    List<Friends> getFriends();
}
