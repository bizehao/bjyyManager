package com.example.bjyymanager.entity;

import java.util.Date;

/**
 * 河道水情表
 */
public class Quyu_River {
    //区县id
    private int id;
    //站id
    private String stcd;
    //站名
    private String rtunm;
    //时间
    private Date tm;
    //水位
    private double z;
    //流量
    private double q;

    public Quyu_River() {
        super();
    }

    public Quyu_River(int id, String stcd, String rtunm, Date tm, double z, double q) {
        this.id = id;
        this.stcd = stcd;
        this.rtunm = rtunm;
        this.tm = tm;
        this.z = z;
        this.q = q;
    }

    @Override
    public String toString() {
        return "Quyu_River{" +
                "id=" + id +
                ", stcd='" + stcd + '\'' +
                ", rtunm='" + rtunm + '\'' +
                ", tm=" + tm +
                ", z=" + z +
                ", q=" + q +
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

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getQ() {
        return q;
    }

    public void setQ(double q) {
        this.q = q;
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
