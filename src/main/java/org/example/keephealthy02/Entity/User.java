package org.example.keephealthy02.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
    private String id;

    private String name;

    private String password;

    private Integer sex;

    private Integer age;

    private LocalDate birthday;

    private Double height;

    private Double weight;

    private Double targetweight;

    private String  pastMedicalHistory;
}