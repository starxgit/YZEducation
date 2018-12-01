package com.fstch.YZEduadmin.mapper;

import java.util.List;

import com.fstch.YZEduadmin.models.School;

public interface SchoolMapper {
	List<School> findAll();
	List<School> findOrg();
	School findById(String school_code);
	void addSchool(School school);
	void delSchool(String school_code);
	void modify(School school);
}
