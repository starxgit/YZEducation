package com.fstech.yzeduds.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fstech.yzeduds.dao.CourseDao;
import com.fstech.yzeduds.dao.LessonDao;
import com.fstech.yzeduds.dao.TaskDao;
import com.fstech.yzeduds.model.BannerBean;
import com.fstech.yzeduds.model.ClassificationBean;
import com.fstech.yzeduds.model.CourseBean;
import com.fstech.yzeduds.model.LessonBean;
import com.fstech.yzeduds.util.Constant;
import com.fstech.yzeduds.util.ErrorCode;
import com.fstech.yzeduds.util.ResponseUtil;
import com.fstech.yzeduds.util.TokenUtil;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private LessonDao lessonDao;
    @Autowired
    private TaskDao taskDao;

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
        List<CourseBean> courseList = courseDao.courseListByClassification(
                school_id, faculty_id, class_id, (page - 1)
                        * Constant.COURSE_PAGE_SIZE, Constant.COURSE_PAGE_SIZE,
                classification_own);
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

    /**
     * 查看课程详情
     * */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public void courseDetail(@RequestParam String token,
            @RequestParam Integer course_id, HttpServletResponse response) {
        CourseBean courseBean = courseDao.findByCourseId(course_id);
        if (courseBean != null) {
            int user_id = TokenUtil.decodeUserId(token);
            int isLearn = courseDao.isLearnCourse(user_id, course_id);
            JSONObject return_data = new JSONObject();
            return_data.put("isLearn", isLearn);
            return_data.put("courseBean", courseBean);
            ResponseUtil.normalResponse(response, return_data);
        } else {
            ResponseUtil.errorResponse(response, null,
                    ErrorCode.CODE_COURSE_NOT_EXIST,
                    ErrorCode.MESSAGE_COURSE_NOT_EXIST);
        }
    }

    /**
     * 查看课程目录
     * */
    @RequestMapping(value = "catalog", method = RequestMethod.GET)
    public void courseCatalog(@RequestParam Integer course_id,
            HttpServletResponse response) {
        CourseBean courseBean = courseDao.findByCourseId(course_id);
        if (courseBean != null) {
            int courseType = courseBean.getCourse_type();
            List<LessonBean> lessonList = new ArrayList<LessonBean>();
            if (courseType == 3) {
                // 返回实训章节列表
                lessonList = taskDao.findListByCourseId(course_id);
            } else {
                // 返回课程章节列表
                lessonList = lessonDao.findListByCourseId(course_id);
            }
            ResponseUtil.normalResponse(response, lessonList);
        } else {
            ResponseUtil.errorResponse(response, null,
                    ErrorCode.CODE_COURSE_NOT_EXIST,
                    ErrorCode.MESSAGE_COURSE_NOT_EXIST);
        }
    }

    /**
     * 我的课程列表
     * */
    @RequestMapping(value = "myList", method = RequestMethod.GET)
    public void myCourseList(@RequestParam String token,
            HttpServletResponse response) {
        int user_id = TokenUtil.decodeUserId(token);
        List<CourseBean> myCourseList = courseDao.findCourseByUserId(user_id);
        ResponseUtil.normalResponse(response, myCourseList);
    }

    /**
     * 报名选课
     * */
    @RequestMapping(value = "joinCourse", method = RequestMethod.GET)
    public void joinCourse(@RequestParam String token,
            @RequestParam Integer course_id, HttpServletResponse response) {
        int user_id = TokenUtil.decodeUserId(token);
        int isLearn = courseDao.isLearnCourse(user_id, course_id);
        if (isLearn == 0) {
            // 没有参加该课程
            int re = courseDao.addMyCourse(user_id, course_id);
            int num = courseDao.increCourseLearnNum(course_id);
            if (re > 0 && num > 0) {
                // 选课成功
                ResponseUtil.normalResponse(response, null);
            } else {
                // 选课失败
                ResponseUtil.errorResponse(response, null,
                        ErrorCode.CODE_SYSTEM_ERROR,
                        ErrorCode.MESSAGE_SYSTEM_ERROR);
            }
        } else {
            // 正在参加该课程
            ResponseUtil.errorResponse(response, null,
                    ErrorCode.CODE_MY_COURSE_IS_EXIST,
                    ErrorCode.MESSAGE_MY_COURSE_IS_EXIST);
        }
    }

    /**
     * 退选课程
     * */
    @RequestMapping(value = "quitCourse", method = RequestMethod.GET)
    public void quitCourse(@RequestParam String token,
            @RequestParam Integer course_id, HttpServletResponse response) {
        int user_id = TokenUtil.decodeUserId(token);
        int isLearn = courseDao.isLearnCourse(user_id, course_id);
        if (isLearn > 0) {
            int re = courseDao.delMyCourse(course_id, user_id);
            int num = courseDao.decreCourseLearnNum(course_id);
            if (re > 0 && num > 0) {
                // 退选成功
                ResponseUtil.normalResponse(response, null);
            } else {
                // 退选失败
                ResponseUtil.errorResponse(response, null,
                        ErrorCode.CODE_SYSTEM_ERROR,
                        ErrorCode.MESSAGE_SYSTEM_ERROR);
            }
        } else {
            // 没有参加该课程
            ResponseUtil.errorResponse(response, null,
                    ErrorCode.CODE_MY_COURSE_NOT_EXIST,
                    ErrorCode.MESSAGE_MY_COURSE_NOT_EXIST);
        }
    }

    /**
     * 课程知识点列表
     * */
    @RequestMapping(value = "knowledgeList", method = RequestMethod.GET)
    public void knowledgeList(@RequestParam Integer lesson_id,
            HttpServletResponse response) {
        List<String> knowledgeList = lessonDao.findListByLessonId(lesson_id);
        ResponseUtil.normalResponse(response, knowledgeList);
    }

}
