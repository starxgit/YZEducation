package com.fstech.yzeduds.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by shaoxin on 18-11-13. 显示咨询列表的模型类
 */

public class InformationBean implements Serializable{
    private static final long serialVersionUID = -1;
    
    private int information_id; // 资讯id
    private String information_title; // 资讯标题
    private String information_cover; // 资讯图片路径(如果没有设为null)
    private String information_date; // 资讯日期(直接取字符串)

    public InformationBean() {
    }

    public InformationBean(int information_id, String information_title,
            String information_cover, String information_date) {
        super();
        this.information_id = information_id;
        this.information_title = information_title;
        this.information_cover = information_cover;
        this.information_date = information_date;
    }

    public int getInformation_id() {
        return information_id;
    }

    public void setInformation_id(int information_id) {
        this.information_id = information_id;
    }

    public String getInformation_title() {
        return information_title;
    }

    public void setInformation_title(String information_title) {
        this.information_title = information_title;
    }

    public String getInformation_cover() {
        return information_cover;
    }

    public void setInformation_cover(String information_cover) {
        this.information_cover = information_cover;
    }

    public String getInformation_date() {
        return information_date;
    }

    public void setInformation_date(String information_date) {
        this.information_date = information_date;
    }

    @Override
    public String toString() {
        return "InformationBean [information_id=" + information_id
                + ", information_title=" + information_title
                + ", information_cover=" + information_cover
                + ", information_date=" + information_date + "]";
    }

}
