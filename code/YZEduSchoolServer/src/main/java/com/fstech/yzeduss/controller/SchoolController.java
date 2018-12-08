package com.fstech.yzeduss.controller;

import com.fstech.yzeduss.mapper.FacultyMapper;
import com.fstech.yzeduss.model.Faculty;
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

    // 所有院系列表
    @RequestMapping(value = "facultyList", method = RequestMethod.GET)
    public void facultyList(HttpServletResponse response, @RequestParam int school_id) {
        List<Faculty> facultyList = facultyMapper.findListBySchoolId(school_id);
        ResponseUtil.normalResponse(response, facultyList);
    }

    // TODO 所有班级列表

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
    public void delFaculty(HttpServletResponse response, @RequestParam int school_id) {
        int result = facultyMapper.delFaculty(school_id);
        if (result > 0) {
            ResponseUtil.normalResponse(response, null);
        } else {
            ResponseUtil.errorResponse(response, null, 55555, Constant.UNKNOW_ERROR);
        }

    }

    // TODO 修改一个院系

    // TODO 添加一个班级

    // TODO 修改一个班级

    // TODO 删除一个班级
}