package com.fstch.YZEduadmin.mapper;

import java.util.List;

import com.fstch.YZEduadmin.models.Pcourse;

public interface PcourseMapper {
	List<Pcourse> findAll();
	List<Pcourse> findCourse();
	void addPcourse(int course_id);
	void delPcourse(int platform_course_id);
}
