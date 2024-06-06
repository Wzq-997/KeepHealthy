package org.example.keephealthy02.Mapper;

import org.example.keephealthy02.Entity.Userdiet;

public interface UserdietMapper {
    int insert(Userdiet record);

    int insertSelective(Userdiet record);
}