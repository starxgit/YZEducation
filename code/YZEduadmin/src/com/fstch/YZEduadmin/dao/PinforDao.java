package com.fstch.YZEduadmin.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fstch.YZEduadmin.mapper.PinforMapper;
import com.fstch.YZEduadmin.models.Pinfor;
@Repository
public class PinforDao implements PinforMapper {
	@Autowired
	PinforMapper pinforMapper;
	@Override
	public List<Pinfor> findAll() {
		// TODO Auto-generated method stub
		return pinforMapper.findAll();
	}
	@Override
	public Pinfor findById(int platform_information_id) {
		// TODO Auto-generated method stub
		return pinforMapper.findById(platform_information_id);
	}
	@Override
	public void addPinfor(Pinfor pinfor) {
		// TODO Auto-generated method stub
		pinforMapper.addPinfor(pinfor);
	}
	@Override
	public void delPinfor(int platform_information_id) {
		// TODO Auto-generated method stub
		pinforMapper.delPinfor(platform_information_id);
	}
	@Override
	public void addPinforImg(Pinfor pinfor) {
		// TODO Auto-generated method stub
		pinforMapper.addPinforImg(pinfor);
	}
	@Override
	public void modify(Pinfor pinfor) {
		// TODO Auto-generated method stub
		pinforMapper.modify(pinfor);
	}

}
