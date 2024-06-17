package org.example.keephealthy02.Controller;

import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.example.keephealthy02.Entity.Menu;
import org.example.keephealthy02.Entity.Result;
import org.example.keephealthy02.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/diet")
@Api(tags = "每日食谱")
public class MenuController {

    @Autowired
    private MenuService menuService;
    @ApiOperation(value = "获取食谱")
    @PostMapping("/getMenuRecord")
    public Result getAllMenuRecord(){
        List<Menu> all = menuService.getAll();
        return Result.success(all);
    }
}
