package com.fstech.yzeduss.controller;

import com.fstech.yzeduss.mapper.CourseMapper;
import com.fstech.yzeduss.model.Course;
import com.fstech.yzeduss.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created By shaoxin On 12/8/18
 */
@Controller
@RequestMapping("course")
@CrossOrigin("*")
public class CourseController {
    @Autowired
    private CourseMapper courseMapper;

    // 所有课程列表
    @RequestMapping(value = "cList",method = RequestMethod.GET)
    public void courseList(HttpServletResponse response, @RequestParam int school_id){
        List<Course> courseList =courseMapper.findCourseBySchoolId(school_id);
        ResponseUtil.normalResponse(response,courseList);
    }

    // 所有实训列表
    @RequestMapping(value = "pList",method = RequestMethod.GET)
    public void practiceList(HttpServletResponse response, @RequestParam int school_id){
        List<Course> courseList =courseMapper.findPracticalBySchoolId(school_id);
        ResponseUtil.normalResponse(response,courseList);
    }

    // TODO 添加一门课程（包括实训）

    // TODO 删除一门课程

    // TODO 修改一门课程


}