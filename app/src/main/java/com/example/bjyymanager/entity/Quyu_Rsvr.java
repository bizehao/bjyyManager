package com.example.bjyymanager.entity;

import java.util.Date;

/**
 * 水库水清表
 */
public class Quyu_Rsvr {
    //区县id
    private int id;
    //站id
    private String stcd;
    //站名
    private String rtunm;
    //时间
    private Date tm;
    //库水位
    private double rz;
    //蓄水量
    private double w;

    public Quyu_Rsvr() {
        super();
    }

    public Quyu_Rsvr(int id, String stcd, String rtunm, Date tm, double rz, double w) {
        this.id = id;
        this.stcd = stcd;
        this.rtunm = rtunm;
        this.tm = tm;
        this.rz = rz;
        this.w = w;
    }

    @Override
    public String toString() {
        return "Quyu_Rsvr{" +
                "id=" + id +
                ", stcd='" + stcd + '\'' +
                ", rtunm='" + rtunm + '\'' +
                ", tm=" + tm +
                ", rz=" + rz +
                ", w=" + w +
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

    public double getRz() {
        return rz;
    }

    public void setRz(double rz) {
        this.rz = rz;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
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
