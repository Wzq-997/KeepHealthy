package org.example.keephealthy02.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.keephealthy02.Entity.User;
import org.example.keephealthy02.Entity.Userdiet;

import java.time.LocalDate;
import java.util.Date;

@Mapper
public interface UserdietMapper extends BaseMapper<Userdiet> {
}