package com.fstech.yzedutc.bean;

import java.io.Serializable;

/**
 * Created by shaoxin on 18-4-9.
 * 学友圈动态图片的模型类
 */

public class DynamicImage implements Serializable {
    private int dynamic_image_id;           // 图片id
    private int circle_dynamic_id;          // 所属学友圈动态id
    private String dynamic_image_url;       // 图片路径

    public DynamicImage() {
    }

    public DynamicImage(int dynamic_image_id, int circle_dynamic_id, String dynamic_image_url) {
        this.dynamic_image_id = dynamic_image_id;
        this.circle_dynamic_id = circle_dynamic_id;
        this.dynamic_image_url = dynamic_image_url;
    }

    public int getDynamic_image_id() {
        return dynamic_image_id;
    }

    public void setDynamic_image_id(int dynamic_image_id) {
        this.dynamic_image_id = dynamic_image_id;
    }

    public int getCircle_dynamic_id() {
        return circle_dynamic_id;
    }

    public void setCircle_dynamic_id(int circle_dynamic_id) {
        this.circle_dynamic_id = circle_dynamic_id;
    }

    public String getDynamic_image_url() {
        return dynamic_image_url;
    }

    public void setDynamic_image_url(String dynamic_image_url) {
        this.dynamic_image_url = dynamic_image_url;
    }

    @Override
    public String toString() {
        return "DynamicImage{" +
                "dynamic_image_id=" + dynamic_image_id +
                ", circle_dynamic_id=" + circle_dynamic_id +
                ", dynamic_image_url='" + dynamic_image_url + '\'' +
                '}';
    }
}
