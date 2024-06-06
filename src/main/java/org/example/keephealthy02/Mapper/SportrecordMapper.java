package org.example.keephealthy02.Mapper;

import org.example.keephealthy02.Entity.Sportrecord;

public interface SportrecordMapper {
    int insert(Sportrecord record);

    int insertSelective(Sportrecord record);
}