package org.example.keephealthy02.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.keephealthy02.Entity.Result;
import org.example.keephealthy02.Entity.Sportrecord;
import org.example.keephealthy02.Entity.User;
import org.example.keephealthy02.Service.HealthanalysisService;
import org.example.keephealthy02.Service.SportRecordService;
import org.example.keephealthy02.Service.UserService;
import org.example.keephealthy02.Vo.Friends;
import org.example.keephealthy02.Vo.SingleCar;
import org.example.keephealthy02.Vo.sportsWithType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Api(tags = "运动管理Api")
@RestController
@RequestMapping("/sport")
public class SportrecordController {

    @Autowired
    private SportRecordService sportRecordService;
    @Autowired
    private UserService userService;
    @Autowired
    private HealthanalysisService healthanalysisService;
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
                    name = "sportId",
                    value = "运动种类id",
                    required = true
            ),
    })
    @PostMapping("/insertSportRecord")
    public Result newSportRecord(@RequestParam("lastTime") double lastTime,
                                 @RequestParam("userId") String userId,
                                 @RequestParam("sportId") String sportId
                                 ){
        Integer i = sportRecordService.newSportRecord(lastTime, userId, sportId);
        if (i>0)
        {
//            当用户添加一次运动时，健康等级发生改变
            healthanalysisService.updateHealthData(userService.getuser(userId));
            return Result.success("添加成功");
        }
        else
            return Result.error("添加失败");
    }

//    获取所有的用户运动记录
//    @ApiOperation(value = "获取指定用户的运动记录")
//    @ApiImplicitParam(
//            name = "userId",
//            value = "用户id",
//            required = true
//    )
//    @PostMapping("/getSportRecord")
//    public Result getSportRecord(@RequestParam String userId) {
//        System.out.println(userId);
//        User user = userService.selectById(userId);
//        if(user==null)
//            return Result.error("用户不存在");
//        List<sportsWithType> sport = sportRecordService.getSport(userId);
//        return Result.success(sport);
//    }
    //获取今天的运动记录
    @ApiOperation(value = "获取今天的运动记录")
    @ApiImplicitParam(
            name = "userId",
            value = "用户id",
            required = true
    )
    @GetMapping("/getSportRecordOne")
    public Result getSportRecordOne(@RequestParam String userId) {
        LocalDate now = LocalDate.now();
        List<SingleCar> todayRecord = sportRecordService.getTodayRecord(userId, now);
        return Result.success(todayRecord);
    }

//    获取前一天数据
    @GetMapping("/getSportRecordYes")
    public Result getSportRecordYes(@RequestParam String userId) {
        LocalDate now = LocalDate.now();
        LocalDate date = now.minusDays(1);
        System.out.println(date);
        //   LocalDate localDate = LocalDate.of(2024, 6, 20);
        List<SingleCar> todayRecord = sportRecordService.getTodayRecord(userId, date);
        return Result.success(todayRecord);
    }

//    好友榜
    @GetMapping("/getFriends")
    public Result getFriends(){
        List<Friends> friends = sportRecordService.getFriends();
        return Result.success(friends);
    }
}



@Data
@AllArgsConstructor
@NoArgsConstructor
class SportTypes{
    private String userId;
    private String sportId;
    private double totalconCar;
    private double totallastTime;
    private LocalDate date;
}
