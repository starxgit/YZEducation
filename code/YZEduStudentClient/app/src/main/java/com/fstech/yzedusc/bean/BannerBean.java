package com.fstech.yzedusc.bean;

import java.io.Serializable;

/**
 * Created by shaoxin on 18-4-6.
 * 轮播图的模型类
 */

public class BannerBean implements Serializable {
    private int banner_id;          // banner_id
    private String banner_image;    // banner图片路径
    private int banner_type;        // banner广告类型
    private String banner_link;     // banner连接内容

    public BannerBean() {
    }

    public BannerBean(int banner_id, String banner_image, int banner_type, String banner_link) {
        this.banner_id = banner_id;
        this.banner_image = banner_image;
        this.banner_type = banner_type;
        this.banner_link = banner_link;
    }

    public int getBanner_id() {
        return banner_id;
    }

    public void setBanner_id(int banner_id) {
        this.banner_id = banner_id;
    }

    public String getBanner_image() {
        return banner_image;
    }

    public void setBanner_image(String banner_image) {
        this.banner_image = banner_image;
    }

    public int getBanner_type() {
        return banner_type;
    }

    public void setBanner_type(int banner_type) {
        this.banner_type = banner_type;
    }

    public String getBanner_link() {
        return banner_link;
    }

    public void setBanner_link(String banner_link) {
        this.banner_link = banner_link;
    }

    @Override
    public String toString() {
        return "BannerBean{" +
                "banner_id=" + banner_id +
                ", banner_image='" + banner_image + '\'' +
                ", banner_type=" + banner_type +
                ", banner_link=" + banner_link +
                '}';
    }
}
