package org.example.keephealthy02.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.keephealthy02.Vo.UserDietwithFood;

import java.util.List;

@Mapper
public interface UserDietwithFoodMapper {
    List<UserDietwithFood> getUserDietwithFood(String userId);
}
