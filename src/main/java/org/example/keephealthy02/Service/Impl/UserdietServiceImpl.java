package org.example.keephealthy02.Service.Impl;

import org.example.keephealthy02.Entity.Userdiet;
import org.example.keephealthy02.Mapper.UserdietMapper;
import org.example.keephealthy02.Service.UserdietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserdietServiceImpl implements UserdietService {

    @Autowired
    private UserdietMapper userdietMapper;

    @Override
    public int addUserdiet(Userdiet userdiet) {
        return userdietMapper.insert(userdiet);
    }

    @Override
    public int addUserdietSelective(Userdiet userdiet) {
        return userdietMapper.insertSelective(userdiet);
    }

    @Override
    public int deleteUserdietById(String userid, Date date) {
        return userdietMapper.deleteById(userid, date);
    }

    @Override
    public int updateUserdiet(Userdiet userdiet) {
        return userdietMapper.update(userdiet);
    }

    @Override
    public Userdiet getUserdietById(String userid, Date date) {
        return userdietMapper.selectById(userid, date);
    }

    @Override
    public List<Userdiet> getAllUserdiets() {
        return userdietMapper.selectAll();
    }
}
