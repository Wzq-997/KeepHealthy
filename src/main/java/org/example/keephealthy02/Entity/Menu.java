package org.example.keephealthy02.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("menu")
public class Menu {
    private String menuId;
    private String name;
    private String make;
    private String image;
    private String tips;
    private double calorie;
}
