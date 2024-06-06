package org.example.keephealthy02.Entity;

import java.util.Date;

public class Userdiet {
    private Date date;

    private Float consumecalories;

    private String userid;

    private String foods;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getConsumecalories() {
        return consumecalories;
    }

    public void setConsumecalories(Float consumecalories) {
        this.consumecalories = consumecalories;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getFoods() {
        return foods;
    }

    public void setFoods(String foods) {
        this.foods = foods == null ? null : foods.trim();
    }
}