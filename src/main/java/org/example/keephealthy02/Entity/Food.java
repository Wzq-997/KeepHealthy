package org.example.keephealthy02.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("food")
public class Food {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "食物名")
    private String name;
    @ApiModelProperty(value = "卡路里")
    private Float calories;


}