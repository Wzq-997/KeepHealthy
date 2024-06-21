package org.example.keephealthy02.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.keephealthy02.Entity.Result;
import org.example.keephealthy02.Entity.User;
import org.example.keephealthy02.Service.HealthanalysisService;
import org.example.keephealthy02.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@Api(tags = "用户管理Api")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private HealthanalysisService healthanalysisService;

    @GetMapping("/list")
    public Result list(){
        return Result.success(this.userService.list());
    }
    @ApiOperation(value = "修改")
    @PutMapping("/updateById")
    public Result updateById(@RequestBody User user){
        this.userService.update(user);
//        用户信息修改时，更新健康等级
        healthanalysisService.updateHealthData(user);

        return Result.success(user);
    }
    @ApiOperation(value = "删除")
    @GetMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable String id){

        this.userService.delete(id);
        return Result.success(id);
    }

}


