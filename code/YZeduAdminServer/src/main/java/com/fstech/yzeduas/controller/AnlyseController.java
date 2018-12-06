package com.fstech.yzeduas.controller;

import com.fstech.yzeduas.util.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created By shaoxin On 12/6/18
 */

@Controller
@RequestMapping("/anlyse")
public class AnlyseController {

    // 院校数据分析
    @RequestMapping(value = "school", method = RequestMethod.GET)
    public String schoolAnlyse(Model model, HttpServletRequest request) {
        if (SessionUtil.isLogin(request) == true) {
            return "/school_anlyse";
        } else {
            return "/relogin";
        }
    }

    // 用户数据分析
    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String userAnlyse(Model model, HttpServletRequest request) {
        if (SessionUtil.isLogin(request) == true) {
            return "/user_anlyse";
        } else {
            return "/relogin";
        }
    }

    // 课程数据分析
    @RequestMapping(value = "course", method = RequestMethod.GET)
    public String courseAnlyse(Model model, HttpServletRequest request) {
        if (SessionUtil.isLogin(request) == true) {
            return "/course_anlyse";
        } else {
            return "/relogin";
        }
    }
}