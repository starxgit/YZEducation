package com.fstech.yzeduds.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fstech.yzeduds.dao.CourseDao;
import com.fstech.yzeduds.model.BannerBean;
import com.fstech.yzeduds.model.ClassificationBean;
import com.fstech.yzeduds.model.CourseBean;
import com.fstech.yzeduds.util.Constant;
import com.fstech.yzeduds.util.ResponseUtil;
import com.fstech.yzeduds.util.TokenUtil;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseDao courseDao;

    /**
     * 最新课程列表
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public void newCourse(@RequestParam Integer page,
            @RequestParam String token, HttpServletResponse response) {
        int school_id = TokenUtil.decodeSchoolId(token);
        int faculty_id = TokenUtil.decodeFacultyId(token);
        int class_id = TokenUtil.decodeClassId(token);
        List<CourseBean> courseList = courseDao.newCourseList(school_id,
                faculty_id, class_id, (page - 1) * Constant.COURSE_PAGE_SIZE,
                Constant.COURSE_PAGE_SIZE);
        ResponseUtil.normalResponse(response, courseList);
    }

    /**
     * 热门课程列表
     */
    @RequestMapping(value = "top", method = RequestMethod.GET)
    public void topCourse(@RequestParam Integer page,
            @RequestParam String token, HttpServletResponse response) {
        int school_id = TokenUtil.decodeSchoolId(token);
        int faculty_id = TokenUtil.decodeFacultyId(token);
        int class_id = TokenUtil.decodeClassId(token);
        List<CourseBean> courseList = courseDao.topCourseList(school_id,
                faculty_id, class_id, (page - 1) * Constant.COURSE_PAGE_SIZE,
                Constant.COURSE_PAGE_SIZE);
        ResponseUtil.normalResponse(response, courseList);
    }

    /**
     * 课程分类列表
     */
    @RequestMapping(value = "classification", method = RequestMethod.GET)
    public void classification(@RequestParam Integer classification_own,
            HttpServletResponse response) {
        List<ClassificationBean> classification = courseDao
                .classificationList(classification_own);
        ResponseUtil.normalResponse(response, classification);
    }

    /**
     * 该分类下的课程列表
     */
    @RequestMapping(value = "classificationCourse", method = RequestMethod.GET)
    public void classificationCourse(@RequestParam Integer page,
            @RequestParam String token, @RequestParam int classification_own,
            HttpServletResponse response) {
        int school_id = TokenUtil.decodeSchoolId(token);
        int faculty_id = TokenUtil.decodeFacultyId(token);
        int class_id = TokenUtil.decodeClassId(token);
        List<CourseBean> courseList = courseDao.courseListByClassification(school_id,
                faculty_id, class_id, (page - 1) * Constant.COURSE_PAGE_SIZE,
                Constant.COURSE_PAGE_SIZE, classification_own);
        ResponseUtil.normalResponse(response, courseList);
    }

    /**
     * 搜索课程列表
     */
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public void searchCourse(@RequestParam Integer page,
            @RequestParam String token, @RequestParam String keyword,
            HttpServletResponse response) {
        int school_id = TokenUtil.decodeSchoolId(token);
        int faculty_id = TokenUtil.decodeFacultyId(token);
        int class_id = TokenUtil.decodeClassId(token);
        List<CourseBean> courseList = courseDao.searchCourseList(school_id,
                faculty_id, class_id, (page - 1) * Constant.COURSE_PAGE_SIZE,
                Constant.COURSE_PAGE_SIZE, keyword);
        ResponseUtil.normalResponse(response, courseList);
    }
}
