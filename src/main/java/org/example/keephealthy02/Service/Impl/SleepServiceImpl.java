package org.example.keephealthy02.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.example.keephealthy02.Entity.Sleep;
import org.example.keephealthy02.Mapper.SleepMapper;
import org.example.keephealthy02.Service.SleepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 睡眠质量表 服务实现类
 * </p>
 *
 * @author 陈昕榆
 * @since 2024-06-18
 */
@Service
public class SleepServiceImpl extends ServiceImpl<SleepMapper, Sleep> implements SleepService {
    @Autowired
    private SleepMapper sleepMapper;

    @Override
    public String CalculateSleepTime(String userId) {
        QueryWrapper<Sleep> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        Sleep sleep = sleepMapper.selectOne(queryWrapper);

        if (sleep != null) {
            Duration duration = Duration.between(sleep.getBed(),sleep.getWeak());
            long days = duration.toDays();
            long hours = duration.toHours();
            long minutes = duration.toMinutesPart();
            long seconds = duration.toSecondsPart();

            String formattedDuration = String.format("您的睡眠时间：%02d天%02d时%02d分%02d秒",days, hours, minutes, seconds);

            return formattedDuration;


        }
        return "无睡眠记录";
    }

    @Override
    public Sleep MyInfo(String userId) {
        QueryWrapper<Sleep> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        Sleep sleep = sleepMapper.selectOne(queryWrapper);
        return sleep;
    }
}
