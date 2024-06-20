package org.example.keephealthy02.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.keephealthy02.Entity.Healthyanalysis;
import org.example.keephealthy02.Entity.User;
import org.example.keephealthy02.Mapper.HealthyanalysisMapper;
import org.example.keephealthy02.Mapper.SportrecordMapper;
import org.example.keephealthy02.Mapper.UserdietMapper;
import org.example.keephealthy02.Service.HealthyanalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.time.Instant;
import java.util.List;

@Service
public class HealthyanalysisImpl implements HealthyanalysisService {

    @Autowired
    HealthyanalysisMapper healthyanalysisMapper;
    @Autowired
    UserdietMapper userdietMapper;
    @Autowired
    SportrecordMapper sportrecordMapper;
    @Autowired
    // 常量定义
    private static final double WEIGHT_MULTIPLIER = 10.0;
    private static final double HEIGHT_MULTIPLIER = 6.25;
    private static final double AGE_MULTIPLIER = 5.0;
    private static final double CALORIE_DEFICIT_MULTIPLIER = 1.0;
    private static final double CALORIE_SURPLUS_MULTIPLIER = 1.0;
    private static final double IDEAL_CALORIE_BALANCE = 0.0;


    @Override
    public double getBMI(String userId) {
        return healthyanalysisMapper.selectById(userId).getBmi();
    }

    @Override
    public Integer insertHealthData(User user,Date date) {
        double BMI = user.getWeight()/ (user.getHeight()* user.getHeight());
        Healthyanalysis healthyanalysis = new Healthyanalysis(date, user.getId(),BMI,
                countHealthLevel(user,userdietMapper.getConsumeCalories(user.getId(),date),sportrecordMapper.getConsumeCalories(user.getId(),date)));
        return healthyanalysisMapper.insert(healthyanalysis);
    }

    @Override
    public Healthyanalysis getHealthLevle(User user, Date date){
        QueryWrapper<Healthyanalysis> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid",user.getId());
        queryWrapper.eq("date",date);
        return healthyanalysisMapper.selectOne(queryWrapper);
    }
    public Integer countHealthLevel(User user,double intakeCalories,double burnedCalories){
        // 计算BMR（基础代谢率）
        double bmr;

        if (user.getSex()==0) {
            bmr = (WEIGHT_MULTIPLIER * user.getWeight()) + (HEIGHT_MULTIPLIER * user.getHeight()) - (AGE_MULTIPLIER * user.getAge()) + 5;
        } else {
            bmr = (WEIGHT_MULTIPLIER * user.getWeight()) + (HEIGHT_MULTIPLIER * user.getHeight()) - (AGE_MULTIPLIER * user.getAge()) - 161;
        }

        // 计算每日总能量消耗
        double tdee = bmr + burnedCalories;

        // 计算卡路里平衡
        double calorieBalance = intakeCalories - tdee;

        // 计算健康评分
        int healthScore = 100; // 基础分数为100

        // 根据卡路里平衡调整分数
        if (calorieBalance < IDEAL_CALORIE_BALANCE) {
            healthScore -= (int)(Math.abs(calorieBalance) * CALORIE_DEFICIT_MULTIPLIER);
        } else {
            healthScore -= (int)(calorieBalance * CALORIE_SURPLUS_MULTIPLIER);
        }

        // 限制健康评分在0到100之间
        healthScore = Math.max(0, Math.min(healthScore, 100));

        return healthScore;
    }

    public List<Healthyanalysis> getListHealthyData(User user, Date startDate, Date endDate) {
        // 创建 QueryWrapper 对象
        QueryWrapper<Healthyanalysis> queryWrapper = new QueryWrapper<>();

        // 添加日期范围查询条件
        queryWrapper.between("date", startDate, endDate);
        return healthyanalysisMapper.selectList(queryWrapper);
    }
}
