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
import com.fstech.yzeduds.util.ErrorCode;
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
    @RequestMapping(value = "login", method = RequestMethod.POST)
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
                if (user.getSchool_id() > 0) {
                    // 学生用户
                    userType = 1;
                    studentName = user.getStudent_name();
                    student_id = user.getStudent_id();
                    schoolId = user.getSchool_id();
                    class_id = user.getClass_id();
                    faculty_id = user.getFaculty_id();
                }
                // 其他用户，非教师
                UserInfoBean userInfoBean = new UserInfoBean(studentName, null,
                        user.getUser_account(), user.getUser_avatar(), userType);
                String token = TokenUtil.enCodeToken(user.getUser_id(),
                        student_id, -1, class_id, faculty_id, schoolId);
                JSONObject return_data = new JSONObject();
                return_data.put("token", token);
                return_data.put("userInfo", userInfoBean);
                ResponseUtil.normalResponse(response, return_data);
            } else {
                ResponseUtil.errorResponse(response, null,
                        ErrorCode.CODE_PASSWORD_WRONG,
                        ErrorCode.MESSAGE_PASSWORD_WRONG);
            }
        } else {
            ResponseUtil.errorResponse(response, null,
                    ErrorCode.CODE_USER_NOT_EXIST,
                    ErrorCode.MESSAGE_USER_NOT_EXIT);
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
        int user_id = TokenUtil.decodeUserId(token);
        UserBean user = userDao.findUserById(user_id);
        if (user != null) {
            ResponseUtil.normalResponse(response, user);
        } else {
            ResponseUtil.errorResponse(response, null,
                    ErrorCode.CODE_USER_NOT_EXIST,
                    ErrorCode.MESSAGE_USER_NOT_EXIT);
        }
    }

    /**
     * 用户修改个人信息
     * 
     * @author shaoxin
     * @param token
     * */
    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public void updateUserInfo(HttpServletResponse response,
            @RequestParam String token, @RequestParam String phone,
            @RequestParam int age, @RequestParam String sex,
            @RequestParam String avatar) {
        int user_id = TokenUtil.decodeUserId(token);
        int result = userDao.updateUser(user_id, phone, age, sex, avatar);
        if (result > 0) {
            ResponseUtil.normalResponse(response, null);
        } else {
            ResponseUtil
                    .errorResponse(response, null, ErrorCode.CODE_SYSTEM_ERROR,
                            ErrorCode.MESSAGE_SYSTEM_ERROR);
        }
    }

    /**
     * 用户修改密码
     * 
     * @author shaoxin
     * @param token
     * */
    @RequestMapping(value = "password", method = RequestMethod.POST)
    public void updateUserPassword(HttpServletResponse response) {

    }

    // 教师登录的方法
    @RequestMapping(value = "teacherLogin", method = RequestMethod.POST)
    public void teacherLoginByAccount(HttpServletResponse response,
            @RequestParam String account, @RequestParam String password) {
        UserBean user = userDao.findTeacherByAccount(account);
        if (user != null) {
            String encodePass = CreateMD5.getMd5(password);
            if (encodePass.equals(user.getUser_password())) {
                // 登录成功，生成Token
                int userType = 2; // 教师类型
                String teacherName = user.getTeacher_name();
                int teacher_id = user.getTeacher_id();
                int schoolId = user.getSchool_id();
                int class_id = 0;
                int faculty_id = 0;
                // 其他用户，非教师
                UserInfoBean userInfoBean = new UserInfoBean(null, teacherName,
                        user.getUser_account(), user.getUser_avatar(), userType);
                String token = TokenUtil.enCodeToken(user.getUser_id(), -1,
                        teacher_id, class_id, faculty_id, schoolId);
                JSONObject return_data = new JSONObject();
                return_data.put("token", token);
                return_data.put("userInfo", userInfoBean);
                ResponseUtil.normalResponse(response, return_data);
            } else {
                ResponseUtil.errorResponse(response, null,
                        ErrorCode.CODE_PASSWORD_WRONG,
                        ErrorCode.MESSAGE_PASSWORD_WRONG);
            }
        } else {
            ResponseUtil.errorResponse(response, null,
                    ErrorCode.CODE_USER_NOT_EXIST,
                    ErrorCode.MESSAGE_USER_NOT_EXIT);
        }
    }

}
