package com.fstch.YZEduadmin.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fstch.YZEduadmin.mapper.PcourseMapper;
import com.fstch.YZEduadmin.models.Pcourse;
@Repository
public class PcourseDao implements PcourseMapper {
	@Autowired
	PcourseMapper pcourseMapper;
	@Override
	public List<Pcourse> findAll() {
		// TODO Auto-generated method stub
		return pcourseMapper.findAll();
	}
	@Override
	public List<Pcourse> findCourse() {
		// TODO Auto-generated method stub
		return pcourseMapper.findCourse();
	}
	@Override
	public void addPcourse(int course_id) {
		// TODO Auto-generated method stub
		pcourseMapper.addPcourse(course_id);
	}
	@Override
	public void delPcourse(int platform_course_id) {
		// TODO Auto-generated method stub
		pcourseMapper.delPcourse(platform_course_id);
	}

}
