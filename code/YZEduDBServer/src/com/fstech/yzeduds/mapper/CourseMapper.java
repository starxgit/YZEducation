package com.fstech.yzeduds.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;

import com.fstech.yzeduds.model.ClassificationBean;
import com.fstech.yzeduds.model.CourseBean;
import com.fstech.yzeduds.model.LessonBean;

public interface CourseMapper {
    // 查看这门课程详情
    public CourseBean findByCourseId(@Param("course_id") int course_id);

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

    // 学生-课程关系
    public int isLearnCourse(@Param("user_id") int user_id,
            @Param("course_id") int course_id);

    // 插入选课记录
    public int addMyCourse(@Param("user_id") int user_id,
            @Param("course_id") int course_id);

    // 我的课程列表（包括实训）
    public List<CourseBean> findCourseByUserId(@Param("user_id") int user_id);

    // 删除选课记录
    public int delMyCourse(@Param("course_id") int course_id,
            @Param("user_id") int user_id);
    
    // 课程学习人数自减
    public int decreCourseLearnNum(@Param("course_id") int course_id);
    
    // 课程学习人数自增
    public int increCourseLearnNum(@Param("course_id") int course_id);

}
