package com.fstch.YZEduadmin.models;

import java.sql.Timestamp;

public class Bill {
	int bill_id;
	int user_id;
	String user_account;
	double bill_money;
	String bill_details;
	Timestamp gmt_create;
	Timestamp gmt_modified;
	public int getBill_id() {
		return bill_id;
	}
	public void setBill_id(int bill_id) {
		this.bill_id = bill_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_account() {
		return user_account;
	}
	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}
	public double getBill_money() {
		return bill_money;
	}
	public void setBill_money(double bill_money) {
		this.bill_money = bill_money;
	}
	public String getBill_details() {
		return bill_details;
	}
	public void setBill_details(String bill_details) {
		this.bill_details = bill_details;
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
