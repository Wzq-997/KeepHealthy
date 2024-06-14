package org.example.keephealthy02.Entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

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
    private Date birthday;
    @ApiModelProperty(value = "身高")
    private Float height;
    @ApiModelProperty(value = "体重")
    private Float weight;
    @ApiModelProperty(value = "目标体重")
    private String targetweight;
    @ApiModelProperty(value = "既往病史")
    private String pastMedicalHistory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getTargetweight() {
        return targetweight;
    }

    public void setTargetweight(String targetweight) {
        this.targetweight = targetweight == null ? null : targetweight.trim();
    }

    public String getPastMedicalHistory() {
        return pastMedicalHistory;
    }

    public void setPastMedicalHistory(String pastMedicalHistory) {
        this.pastMedicalHistory = pastMedicalHistory == null ? null : pastMedicalHistory.trim();
    }
}