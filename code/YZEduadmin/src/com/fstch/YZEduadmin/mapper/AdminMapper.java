package com.fstch.YZEduadmin.mapper;

import java.util.List;

import com.fstch.YZEduadmin.models.Admin;

public interface AdminMapper {
	List<Admin> findAll();
	Admin findById(String admin_account);
	void addAdmin(Admin admin);
	void delAdmin(String admin_account);
	void modify(String admin_account);
}
