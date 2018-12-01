package com.fstch.YZEduadmin.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fstch.YZEduadmin.mapper.EnterMapper;
import com.fstch.YZEduadmin.models.Enter;
@Repository
public class EnterDao implements EnterMapper {
	@Autowired
	EnterMapper enterMapper;
	@Override
	public List<Enter> findAll() {
		// TODO Auto-generated method stub
		return enterMapper.findAll();
	}
	@Override
	public List<Enter> findEntered() {
		// TODO Auto-generated method stub
		return enterMapper.findEntered();
	}
	@Override
	public Enter findById(int enter_id) {
		// TODO Auto-generated method stub
		return enterMapper.findById(enter_id);
	}
	@Override
	public void addToEntered(int enter_id) {
		// TODO Auto-generated method stub
		enterMapper.addToEntered(enter_id);
	}
	@Override
	public void delEnter(int enter_id) {
		// TODO Auto-generated method stub
		enterMapper.delEnter(enter_id);
	}

}
