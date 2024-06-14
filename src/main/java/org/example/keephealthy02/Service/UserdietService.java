package org.example.keephealthy02.Service;

import org.example.keephealthy02.Entity.Userdiet;

import java.util.Date;
import java.util.List;

public interface UserdietService {
    int addUserdiet(Userdiet userdiet);

    int addUserdietSelective(Userdiet userdiet);

    int deleteUserdietById(String userid, Date date);

    int updateUserdiet(Userdiet userdiet);

    Userdiet getUserdietById(String userid, Date date);

    List<Userdiet> getAllUserdiets();
}
