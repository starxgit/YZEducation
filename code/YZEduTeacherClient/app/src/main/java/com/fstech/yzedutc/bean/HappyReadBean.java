package com.fstech.yzedutc.bean;

import java.io.Serializable;

/**
 * Created by shaoxin on 2018-05-20.
 * 悦读信息的模型类
 * 基本信息直接从happy_read读取
 */

public class HappyReadBean implements Serializable {

    private int happy_read_id;    // 悦读id
    private String happy_read_title;// 悦读标题
    private String happy_read_content; // 悦读文字内容
    private String happy_read_link; // 悦读链接地址
    private String happy_read_img; // 封面图片

    public HappyReadBean() {
    }

    public HappyReadBean(int happy_read_id, String happy_read_title, String happy_read_content,
                         String happy_read_link, String happy_read_img) {
        this.happy_read_id = happy_read_id;
        this.happy_read_title = happy_read_title;
        this.happy_read_content = happy_read_content;
        this.happy_read_link = happy_read_link;
        this.happy_read_img = happy_read_img;
    }

    public int getHappy_read_id() {
        return happy_read_id;
    }

    public void setHappy_read_id(int happy_read_id) {
        this.happy_read_id = happy_read_id;
    }

    public String getHappy_read_title() {
        return happy_read_title;
    }

    public void setHappy_read_title(String happy_read_title) {
        this.happy_read_title = happy_read_title;
    }

    public String getHappy_read_content() {
        return happy_read_content;
    }

    public void setHappy_read_content(String happy_read_content) {
        this.happy_read_content = happy_read_content;
    }

    public String getHappy_read_link() {
        return happy_read_link;
    }

    public void setHappy_read_link(String happy_read_link) {
        this.happy_read_link = happy_read_link;
    }

    public String getHappy_read_img() {
        return happy_read_img;
    }

    public void setHappy_read_img(String happy_read_img) {
        this.happy_read_img = happy_read_img;
    }

    @Override
    public String toString() {
        return "HappyReadBean{" +
                "happy_read_id=" + happy_read_id +
                ", happy_read_title='" + happy_read_title + '\'' +
                ", happy_read_content='" + happy_read_content + '\'' +
                ", happy_read_link='" + happy_read_link + '\'' +
                ", happy_read_img='" + happy_read_img + '\'' +
                '}';
    }
}