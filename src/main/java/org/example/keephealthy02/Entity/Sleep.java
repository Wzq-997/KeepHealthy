package org.example.keephealthy02.Entity;

import java.util.Date;

public class Sleep {
    private String userid;

    private Integer timequantume;

    private Date weak;

    private Date bed;

    private Date date;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public Integer getTimequantume() {
        return timequantume;
    }

    public void setTimequantume(Integer timequantume) {
        this.timequantume = timequantume;
    }

    public Date getWeak() {
        return weak;
    }

    public void setWeak(Date weak) {
        this.weak = weak;
    }

    public Date getBed() {
        return bed;
    }

    public void setBed(Date bed) {
        this.bed = bed;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}