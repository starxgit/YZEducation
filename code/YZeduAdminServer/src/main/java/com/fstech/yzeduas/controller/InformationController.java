package com.fstech.yzeduas.controller;

import com.fstech.yzeduas.model.Advice;
import com.fstech.yzeduas.util.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created By shaoxin On 12/6/18
 */
@Controller
@RequestMapping("information")
public class InformationController {

    // 平台资讯列表
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String InformationList(Model model, HttpServletRequest request) {
        if (SessionUtil.isLogin(request) == true) {
            return "/information_list";
        } else {
            return "/relogin";
        }
    }

    // 到新增资讯页
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addInformation(Model model, HttpServletRequest request) {
        if (SessionUtil.isLogin(request) == true) {
            return "/add_information";
        } else {
            return "/relogin";
        }
    }

    // 新增资讯

    // 删除资讯

}