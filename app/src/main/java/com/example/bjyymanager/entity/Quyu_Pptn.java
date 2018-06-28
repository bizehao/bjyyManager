package com.example.bjyymanager.entity;

import java.util.Date;

/**
 * 降水量表
 */
public class Quyu_Pptn {
    //区县id
    private int id;
    //站id
    private String stcd;
    //站名
    private String rtunm;
    //时间
    private Date tm;
    //时间降水量
    private double drp;


    public Quyu_Pptn() {
        super();
    }

    public Quyu_Pptn(int id, String stcd, String rtunm, Date tm, double drp) {
        this.id = id;
        this.stcd = stcd;
        this.rtunm = rtunm;
        this.tm = tm;
        this.drp = drp;
    }

    @Override
    public String toString() {
        return "Quyu_Pptn{" +
                "id=" + id +
                ", stcd='" + stcd + '\'' +
                ", rtunm='" + rtunm + '\'' +
                ", tm=" + tm +
                ", drp=" + drp +
                '}';
    }

    public int getQuyu() {
        return id;
    }

    public void setQuyu(int id) {
        this.id = id;
    }

    public Date getTm() {
        return tm;
    }

    public void setTm(Date tm) {
        this.tm = tm;
    }

    public double getDrp() {
        return drp;
    }

    public void setDrp(double drp) {
        this.drp = drp;
    }

    public String getRtunm() {
        return rtunm;
    }

    public void setRtunm(String rtunm) {
        this.rtunm = rtunm;
    }

    public String getStcd() {
        return stcd;
    }

    public void setStcd(String stcd) {
        this.stcd = stcd;
    }

}
