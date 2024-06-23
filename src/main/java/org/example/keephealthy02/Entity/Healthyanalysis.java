package org.example.keephealthy02.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("healthyanalysis")
public class Healthyanalysis {

    private LocalDate date;
    private String userId;

    private double bmi;

    private Integer healthLevel;
}