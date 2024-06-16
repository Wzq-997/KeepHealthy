package org.example.keephealthy02.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.example.keephealthy02.Entity.Result;
import org.example.keephealthy02.Entity.Sportrecord;
import org.example.keephealthy02.Entity.User;
import org.example.keephealthy02.Service.SportRecordService;
import org.example.keephealthy02.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "运动管理Api")
@RestController
@RequestMapping("/sport")
public class SportrecordController {

    @Autowired
    private SportRecordService sportRecordService;
    @Autowired
    private UserService userService;
    @ApiOperation(value = "新添加一条运动记录")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "lastTime",
                    value = "运动时长",
                    required = true
            ),
            @ApiImplicitParam(
                    name = "userId",
                    value = "用户id   ",
                    required = true
            ),
            @ApiImplicitParam(
                    name = "sport_id",
                    value = "运动种类id",
                    required = true
            ),
    })
    @PostMapping("/insertSportRecord")
    public Result newSportRecord(@RequestParam double lastTime,
                                 @RequestParam String userId,
                                 @RequestParam String sportId
                                 ){
        Integer i = sportRecordService.newSportRecord(lastTime, userId, sportId);
        if (i>0)
        {
            return Result.success("添加成功");
        }
        else
            return Result.error("添加失败");
    }

//    获取所有的用户运动记录
    @ApiOperation(value = "获取指定用户的运动记录")
    @ApiImplicitParam(
            name = "userId",
            value = "用户id",
            required = true
    )
    @PostMapping("/getSportRecord")
    public Result getSportRecord(@RequestParam String userId) {
        User user = userService.selectById(userId);
        if(user==null)
            return Result.error("用户不存在");
        List<Sportrecord> sport = sportRecordService.getSport(userId);
        return Result.success(sport);
    }
}
