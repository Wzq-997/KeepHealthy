package org.example.keephealthy02.Service;

import org.example.keephealthy02.Entity.Healthyanalysis;
import org.example.keephealthy02.Entity.User;

import java.util.Date;
import java.util.List;

public interface HealthanalysisService {
    //根据userId获取bmi
    List<Healthyanalysis> getBMI(String userId);

    //根据用户信息计算bmi和健康信息
    Integer updateHealthData(User user);
    //获取用户的健康等级
    List<Healthyanalysis> getHealthLevle(String userId);
    //获取一定时间内的健康信息
    List<Healthyanalysis> getListHealthyData(User user, Date startDate, Date endDate);
}
