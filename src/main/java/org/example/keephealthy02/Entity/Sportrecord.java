package org.example.keephealthy02.Entity;

import java.util.Date;

public class Sportrecord {
    private Date date;

    private Float consumecalories;

    private String userid;

    private String type;
    private double lastTime;

    public double getLastTime() {
        return lastTime;
    }

    public void setLastTime(double lastTime) {
        this.lastTime = lastTime;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}