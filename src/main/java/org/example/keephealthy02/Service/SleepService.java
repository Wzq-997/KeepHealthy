package org.example.keephealthy02.Service;

import org.example.keephealthy02.Entity.Sleep;

import java.util.Date;
import java.util.List;

public interface SleepService {
    void addSleep(Sleep sleep);

    void addSleepSelective(Sleep sleep);

    Sleep getSleepByUserIdAndDate(String userid, Date date);

    List<Sleep> getAllSleeps();

    void updateSleep(Sleep sleep);

    void deleteSleepByUserIdAndDate(String userid, Date date);
}
