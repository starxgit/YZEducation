package com.fstch.YZEduadmin.models;

import java.sql.Timestamp;

public class Admin {
	String admin_name;//管理员名称
	String admin_account;//管理员登录账号
	String admin_password;//管理员登录密码
	String admin_authority;//管理员权限
	Timestamp gmt_create;//创建时间
	Timestamp gmt_modified;//修改时间
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getAdmin_account() {
		return admin_account;
	}
	public void setAdmin_account(String admin_account) {
		this.admin_account = admin_account;
	}
	public String getAdmin_password() {
		return admin_password;
	}
	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}
	public String getAdmin_authority() {
		return admin_authority;
	}
	public void setAdmin_authority(String admin_authority) {
		this.admin_authority = admin_authority;
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
