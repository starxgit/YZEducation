package com.fstch.YZEduadmin.mapper;

import java.util.List;

import com.fstch.YZEduadmin.models.Pinfor;

public interface PinforMapper {
	List<Pinfor> findAll();
	Pinfor findById(int platform_information_id);
	void addPinfor(Pinfor pinfor);
	void delPinfor(int platform_information_id);
	void addPinforImg(Pinfor pinfor);
	void modify(Pinfor pinfor);
}
