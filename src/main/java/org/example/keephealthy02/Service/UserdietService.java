package org.example.keephealthy02.Service;

import org.example.keephealthy02.Entity.Userdiet;

import java.time.LocalDate;
import java.util.List;

public interface UserdietService {
    List<Userdiet> getAllUserDiet(String userId);

//    根据所给的食物编号，给用户的摄入添加一条新的信息
    Integer insertOne(String userId, String foodId,int quantity);
}
