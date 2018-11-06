package com.fstech.yzedusc.bean;

/**
 * Created by shaoxin on 2018-05-21.
 * 用户信息的模型类
 */

public class UserBean {

    private int user_id;          // user_id
    private String user_avatar;    // 用户头像
    private String user_phone;          // 用户手机号
    private String user_sex;            // 用户性别
    private int user_age;               // 年龄

    public UserBean() {
    }

    public UserBean(int user_id, String user_avatar, String user_phone, String user_sex, int user_age) {
        this.user_id = user_id;
        this.user_avatar = user_avatar;
        this.user_phone = user_phone;
        this.user_sex = user_sex;
        this.user_age = user_age;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public int getUser_age() {
        return user_age;
    }

    public void setUser_age(int user_age) {
        this.user_age = user_age;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "user_id=" + user_id +
                ", user_avatar='" + user_avatar + '\'' +
                ", user_phone='" + user_phone + '\'' +
                ", user_sex='" + user_sex + '\'' +
                ", user_age=" + user_age +
                '}';
    }
}
