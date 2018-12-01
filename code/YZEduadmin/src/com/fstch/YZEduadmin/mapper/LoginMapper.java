package com.fstch.YZEduadmin.mapper;

import com.fstch.YZEduadmin.models.Login;

public interface LoginMapper {
	Login findByName(String admin_name);
	
	void modify(Login login);
}
