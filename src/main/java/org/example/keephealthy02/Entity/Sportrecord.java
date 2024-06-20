package org.example.keephealthy02.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@TableName("sportrecord")
@NoArgsConstructor
@AllArgsConstructor
public class Sportrecord {

    @ApiModelProperty(value = "类型")
    private String type;
    @ApiModelProperty(value = "日期")
    private LocalDate date;
    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "运动id")
    private String sportId;
    @ApiModelProperty(value = "消耗卡路里")
    private Double consumeCalories;
    private double lastTime;
}