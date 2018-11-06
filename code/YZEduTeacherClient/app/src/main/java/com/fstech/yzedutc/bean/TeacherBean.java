package com.fstech.yzedutc.bean;

import java.io.Serializable;

/**
 * Created by shaoxin on 18-4-9.
 * 教师用户信息的模型类
 */

public class TeacherBean implements Serializable {
    private int user_id;                // 用户id
    private String user_phone;          // 学生绑定用户手机号
    private String user_sex;            // 用户性别
    private int user_age;               // 年龄
    private String user_avatar;         // 用户头像
    private String teacher_id;          // 学生id
    private String teacher_name;        // 学生姓名

    public TeacherBean() {
    }

    public TeacherBean(int user_id, String user_phone, String user_sex, int user_age, String user_avatar, String teacher_id, String teacher_name) {
        this.user_id = user_id;
        this.user_phone = user_phone;
        this.user_sex = user_sex;
        this.user_age = user_age;
        this.user_avatar = user_avatar;
        this.teacher_id = teacher_id;
        this.teacher_name = teacher_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    @Override
    public String toString() {
        return "TeacherBean{" +
                "user_id=" + user_id +
                ", user_phone='" + user_phone + '\'' +
                ", user_sex='" + user_sex + '\'' +
                ", user_age=" + user_age +
                ", user_avatar='" + user_avatar + '\'' +
                ", teacher_id='" + teacher_id + '\'' +
                ", teacher_name='" + teacher_name + '\'' +
                '}';
    }
}
