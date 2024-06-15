package org.example.keephealthy02.Service.Impl;

import org.example.keephealthy02.Entity.Sleep;
import org.example.keephealthy02.Mapper.SleepMapper;
import org.example.keephealthy02.Service.SleepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SleepServiceImpl implements SleepService {

    @Autowired
    private SleepMapper sleepMapper;

    @Override
    public void addSleep(Sleep sleep) {
        sleepMapper.insert(sleep);
    }

    @Override
    public void addSleepSelective(Sleep sleep) {
        sleepMapper.insertSelective(sleep);
    }

    @Override
    public Sleep getSleepByUserIdAndDate(String userid, Date date) {
        return sleepMapper.selectSleepByUserIdAndDate(userid, date);
    }

    @Override
    public List<Sleep> getAllSleeps() {
        return sleepMapper.selectAllSleeps();
    }

    @Override
    public void updateSleep(Sleep sleep) {
        sleepMapper.updateSleep(sleep);
    }

    @Override
    public void deleteSleepByUserIdAndDate(String userid, Date date) {
        sleepMapper.deleteSleepByUserIdAndDate(userid, date);
    }
}
