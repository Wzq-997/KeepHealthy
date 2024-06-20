package org.example.keephealthy02.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@TableName("sportrecord")
@NoArgsConstructor
@AllArgsConstructor
public class Sportrecord {
    private LocalDate date;
    private String userId;
    private String sportId;
//    消耗卡路里

    private double consumeCalories;
    private double lastTime;
}