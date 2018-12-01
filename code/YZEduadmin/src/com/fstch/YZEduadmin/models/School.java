package com.fstch.YZEduadmin.models;

import java.sql.Timestamp;


public class School {
    String school_id;
	String school_name;
	String school_introduce;
	String school_city;
	String school_province;
	String school_type;
	String school_badge;
	String school_background;
	Timestamp gmt_create;
	Timestamp gmt_modified;
	public String getSchool_badge() {
		return school_badge;
	}
	public void setSchool_badge(String school_badge) {
		this.school_badge = school_badge;
	}
	public String getSchool_background() {
		return school_background;
	}
	public void setSchool_background(String school_background) {
		this.school_background = school_background;
	}
	public Timestamp getGmt_create() {
		return gmt_create;
	}
	public void setGmt_create(Timestamp gmt_create) {
		this.gmt_create = gmt_create;
	}
	public Timestamp getGmt_modified() {
		return gmt_modified;
	}
	public void setGmt_modified(Timestamp gmt_modified) {
		this.gmt_modified = gmt_modified;
	}
	
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public String getSchool_introduce() {
		return school_introduce;
	}
	public void setSchool_introduce(String school_introduce) {
		this.school_introduce = school_introduce;
	}
	public String getSchool_city() {
		return school_city;
	}
	public void setSchool_city(String school_city) {
		this.school_city = school_city;
	}
	public String getSchool_province() {
		return school_province;
	}
	public void setSchool_province(String school_province) {
		this.school_province = school_province;
	}
	public String getSchool_type() {
		return school_type;
	}
	public void setSchool_type(String school_type) {
		this.school_type = school_type;
	}
    public String getSchool_id() {
        return school_id;
    }
    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }
	
	
}
