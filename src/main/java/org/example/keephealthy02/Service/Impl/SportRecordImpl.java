package org.example.keephealthy02.Service.Impl;

import org.example.keephealthy02.Entity.Sportrecord;
import org.example.keephealthy02.Mapper.SportrecordMapper;
import org.example.keephealthy02.Service.SportRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportRecordImpl implements SportRecordService {
    @Autowired
    private SportrecordMapper sportrecordMapper;

    @Override
    public List<Sportrecord> getSport(String userId) {
        return null;
    }
}
