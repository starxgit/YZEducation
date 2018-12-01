package com.fstch.YZEduadmin.models;

import java.sql.Timestamp;

public class Pinfor {
	int platform_information_id;
	String platform_information_title;
	String platform_information_content;
	String platform_information_author;
	int information_image_count;
	Timestamp gmt_create;
	Timestamp gmt_modified;
	
	//图片表
	int platform_image_id;
	String platform_image_url;
	public int getPlatform_image_id() {
		return platform_image_id;
	}
	public void setPlatform_image_id(int platform_image_id) {
		this.platform_image_id = platform_image_id;
	}
	public String getPlatform_image_url() {
		return platform_image_url;
	}
	public void setPlatform_image_url(String platform_image_url) {
		this.platform_image_url = platform_image_url;
	}
	
		
	
	public int getPlatform_information_id() {
		return platform_information_id;
	}
	public void setPlatform_information_id(int platform_information_id) {
		this.platform_information_id = platform_information_id;
	}
	public String getPlatform_information_title() {
		return platform_information_title;
	}
	public void setPlatform_information_title(String platform_information_title) {
		this.platform_information_title = platform_information_title;
	}
	public String getPlatform_information_content() {
		return platform_information_content;
	}
	public void setPlatform_information_content(String platform_information_content) {
		this.platform_information_content = platform_information_content;
	}
	public String getPlatform_information_author() {
		return platform_information_author;
	}
	public void setPlatform_information_author(String platform_information_author) {
		this.platform_information_author = platform_information_author;
	}
	public int getInformation_image_count() {
		return information_image_count;
	}
	public void setInformation_image_count(int information_image_count) {
		this.information_image_count = information_image_count;
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
