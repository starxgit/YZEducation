package com.fstech.yzeduds.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fstech.yzeduds.model.ClassificationBean;
import com.fstech.yzeduds.model.CourseBean;

public interface CourseMapper {
    // 查找最新课程列表
    public List<CourseBean> newCourseList(@Param("school_id") int school_id,
            @Param("faculty_id") int faculty_id,
            @Param("class_id") int class_id, @Param("currIndex") int currIndex,
            @Param("pageSize") int pageSize);

    // 查找热门课程列表
    public List<CourseBean> topCourseList(@Param("school_id") int school_id,
            @Param("faculty_id") int faculty_id,
            @Param("class_id") int class_id, @Param("currIndex") int currIndex,
            @Param("pageSize") int pageSize);

    // 查找课程分类列表
    public List<ClassificationBean> classificationList(
            @Param("cfa_id") int cfa_id);

    // 查找分类下的课程列表
    public List<CourseBean> courseListByClassification(
            @Param("school_id") int school_id,
            @Param("faculty_id") int faculty_id,
            @Param("class_id") int class_id, @Param("currIndex") int currIndex,
            @Param("pageSize") int pageSize,
            @Param("course_classification") int course_classification);

    // 搜索课程列表
    public List<CourseBean> searchCourseList(@Param("school_id") int school_id,
            @Param("faculty_id") int faculty_id,
            @Param("class_id") int class_id, @Param("currIndex") int currIndex,
            @Param("pageSize") int pageSize, @Param("keyword") String keyword);
}
