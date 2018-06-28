package com.example.bjyymanager.entity;

/**
 * Created by Administrator on 2017\11\16 0016.
 */

public class Zhan {
    private int id;
    private String zhanName;
    private int ImageId;
    private int biaoId;

    public Zhan(int id, String zhanName, int imageId, int biaoId) {
        this.id = id;
        this.zhanName = zhanName;
        this.ImageId = imageId;
        this.biaoId = biaoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZhanName() {
        return zhanName;
    }

    public void setZhanName(String zhanName) {
        this.zhanName = zhanName;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public int getBiaoId() {
        return biaoId;
    }

    public void setBiaoId(int biaoId) {
        this.biaoId = biaoId;
    }
}
