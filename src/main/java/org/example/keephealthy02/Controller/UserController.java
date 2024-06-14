package org.example.keephealthy02.Controller;

import org.example.keephealthy02.Entity.Result;
import org.example.keephealthy02.Entity.User;
import org.example.keephealthy02.Service.UserService;
import org.example.keephealthy02.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.example.keephealthy02.Service.HealthanalysisService;
import java.io.Serializable;

@RestController
@RequestMapping("/user")
public class UserController{
    //修改和删除
    @Autowired
    private UserService userService;
    @Autowired
    private HealthanalysisService healthanalysisService;
    /*@GetMapping("/list")
    public Result list(){
        return ResultUtil.success(this.userService.list());
    }*/

    @PutMapping("/updateById")
    public Result updateById(@RequestBody User user){
        this.userService.updateById(user);
        return ResultUtil.success(user);
    }

    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Serializable id){

        this.userService.removeById(id);
        return ResultUtil.success(id);
    }

}
