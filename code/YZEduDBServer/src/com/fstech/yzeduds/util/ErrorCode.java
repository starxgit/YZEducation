package com.fstech.yzeduds.util;

/**
 * 存放错误码的常量类
 * Created By shaoxin On 2018-11-17
 * */
public class ErrorCode {
    
    public static final int CODE_USER_NOT_EXIST = 1001;
    public static final String MESSAGE_USER_NOT_EXIT = "该用户不存在";
    
    public static final int CODE_PASSWORD_WRONG = 1002;
    public static final String MESSAGE_PASSWORD_WRONG = "账号或密码错误";
    
    public static final int CODE_SCHOOL_NOT_EXIST = 1003;
    public static final String MESSAGE_SCHOOL_NOT_EXIST = "该院校不存在";
    
    public static final int CODE_COURSE_NOT_EXIST = 1004;
    public static final String MESSAGE_COURSE_NOT_EXIST = "该课程不存在";
    
    public static final int CODE_MY_COURSE_NOT_EXIST = 1005;
    public static final String MESSAGE_MY_COURSE_NOT_EXIST = "退选失败，您尚未参加该课程";
    
    public static final int CODE_SYSTEM_ERROR = 1006;
    public static final String MESSAGE_SYSTEM_ERROR = "系统故障，请稍后再试";
    
    public static final int CODE_MY_COURSE_IS_EXIST = 1007;
    public static final String MESSAGE_MY_COURSE_IS_EXIST = "选课失败，您已经参加该课程";
}
