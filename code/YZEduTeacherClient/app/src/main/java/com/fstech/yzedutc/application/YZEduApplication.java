package com.fstech.yzedutc.application;

import java.io.Serializable;

import android.app.Application;

public class YZEduApplication extends Application implements Serializable{
	private static final long serialVersionUID = 1L;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private String token;   // 用户登录凭证
    private String userName;   // 显示的用户名
    private String avatar;  // 用户头像
    private int user_type;  // 用户类型，0游客，1学生，2教师，3普通用户

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }
}
