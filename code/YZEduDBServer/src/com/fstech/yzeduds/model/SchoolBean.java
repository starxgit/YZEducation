package com.fstech.yzeduds.model;

/**
 * Created by shaoxin on 11/17/18. 院校基本信息模型类
 */

public class SchoolBean {
    private String school_name; // 校名
    private String school_badge; // 校徽
    private String school_introduce; // 院校介绍
    private String school_background; // 背景图片
    private String school_city; // 所在城市
    private String school_province; // 所在省份
    private int school_type; // 院校类型（1中小学，2高等院校，3培训机构）

    public SchoolBean() {
    }

    public SchoolBean(String school_name, String school_badge,
            String school_introduce, String school_background,
            String school_city, String school_province, int school_type) {
        this.school_name = school_name;
        this.school_badge = school_badge;
        this.school_introduce = school_introduce;
        this.school_background = school_background;
        this.school_city = school_city;
        this.school_province = school_province;
        this.school_type = school_type;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getSchool_badge() {
        return school_badge;
    }

    public void setSchool_badge(String school_badge) {
        this.school_badge = school_badge;
    }

    public String getSchool_introduce() {
        return school_introduce;
    }

    public void setSchool_introduce(String school_introduce) {
        this.school_introduce = school_introduce;
    }

    public String getSchool_background() {
        return school_background;
    }

    public void setSchool_background(String school_background) {
        this.school_background = school_background;
    }

    public String getSchool_city() {
        return school_city;
    }

    public void setSchool_city(String school_city) {
        this.school_city = school_city;
    }

    public String getschool_province() {
        return school_province;
    }

    public void setschool_province(String school_province) {
        this.school_province = school_province;
    }

    public int getSchool_type() {
        return school_type;
    }

    public void setSchool_type(int school_type) {
        this.school_type = school_type;
    }

    @Override
    public String toString() {
        return "SchoolBean{" + "school_name='" + school_name + '\''
                + ", school_badge='" + school_badge + '\''
                + ", school_introduce='" + school_introduce + '\''
                + ", school_background='" + school_background + '\''
                + ", school_city='" + school_city + '\''
                + ", school_province='" + school_province + '\''
                + ", school_type=" + school_type + '}';
    }
}
