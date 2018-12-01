package com.fstch.YZEduadmin.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fstch.YZEduadmin.mapper.AdminMapper;
import com.fstch.YZEduadmin.models.Admin;
@Repository
public class AdminDao implements AdminMapper {
	@Autowired
	AdminMapper adminMapper;
	@Override
	public List<Admin> findAll() {
		// TODO Auto-generated method stub
		return adminMapper.findAll();
	}
	@Override
	public Admin findById(String admin_account) {
		// TODO Auto-generated method stub
		return adminMapper.findById(admin_account);
	}
	@Override
	public void addAdmin(Admin admin) {
		// TODO Auto-generated method stub
		adminMapper.addAdmin(admin);
	}
	@Override
	public void delAdmin(String admin_account) {
		// TODO Auto-generated method stub
		adminMapper.delAdmin(admin_account);
	}
	@Override
	public void modify(String admin_account) {
		// TODO Auto-generated method stub
		adminMapper.modify(admin_account);
	}

}
