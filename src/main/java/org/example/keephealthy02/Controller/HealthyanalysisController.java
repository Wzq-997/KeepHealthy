package org.example.keephealthy02.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.example.keephealthy02.Entity.Healthyanalysis;
import org.example.keephealthy02.Entity.User;
import org.example.keephealthy02.Service.HealthyanalysisService;
import org.example.keephealthy02.Service.Impl.HealthyanalysisImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/healthyanalysis")
@Api(tags = "健康分析Api")
public class HealthyanalysisController {
    @Autowired
    HealthyanalysisImpl healthyanalysis;

    @RequestMapping(value = "/getHealthyDate",method = RequestMethod.GET)
    @ApiOperation(value = "获取健康等级")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "user",
                    value = "用户",
                    required = true
            ),
            @ApiImplicitParam(
                    name = "date",
                    value = "日期",
                    required = true
            )
    })
        public Healthyanalysis getHealthLevle(@RequestBody User user, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
            return healthyanalysis.getHealthLevle(user,date);
        }

    @RequestMapping(value = "/insertHealthyData",method = RequestMethod.POST)
    @ApiOperation(value = "插入健康信息")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "user",
                    value = "用户",
                    required = true
            ),
            @ApiImplicitParam(
                    name = "date",
                    value = "日期",
                    required = true
            )
    })
    public Integer insertHealthyData(@RequestBody User user,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return healthyanalysis.insertHealthData(user,date);
    }

    @RequestMapping(value = "/getListHealthData",method = RequestMethod.POST)
    @ApiOperation(value = "获取一段日期内的健康等级")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "user",
                    value = "用户",
                    required = true
            ),
            @ApiImplicitParam(
                    name = "date",
                    value = "日期",
                    required = true
            ),
            @ApiImplicitParam(
                    name = "startDate",
                    value = "开始日期",
                    required = true
            ),
            @ApiImplicitParam(
                    name = "endDate",
                    value = "结束日期",
                    required = true
            )
    })
    public List<Healthyanalysis> getListHealthyData(@RequestBody User user,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
        return healthyanalysis.getListHealthyData(user,startDate,endDate);
    }

}
