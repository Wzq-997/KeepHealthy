package org.example.keephealthy02.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
@TableName("userdiet")
public class Userdiet {
    @ApiModelProperty(value = "食物")
    private String foods;

    @ApiModelProperty(value = "用户ID")
    private String userId;
    @ApiModelProperty(value = "食物ID")
    private String foodId;
    @ApiModelProperty(value = "日期")
    private LocalDate date;
    @ApiModelProperty(value = "消耗卡路里")
    private double consumeCalories;

}