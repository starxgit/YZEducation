package com.fstech.yzeduss.controller;

import com.fstech.yzeduss.mapper.StudentMapper;
import com.fstech.yzeduss.mapper.TeacherMapper;
import com.fstech.yzeduss.model.Student;
import com.fstech.yzeduss.model.Teacher;
import com.fstech.yzeduss.util.Constant;
import com.fstech.yzeduss.util.CreateMD5;
import com.fstech.yzeduss.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created By shaoxin On 12/8/18
 */
@RestController
@CrossOrigin("*")
@RequestMapping("person")
public class PersonController {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    // 所有教师列表
    @RequestMapping(value = "teacherList", method = RequestMethod.GET)
    public void teacherList(HttpServletResponse response, @RequestParam int school_id) {
        List<Teacher> teacherList = teacherMapper.findListBySchoolId(school_id);
        ResponseUtil.normalResponse(response, teacherList);
    }

    // 所有学生列表
    @RequestMapping(value = "studentList", method = RequestMethod.GET)
    public void studentList(HttpServletResponse response, @RequestParam int school_id) {
        List<Student> studentList = studentMapper.findListBySchoolId(school_id);
        ResponseUtil.normalResponse(response, studentList);
    }

    // 添加一个教师
    @RequestMapping(value = "addTeacher", method = RequestMethod.POST)
    public void addTeacher(HttpServletResponse response, @RequestParam int school_id,
                           @RequestParam String teacher_number, @RequestParam String teacher_name) {
        String teacher_password = CreateMD5.getMd5(teacher_number);
        int result = teacherMapper.addTeacher(school_id,teacher_name, teacher_number,teacher_password);
        if (result > 0) {
            ResponseUtil.normalResponse(response, null);
        } else {
            ResponseUtil.errorResponse(response, null, 55555, Constant.UNKNOW_ERROR);
        }
    }

    // TODO 修改一个教师

    // 删除一个教师
    @RequestMapping(value = "delTeacher", method = RequestMethod.POST)
    public void delTeacher(HttpServletResponse response, @RequestParam String teacher_number) {
        int result = teacherMapper.delTeacher(teacher_number);
        if (result > 0) {
            ResponseUtil.normalResponse(response, null);
        } else {
            ResponseUtil.errorResponse(response, null, 55555, Constant.UNKNOW_ERROR);
        }
    }

    // TODO 添加一个学生

    // TODO 修改一个学生

    // TODO 删除一个学生
}