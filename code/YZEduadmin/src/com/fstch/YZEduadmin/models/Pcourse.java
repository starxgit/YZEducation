package com.fstch.YZEduadmin.models;

import java.sql.Timestamp;

public class Pcourse {
	int platform_course_id;
	int course_id;
	String course_code;
	String course_name;
	String course_introduce;
	String course_teacher;
	int teacher_id;
	public int getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}
	String teacher_name;
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	Timestamp gmt_create;
	Timestamp gmt_modified;
	public int getPlatform_course_id() {
		return platform_course_id;
	}
	public void setPlatform_course_id(int platform_course_id) {
		this.platform_course_id = platform_course_id;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public String getCourse_code() {
		return course_code;
	}
	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getCourse_introduce() {
		return course_introduce;
	}
	public void setCourse_introduce(String course_introduce) {
		this.course_introduce = course_introduce;
	}
	public String getCourse_teacher() {
		return course_teacher;
	}
	public void setCourse_teacher(String course_teacher) {
		this.course_teacher = course_teacher;
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
