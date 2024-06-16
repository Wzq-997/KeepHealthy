package org.example.keephealthy02.Service;

import org.example.keephealthy02.Entity.Sportrecord;

import java.util.List;

public interface SportRecordService {

//    获取用户运动记录
    List<Sportrecord> getSport(String userId);
}
