package com.fstech.yzeduas.controller;

import com.fstech.yzeduas.dao.AdminDao;
import com.fstech.yzeduas.model.Admin;
import com.fstech.yzeduas.util.ResponseUtil;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminDao adminDao;

    @RequestMapping("allAdmin")
    public String allAdmin(HttpServletResponse response, Model model){
        List<Admin> admins = adminDao.findList();
        model.addAttribute("hello","欢迎进入Spring页面");
        model.addAttribute("admins",admins);
        return "/admin_list";
    }
}
