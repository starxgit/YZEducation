package com.fstech.yzeduds.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 返回Json数据的工具类 Created By shaoxin On 2018-11-16
 * */
public class ResponseUtil {

    /**
     * 正确情况下返回数据的方法
     * @param response
     * @param return_data
     * @author shaoxin
     * */
    public static void normalResponse(HttpServletResponse response,
            Object return_data) {
        response.setContentType("text/html;charset=utf-8");
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("return_data", return_data);
            jsonObject.put("result_code", Constant.RESULT_CODE_SUCCESS);
            jsonObject.put("message", Constant.MESSAGE_SUCCESS);
            response.getWriter().println(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    /**
     * 错误情况下返回数据的方法
     * @param response
     * @param return_data
     * @author shaoxin
     * */
    public static void errorResponse(HttpServletResponse response,
            Object return_data,int result_code,String message) {
        response.setContentType("text/html;charset=utf-8");
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("return_data", return_data);
            jsonObject.put("result_code", result_code);
            jsonObject.put("message", message);
            response.getWriter().println(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

}
