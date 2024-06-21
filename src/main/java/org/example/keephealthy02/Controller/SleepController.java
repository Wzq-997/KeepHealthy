package org.example.keephealthy02.Controller;

import io.swagger.annotations.ApiOperation;
import org.example.keephealthy02.Entity.Result;
import org.example.keephealthy02.Entity.Sleep;
import org.example.keephealthy02.Service.SleepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/sleep")
public class SleepController {
    @Autowired
    private SleepService sleepService;
    @ApiOperation(value = "测试方法")
    @GetMapping("/list")
    public List<Sleep> list(){

        return sleepService.list();
    }
    @ApiOperation(value = "查询本人的睡眠时间")
    @GetMapping("/CalculateSleepTime/{userId}")
    public String CalculateSleepTime(@PathVariable("userId") String userId){

        return sleepService.CalculateSleepTime(userId);
    }
    @ApiOperation(value = "查询本人的睡眠信息")
    @GetMapping("/MyInfo/{userId}")
    public Result MyInfo(@PathVariable("userId") String userId){

        List<Sleep> sleeps = sleepService.MyInfo(userId);
        return Result.success(sleeps);
    }
    //增删方法靠主外键

}