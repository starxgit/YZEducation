package com.fstch.YZEduadmin.mapper;

import java.util.List;

import com.fstch.YZEduadmin.models.Enter;

public interface EnterMapper {
	List<Enter> findAll();
	List<Enter> findEntered();
	Enter findById(int enter_id);
	void addToEntered(int enter_id);
	void delEnter(int enter_id);
}
