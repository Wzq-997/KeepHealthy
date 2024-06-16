package org.example.keephealthy02.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.example.keephealthy02.Entity.Result;
import org.example.keephealthy02.Service.SportTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "运动类型管理Api")
@RestController
@RequestMapping("/sport/type")
public class SportTypeController {
    @Autowired
    private SportTypeService sportTypeService;

    @ApiOperation(value = "获取所有的运动类型")
    @PostMapping("/getSportType")
    public Result getAll(){
        return Result.success(sportTypeService.getAllSportType());
    }

    @ApiOperation(value = "获取指定的运动类型")
    @ApiImplicitParam(
            name = "sportId",
            value = "运动类型id",
            required = true
    )
    @PostMapping("/getOneSportType")
    public Result getOne(String sportId){
        return Result.success(sportTypeService.getOne(sportId));
    }
}
