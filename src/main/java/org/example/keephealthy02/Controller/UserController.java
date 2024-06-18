package org.example.keephealthy02.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.keephealthy02.Entity.Result;
import org.example.keephealthy02.Entity.User;
import org.example.keephealthy02.Service.UserService;
import org.example.keephealthy02.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@Api(tags = "用户管理Api")
public class UserController {
    //修改和删除
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Result list(){
        return ResultUtil.success(this.userService.list());
    }
    @ApiOperation(value = "修改")
    @PutMapping("/updateById")
    public Result updateById(@RequestBody User user){
        this.userService.updateById(user);
        return ResultUtil.success(user);
    }
    @ApiOperation(value = "删除")
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Serializable id){

        this.userService.removeById(id);
        return ResultUtil.success(id);
    }

//
}
