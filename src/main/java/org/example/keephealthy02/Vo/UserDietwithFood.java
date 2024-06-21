package org.example.keephealthy02.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDietwithFood {
    private  String userId;
    private  String date;
    private  double consumeCalories;
    private  String name;

}
