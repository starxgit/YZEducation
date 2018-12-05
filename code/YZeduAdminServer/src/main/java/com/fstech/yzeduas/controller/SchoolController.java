package com.fstech.yzeduas.controller;

import com.fstech.yzeduas.dao.SchoolDao;
import com.fstech.yzeduas.model.School;
import com.fstech.yzeduas.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created By shaoxin On 12/5/18
 * 院校模块的控制器
 */
@Controller
@RequestMapping("school")
public class SchoolController {
    @Autowired
    private SchoolDao schoolDao;

    // 所有管理员困
    @RequestMapping("schoolList")
    public String allSchool(Model model, HttpServletRequest request) {
        if (SessionUtil.isLogin(request) == true) {
            List<School> schools = schoolDao.findList();
            model.addAttribute("schools", schools);
            model.addAttribute("types", School.SCHOOL_TYPE_LIST);
            return "/school_list";
        } else {
            return "/relogin";
        }
    }

    // 到修改院校页
    @RequestMapping("editSchool")
    public String toEditSchool(Model model, HttpServletRequest request, @RequestParam int school_id) {
        if (SessionUtil.isLogin(request) == true) {
            School school = schoolDao.findSchoolById(school_id);
            model.addAttribute("school", school);
            return "/edit_school";
        } else {
            return "/relogin";
        }
    }

    // 到添加院校页
    @RequestMapping("addSchool")
    public String toAddSchool(HttpServletRequest request) {
        if (SessionUtil.isLogin(request) == true) {
            return "/add_school";
        } else {
            return "/relogin";
        }
    }
}