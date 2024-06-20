package org.example.keephealthy02.Service;

import org.example.keephealthy02.Entity.Healthyanalysis;
import org.example.keephealthy02.Entity.User;
import java.util.Date;
import java.util.List;

public interface HealthyanalysisService {
    //根据userId获取bmi
    double getBMI(String userId);
    //根据用户信息计算bmi和健康信息
    Integer insertHealthData(User user,Date date);
    //获取用户的健康等级
    Healthyanalysis getHealthLevle(User user, Date date);
    //获取一定时间内的健康信息
    List<Healthyanalysis> getListHealthyData(User user,Date startDate,Date endDate);
}
