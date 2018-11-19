package com.fstech.yzeduds.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fstech.yzeduds.mapper.CourseMapper;
import com.fstech.yzeduds.model.ClassificationBean;
import com.fstech.yzeduds.model.CourseBean;
import com.fstech.yzeduds.model.LessonBean;

@Repository
public class CourseDao implements CourseMapper {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<CourseBean> newCourseList(int school_id, int faculty_id,
            int class_id, int currIndex, int pageSize) {
        return courseMapper.newCourseList(school_id, faculty_id, class_id,
                currIndex, pageSize);
    }

    @Override
    public List<CourseBean> topCourseList(int school_id, int faculty_id,
            int class_id, int currIndex, int pageSize) {
        return courseMapper.topCourseList(school_id, faculty_id, class_id,
                currIndex, pageSize);
    }

    @Override
    public List<CourseBean> searchCourseList(int school_id, int faculty_id,
            int class_id, int currIndex, int pageSize, String keyword) {
        return courseMapper.searchCourseList(school_id, faculty_id, class_id,
                currIndex, pageSize, "%" + keyword + "%");
    }

    @Override
    public List<ClassificationBean> classificationList(int cfa_id) {
        return courseMapper.classificationList(cfa_id);
    }

    @Override
    public List<CourseBean> courseListByClassification(int school_id,
            int faculty_id, int class_id, int currIndex, int pageSize,
            int cfa_id) {
        return courseMapper.courseListByClassification(school_id, faculty_id,
                class_id, currIndex, pageSize, cfa_id);
    }

    @Override
    public CourseBean findByCourseId(int course_id) {
        return courseMapper.findByCourseId(course_id);
    }

    @Override
    public int isLearnCourse(int user_id, int course_id) {
        return courseMapper.isLearnCourse(user_id, course_id);
    }

    @Override
    public int addMyCourse(int user_id, int course_id) {
        return courseMapper.addMyCourse(user_id, course_id);
    }

    @Override
    public List<CourseBean> findCourseByUserId(int user_id) {
        return courseMapper.findCourseByUserId(user_id);
    }

    @Override
    public int delMyCourse(int course_id, int user_id) {
        return courseMapper.delMyCourse(course_id, user_id);
    }

    @Override
    public int decreCourseLearnNum(int course_id) {
        return courseMapper.decreCourseLearnNum(course_id);
    }

    @Override
    public int increCourseLearnNum(int course_id) {
        return courseMapper.increCourseLearnNum(course_id);
    }

}
