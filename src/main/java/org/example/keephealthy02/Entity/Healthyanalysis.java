package org.example.keephealthy02.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("healthyanalysis")
public class Healthyanalysis {
    private Date date;

    private String userId;

    private double BMI;

    private Integer healthLevel;
}