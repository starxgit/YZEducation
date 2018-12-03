package com.fstech.yzeduas.util;

import com.fstech.yzeduas.model.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Created By shaoxin On 12/3/18
 * 用来保存和取出Session的工具类
 */
public class SessionUtil {

    // 账号的键名
    public static final String SESSION_KEY = "admin";

    /**
     * 设置Session的方法
     *
     * @param request
     * @param admin
     */
    public static void setSession(HttpServletRequest request, Admin admin) {
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_KEY, admin);
    }

    /**
     * 检查是否登录的方法
     *
     * @param request
     */
    public static boolean isLogin(HttpServletRequest request) {
        boolean result = false;
        HttpSession session = request.getSession();
        if (session != null) {
            Admin a = getSession(request);
            if (a != null) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 获取Sesion内容的方法
     *
     * @param request
     */
    public static Admin getSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute(SESSION_KEY);
        return admin;
    }

    /**
     * 清除Session的方法
     * */
    public static void clearSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute(SESSION_KEY);
    }
}