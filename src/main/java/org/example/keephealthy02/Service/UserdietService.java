package org.example.keephealthy02.Service;

import org.example.keephealthy02.Entity.Userdiet;
import org.example.keephealthy02.Vo.UserDietwithFood;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface UserdietService {
    List<UserDietwithFood> getAllUserDiet(String userId);

//    根据所给的食物编号，给用户的摄入添加一条新的信息
    Integer insertOne(String userId, String foodId,int quantity);
    public double getConsumeCalories(String userid, Date date);
//    获取用户所摄入的卡路里总数
    double getAllIntake(String userId);
}
