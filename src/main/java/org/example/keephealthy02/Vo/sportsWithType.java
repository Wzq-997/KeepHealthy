package org.example.keephealthy02.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.keephealthy02.Entity.SportType;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class sportsWithType {
//    获取运动记录，运动记录和运动种类联合查询
      private LocalDate date;
      private String sportId;
      private double consumeCalories;
      private String userId;
      private double lastTime;
//      private List<SportType> sportTypes;
      private SportType sportType;
}
