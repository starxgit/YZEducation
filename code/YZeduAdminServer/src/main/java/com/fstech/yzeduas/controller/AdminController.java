package com.fstech.yzeduas.controller;

import com.fstech.yzeduas.dao.AdminDao;
import com.fstech.yzeduas.model.Admin;
import com.fstech.yzeduas.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminDao adminDao;

    @RequestMapping("allAdmin")
    public void allAdmin(HttpServletResponse response){
        List<Admin> admins = adminDao.findList();
        ResponseUtil.normalResponse(response,admins);
    }
}
