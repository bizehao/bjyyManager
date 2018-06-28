package com.example.bjyymanager.entity;

/**
 * Created by Administrator on 2017\11\16 0016.
 */

public class Station {
    //站id
    private String stcd;
    //站名
    private String rtuNM;
    //记录数
    private int count;

    public Station() {
        super();
    }

    public Station(String stcd, String rtuNM, int count) {
        this.stcd = stcd;
        this.rtuNM = rtuNM;
        this.count = count;
    }

    @Override
    public String toString() {
        return "St_info{" +
                "stcd='" + stcd + '\'' +
                ", rtuNM='" + rtuNM + '\'' +
                ", count=" + count +
                '}';
    }

    public String getRtuNM() {
        return rtuNM;
    }

    public void setRtuNM(String rtuNM) {
        this.rtuNM = rtuNM;
    }

    public String getStcd() {

        return stcd;
    }

    public void setStcd(String stcd) {
        this.stcd = stcd;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
