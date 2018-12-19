package com.fstech.yzedutc.bean;

/**
 * Created by shaoxin on 11/16/18.
 * 用户基本信息的模型类
 */

public class UserInfoBean {
    // 用户可以看到的参数
    private String student_name;
    private String teacher_name;
    private String user_account;
    private String user_avatar;
    private int user_type;  // 用户类型，1学生，2教师，3其他用户

    public UserInfoBean() {
        super();
    }

    public UserInfoBean(String student_name, String teacher_name,
                        String user_account, String user_avatar, int user_type) {
        super();
        this.student_name = student_name;
        this.teacher_name = teacher_name;
        this.user_account = user_account;
        this.user_avatar = user_avatar;
        this.user_type = user_type;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    @Override
    public String toString() {
        return "UserInfoBean [student_name=" + student_name + ", teacher_name="
                + teacher_name + ", user_account=" + user_account
                + ", user_avatar=" + user_avatar + ", user_type=" + user_type
                + "]";
    }
}
