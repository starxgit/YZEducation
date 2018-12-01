package com.fstch.YZEduadmin.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fstch.YZEduadmin.mapper.SchoolMapper;
import com.fstch.YZEduadmin.models.School;
@Repository
public class SchoolDao implements SchoolMapper {
	@Autowired
	SchoolMapper schoolMapper;
	@Override
	public List<School> findAll() {
		// TODO Auto-generated method stub
		return schoolMapper.findAll();
	}
	@Override
	public School findById(String school_code){
		return schoolMapper.findById(school_code);
		
	}
	@Override
	public void addSchool(School school){
		schoolMapper.addSchool(school);
	}
	@Override
	public void delSchool(String school_code){
		schoolMapper.delSchool(school_code);
	}
	@Override
	public void modify(School school){
		schoolMapper.modify(school);
	}
	@Override
	public List<School> findOrg() {
		// TODO Auto-generated method stub
		return schoolMapper.findOrg();
	}

}
