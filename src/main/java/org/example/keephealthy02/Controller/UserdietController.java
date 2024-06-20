package org.example.keephealthy02.Controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.example.keephealthy02.Entity.Result;
import org.example.keephealthy02.Entity.Userdiet;
import org.example.keephealthy02.Mapper.UserdietMapper;
import org.example.keephealthy02.Service.UserdietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Api(tags = "用户饮食管理")
@RequestMapping("/user/userdietManager")
public class UserdietController {

    @Autowired
    private UserdietService userdietService;
    @ApiOperation(value = "用户获取近期食品摄入信息")
    @ApiImplicitParam(
            name = "userId",
            value = "用户id",
            required = true
    )
    @PostMapping("/getAlldiet")
    public Result getAll(@RequestParam String userId){
        List<Userdiet> allUserDiet = userdietService.getAllUserDiet(userId);
        return Result.success(allUserDiet);
    }



    @ApiOperation(value = "用户新添加一条食物摄入信息")
    @PostMapping("/insertDiet")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "userId",
                    value = "用户id",
                    required = true
            ),
            @ApiImplicitParam(
                    name = "foodId",
                    value = "食物编号",
                    required = true
            ),
            @ApiImplicitParam(
                    name = "quantity",
                    value = "数量",
                    required = true
            )
    })
    public Result insertOne(@RequestParam String userId, @RequestParam String foodId,@RequestParam int quantity){
        Integer i = userdietService.insertOne(userId, foodId,quantity);
        return Result.success(i);
    }


}
