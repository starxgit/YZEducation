package com.fstech.yzeduas.controller;

import com.fstech.yzeduas.dao.AdminDao;
import com.fstech.yzeduas.model.Admin;
import com.fstech.yzeduas.util.Constant;
import com.fstech.yzeduas.util.CreateMD5;
import com.fstech.yzeduas.util.ResponseUtil;
import com.fstech.yzeduas.util.SessionUtil;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminDao adminDao;

    // 所有管理员困
    @RequestMapping("adminList")
    public String allAdmin(Model model, HttpServletRequest request) {
        if (SessionUtil.isLogin(request) == true) {
            List<Admin> admins = adminDao.findList();
            model.addAttribute("admins", admins);
            return "/admin_list";
        } else {
            return "/relogin";
        }
    }

    // 管理员登录
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String Login(Model model, @RequestParam String account, HttpServletRequest request,
                        @RequestParam String password) {
        String errMsg = "";
        if (account.equals("") || password.equals("")) {
            errMsg = Constant.PLEASE_INPUT_ALL;
        } else {
            Admin admin = adminDao.findByAccount(account);
            if (admin != null) {
                if (admin.getAdmin_password().equals(CreateMD5.getMd5(password))) {
                    admin.setAdmin_account(account);
                    SessionUtil.setSession(request, admin);
                    model.addAttribute("admin_account", admin.getAdmin_account());
                    model.addAttribute("admin_name", admin.getAdmin_name());
                    return "/index";
                } else {
                    errMsg = Constant.WRONG_PASSWORD;
                }
            } else {
                errMsg = Constant.ADMIN_NOT_EXIST;
            }
        }
        model.addAttribute("error_msg", errMsg);
        return "/login";
    }

    // 管理员主页
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String Login(HttpServletRequest request) {
        if (SessionUtil.isLogin(request) == true) {
            return "/index";
        } else {
            return "/login";
        }
    }

    // 欢迎页
    @RequestMapping(value = "welcome", method = RequestMethod.GET)
    public String welcome(HttpServletRequest request) {
        if (SessionUtil.isLogin(request) == true) {
            return "/welcome";
        } else {
            return "/relogin";
        }
    }

    // 退出登录
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String louout(HttpServletRequest request) {
        SessionUtil.clearSession(request);
        return "/login";
    }

    // 到修改密码页
    @RequestMapping(value = "modifyPass", method = RequestMethod.GET)
    public String modifyPass(HttpServletRequest request, Model model, @RequestParam String account) {
        if (SessionUtil.isLogin(request) == true) {
            Admin admin = adminDao.findByAccount(account);
            admin.setAdmin_password("");    // 隐藏敏感信息
            model.addAttribute("admin", admin);
            return "/edit_admin";
        } else {
            return "/relogin";
        }
    }

    // 提交修改密码
    @RequestMapping(value = "updatePassword", method = RequestMethod.POST)
    public String updatePassword(@RequestParam String account, HttpServletRequest request,
                                 @RequestParam String password, @RequestParam String newpass, Model model) {
        if (SessionUtil.isLogin(request) == true) {
            String errMsg = "";
            Admin admin = adminDao.findByAccount(account);
            if (password.equals("") || newpass.equals("")) {
                errMsg = Constant.PLEASE_INPUT_ALL;
            } else {
                if (admin.getAdmin_password().equals(CreateMD5.getMd5(password))) {
                    String admin_account = SessionUtil.getSession(request).getAdmin_account();
                    if (account.equals(admin_account) || account.equals("1219371280")) {
                        admin.setAdmin_password(CreateMD5.getMd5(newpass));
                        int result = adminDao.updateAdmin(admin);
                        if (result > 0) {
                            model.addAttribute("error_msg", "修改成功，请重新登录");
                            return "/login";
                        } else {
                            errMsg = Constant.UNKNOW_ERROR;
                        }
                    } else {
                        errMsg = Constant.AUTH_NOT_ENOUGH;
                    }
                } else {
                    errMsg = Constant.WRONG_PASSWORD;
                }
            }
            model.addAttribute("error_msg", errMsg);
            model.addAttribute("admin", admin);
            return "/edit_admin";
        } else {
            return "/relogin";
        }
    }

}
