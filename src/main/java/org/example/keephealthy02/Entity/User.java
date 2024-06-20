package org.example.keephealthy02.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User {
    @ApiModelProperty(value = "用户唯一标识")
    private String id;
    @ApiModelProperty(value = "用户名")
    private String name;
    @ApiModelProperty(value = "密码")

    private String password;
    @ApiModelProperty(value = "性别")
    private Integer sex;
    @ApiModelProperty(value = "生日")
    private LocalDate birthday;
    @ApiModelProperty(value = "身高")
    private Double height;
    @ApiModelProperty(value = "体重")
    private Double weight;
    @ApiModelProperty(value = "目标体重")
    private Double targetweight;
    @ApiModelProperty(value = "既往病史")
    private String pastMedicalHistory;

}