package com.fstch.YZEduadmin.models;

import java.sql.Timestamp;

public class Sword {
	int sensitive_word_id;
	int school_id;
	String school_name;
	String sensitive_word;
	Timestamp gmt_create;
	Timestamp gmt_modified;
	String school_code;
	public String getSchool_code() {
		return school_code;
	}
	public void setSchool_code(String school_code) {
		this.school_code = school_code;
	}
	public int getSensitive_word_id() {
		return sensitive_word_id;
	}
	public void setSensitive_word_id(int sensitive_word_id) {
		this.sensitive_word_id = sensitive_word_id;
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
	public String getSensitive_word() {
		return sensitive_word;
	}
	public void setSensitive_word(String sensitive_word) {
		this.sensitive_word = sensitive_word;
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
	
}
