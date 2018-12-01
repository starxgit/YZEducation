package com.fstch.YZEduadmin.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fstch.YZEduadmin.mapper.LoginMapper;
import com.fstch.YZEduadmin.models.Login;
@Repository
public class LoginDao implements LoginMapper {
	@Autowired
	LoginMapper loginMapper;
	@Override
	public Login findByName(String admin_name) {
		// TODO Auto-generated method stub
		return loginMapper.findByName(admin_name);
	}
	
	
	@Override
	public void modify(com.fstch.YZEduadmin.models.Login login) {
		// TODO Auto-generated method stub
		loginMapper.modify(login);
	}


	



}
