package com.fstech.yzeduss.controller;

import com.fstech.yzeduss.mapper.ClassMapper;
import com.fstech.yzeduss.mapper.FacultyMapper;
import com.fstech.yzeduss.model.Faculty;
import com.fstech.yzeduss.model.GradeClass;
import com.fstech.yzeduss.util.Constant;
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
@RequestMapping("school")
public class SchoolController {

    @Autowired
    private FacultyMapper facultyMapper;

    @Autowired
    private ClassMapper classMapper;

    // 所有院系列表
    @RequestMapping(value = "facultyList", method = RequestMethod.GET)
    public void facultyList(HttpServletResponse response, @RequestParam int school_id) {
        List<Faculty> facultyList = facultyMapper.findListBySchoolId(school_id);
        ResponseUtil.normalResponse(response, facultyList);
    }

    // 所有班级列表
    @RequestMapping(value = "classlList", method = RequestMethod.GET)
    public void schoolList(HttpServletResponse response, @RequestParam int school_id) {
        List<GradeClass> gradeClassList = classMapper.findListBySchoolId(school_id);
        ResponseUtil.normalResponse(response, gradeClassList);
    }


    // 添加一个院系
    @RequestMapping(value = "addFaculty", method = RequestMethod.POST)
    public void addFaculty(HttpServletResponse response, @RequestParam int school_id, @RequestParam String faculty_name) {
        int result = facultyMapper.addFaculty(faculty_name, school_id);
        if (result > 0) {
            ResponseUtil.normalResponse(response, null);
        } else {
            ResponseUtil.errorResponse(response, null, 55555, Constant.UNKNOW_ERROR);
        }
    }

    // 删除一个院系
    @RequestMapping(value = "delFaculty", method = RequestMethod.POST)
    public void delFaculty(HttpServletResponse response, @RequestParam int faculty_id) {
        int result = facultyMapper.delFaculty(faculty_id);
        if (result > 0) {
            ResponseUtil.normalResponse(response, null);
        } else {
            ResponseUtil.errorResponse(response, null, 55555, Constant.UNKNOW_ERROR);
        }
    }

    // 添加一个班级
    @RequestMapping(value = "addClass", method = RequestMethod.POST)
    public void addClass(HttpServletResponse response, @RequestParam int school_id,
                         @RequestParam String class_name, @RequestParam int faculty_id) {
        int result = classMapper.addClass(school_id, faculty_id, class_name);
        if (result > 0) {
            ResponseUtil.normalResponse(response, null);
        } else {
            ResponseUtil.errorResponse(response, null, 55555, Constant.UNKNOW_ERROR);
        }
    }

    // 删除一个班级
    @RequestMapping(value = "delClass", method = RequestMethod.POST)
    public void delClass(HttpServletResponse response, @RequestParam int class_id) {
        int result = classMapper.delClass(class_id);
        if (result > 0) {
            ResponseUtil.normalResponse(response, null);
        } else {
            ResponseUtil.errorResponse(response, null, 55555, Constant.UNKNOW_ERROR);
        }
    }


    // TODO 修改一个院系


    // TODO 修改一个班级


}