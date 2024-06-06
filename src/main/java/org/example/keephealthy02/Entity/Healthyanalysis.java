package org.example.keephealthy02.Entity;

import java.util.Date;

public class Healthyanalysis {
    private Date date;

    private String userid;

    private Float bmi;

    private Integer healthlevel;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public Float getBmi() {
        return bmi;
    }

    public void setBmi(Float bmi) {
        this.bmi = bmi;
    }

    public Integer getHealthlevel() {
        return healthlevel;
    }

    public void setHealthlevel(Integer healthlevel) {
        this.healthlevel = healthlevel;
    }
}