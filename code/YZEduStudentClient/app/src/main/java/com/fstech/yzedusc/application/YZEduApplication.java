package com.fstech.yzedusc.application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Application;

import com.fstech.yzedusc.bean.MistakeBean;

public class YZEduApplication extends Application implements Serializable{
	private static final long serialVersionUID = 1L;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private int user_id;
	private String user_avatar;
	private String user_phone;
	private String user_sex;
	private int user_age;

	private int student_id;
	private String student_name;
	private String student_number;

	private int school_id;
	private String school_name;

	private List<MistakeBean> mistakes = new ArrayList<>();

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public List<MistakeBean> getMistakes() {
        return mistakes;
    }

    public void setMistakes(List<MistakeBean> mistakes) {
        this.mistakes = mistakes;
    }
}
