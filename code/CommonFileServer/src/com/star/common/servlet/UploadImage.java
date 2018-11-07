package com.star.common.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.star.common.util.FileUtil;

/**
 * Created By shaoxin On 2018-11-07
 * 上传图片的Servlet
 */
@WebServlet("/UploadImage")
public class UploadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // 允许跨域访问
        response.setHeader("Access-Control-Allow-Origin", "*");
        String fileUrl = FileUtil.upLoadFile(request,"YZEduResources/images/");
        System.out.println(fileUrl);
        response.getWriter().print(fileUrl);
    }

}
