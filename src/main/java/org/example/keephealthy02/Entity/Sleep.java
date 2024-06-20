package org.example.keephealthy02.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@TableName("sleep")
@Data
public class Sleep {

    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "时间量")
    private Integer timeQuantume;
    @ApiModelProperty("起床时间")
    private LocalDateTime weak;
    @ApiModelProperty(value = "上床时间")
    private LocalDateTime bed;
    @ApiModelProperty(value = "日期")
    private LocalDate date;

}