package org.example.keephealthy02.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
    private String userId;

//    睡眠总时长
    private Integer timeQuantume;

//    起床时间
    private LocalDateTime weak;
//  入睡
    private LocalDateTime bed;

    private LocalDate date;

}