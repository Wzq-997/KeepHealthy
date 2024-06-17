package org.example.keephealthy02.Service.Impl;

import org.example.keephealthy02.Entity.Menu;
import org.example.keephealthy02.Mapper.MenuMapper;
import org.example.keephealthy02.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getAll() {
        return menuMapper.selectList(null);
    }
}
