package com.fstch.YZEduadmin.models;

import java.sql.Timestamp;

public class Banner {
	int banner_id;
	String school_id;
	String banner_image;
	String banner_type;
	String banner_link;
	Timestamp gmt_create;
	Timestamp gmt_modified;
	public int  getBanner_id() {
		return banner_id;
	}
	public void setBanner_id(int  banner_id) {
		this.banner_id = banner_id;
	}
	public String getSchool_id() {
		return school_id;
	}
	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}
	public String getBanner_image() {
		return banner_image;
	}
	public void setBanner_image(String banner_image) {
		this.banner_image = banner_image;
	}
	public String getBanner_type() {
		return banner_type;
	}
	public void setBanner_type(String banner_type) {
		this.banner_type = banner_type;
	}
	public String getBanner_link() {
		return banner_link;
	}
	public void setBanner_link(String banner_link) {
		this.banner_link = banner_link;
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
	@Override
	public String toString() {
		return "Banner [banner_id=" + banner_id + ", school_id=" + school_id
				+ ", banner_image=" + banner_image + ", banner_type="
				+ banner_type + ", banner_link=" + banner_link
				+ ", gmt_create=" + gmt_create + ", gmt_modified="
				+ gmt_modified + "]";
	}
	
	
}
