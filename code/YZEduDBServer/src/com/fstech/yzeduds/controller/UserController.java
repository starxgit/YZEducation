package com.fstech.yzeduds.controller;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fstech.yzeduds.dao.UserDao;
import com.fstech.yzeduds.model.UserBean;
import com.fstech.yzeduds.model.UserInfoBean;
import com.fstech.yzeduds.util.CreateMD5;
import com.fstech.yzeduds.util.ResponseUtil;
import com.fstech.yzeduds.util.TokenUtil;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDao userDao;

    /**
     * 用户账号密码登录的方法
     * 
     * @author shaoxin
     * @String account
     * @param password
     * */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public void loginByAccount(HttpServletResponse response,
            @RequestParam String account, @RequestParam String password) {
        UserBean user = userDao.findUserByAccount(account);
        if (user != null) {
            String encodePass = CreateMD5.getMd5(password);
            if (encodePass.equals(user.getUser_password())) {
                String studentName = null;
                int userType = 3;
                int schoolId = -1;
                int student_id = -1;
                int class_id = -1;
                int faculty_id = -1;
                if(user.getSchool_id() > 0){
                    // 学生用户
                    userType =1;
                    student_id = user.getStudent_id();
                    schoolId = user.getSchool_id();
                    class_id = user.getClass_id();
                    faculty_id = user.getFaculty_id();
                }
                // 其他用户，非教师
                UserInfoBean userInfoBean = new UserInfoBean(studentName, null,
                        user.getUser_account(), user.getUser_avatar(),
                        userType);
                String token = TokenUtil.enCodeToken(user.getUser_id(), student_id, -1, class_id, faculty_id, schoolId);
                JSONObject return_data = new JSONObject();
                return_data.put("token", token);
                return_data.put("userInfo", userInfoBean);
                ResponseUtil.normalResponse(response, return_data);
            } else {
                ResponseUtil.errorResponse(response, null, 1002, "账号或密码错误");
            }
        } else {
            ResponseUtil.errorResponse(response, null, 1001, "用户不存在");
        }
    }

    /**
     * 用户查看个人信息
     * 
     * @author shaoxin
     * @param token
     * */
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public void UserInfo(HttpServletResponse response,
            @RequestParam String token) {

    }

    /**
     * 用户修改个人信息
     * 
     * @author shaoxin
     * @param token
     * */
    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public void updateUserInfo(HttpServletResponse response) {

    }

    /**
     * 用户修改头像
     * 
     * @author shaoxin
     * @param token
     * */
    @RequestMapping(value = "avatar", method = RequestMethod.POST)
    public void updateUserAvatar(HttpServletResponse response) {

    }

}
