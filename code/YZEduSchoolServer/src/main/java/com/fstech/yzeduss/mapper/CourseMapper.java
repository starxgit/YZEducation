package com.fstech.yzeduss.mapper;

import com.fstech.yzeduss.model.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By shaoxin On 1/1/19
 */
@Mapper
@Repository
public interface CourseMapper {
    // 所有课程列表
    public List<Course> findCourseBySchoolId(@Param("school_id")int school_id);

    // 所有实训列表
    public List<Course>findPracticalBySchoolId(@Param("school_id")int school_id);

}