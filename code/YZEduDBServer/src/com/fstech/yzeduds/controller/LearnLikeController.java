package com.fstech.yzeduds.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fstech.yzeduds.dao.CourseDao;
import com.fstech.yzeduds.dao.UserLikeDao;
import com.fstech.yzeduds.model.ClassificationBean;
import com.fstech.yzeduds.model.CourseBean;
import com.fstech.yzeduds.model.LearnLike;
import com.fstech.yzeduds.util.ResponseUtil;
import com.fstech.yzeduds.util.TokenUtil;

@Controller
@RequestMapping("/userlike")
public class LearnLikeController {
    @Autowired
    private UserLikeDao userLikeDao;
    @Autowired
    private CourseDao courseDao;

    /**
     * 我的学习偏好列表
     * */
    @RequestMapping(value = "/likelist", method = RequestMethod.POST)
    public void likelist(@RequestParam String token,
            HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        int userid = TokenUtil.decodeUserId(token);
        List<ClassificationBean> clist = userLikeDao.LikesList();
        String mylike = userLikeDao.mylike(userid + "");
        JSONArray ja = new JSONArray();
        for (ClassificationBean c : clist) {
            JSONObject jobj = new JSONObject();
            String chose = "0";
            if (mylike != null) {
                if (mylike.contains(buling(c.getClassification_id())))
                    chose = "1";
            }
            jobj.put("chose", chose);
            jobj.put("name", c.getClassification_name());
            jobj.put("cfa_id", c.getClassification_id());
            ja.add(jobj);
        }
        ResponseUtil.normalResponse(response, ja);
    }

    /**
     * 保存我的学习偏好
     * */
    @RequestMapping(value = "/savelike", method = RequestMethod.POST)
    public void savalike(@RequestParam String token,
            @RequestParam String userlike, HttpServletResponse response)
            throws Exception {
        int userid = TokenUtil.decodeUserId(token);
        userLikeDao.delMyLike(userid + "");
        LearnLike ll = new LearnLike();
        ll.setUser_id(userid + "");
        ll.setUser_like(userlike);
        userLikeDao.addMyLike(ll);
        ResponseUtil.normalResponse(response, null);
    }

    /**
     * 补0的方法
     * */
    public String buling(int a) {
        String str = a + "";
        if (a < 10) {
            str = "0" + a;
        }
        return str;
    }

    /**
     * 获取个性化推荐课程列表的方法
     * */
    @RequestMapping(value = "/like", method = RequestMethod.GET)
    public void getLikeCourse(@RequestParam String token,
            HttpServletResponse response) {
        int school_id = TokenUtil.decodeSchoolId(token);
        int faculty_id = TokenUtil.decodeFacultyId(token);
        int class_id = TokenUtil.decodeClassId(token);
        List<CourseBean> courseList = courseDao.topCourseList(school_id,
                faculty_id, class_id, 0,30);
        ResponseUtil.normalResponse(response, courseList);
    }

}