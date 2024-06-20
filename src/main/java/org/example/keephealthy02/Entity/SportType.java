package org.example.keephealthy02.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sporttype")
public class SportType {
    private String sportId;
    private String sportName;
    @ApiModelProperty(value = "标准卡路里")
    private double singleCalorie;
}
