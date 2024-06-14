package org.example.keephealthy02.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.keephealthy02.Entity.Sportrecord;

@Mapper
public interface SportrecordMapper {
    int insert(Sportrecord record);

    int insertSelective(Sportrecord record);
}