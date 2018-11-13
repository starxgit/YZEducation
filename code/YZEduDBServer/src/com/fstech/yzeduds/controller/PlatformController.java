package com.fstech.yzeduds.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fstech.yzeduds.dao.BannerDao;
import com.fstech.yzeduds.model.BannerBean;
import com.fstech.yzeduds.util.Constant;

@Controller
@RequestMapping("/platform")
public class PlatformController {
    @Autowired
    private BannerDao bannerDao;
    
    /**
     * 获取首页Banner列表的方法
     * */
    @RequestMapping(value="banner",method=RequestMethod.GET)
    public void banner(HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        List<BannerBean> bannerList = bannerDao.bannerList();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("return_data", bannerList);
        jsonObject.put("result_code", Constant.RESULT_CODE_SUCCESS);
        jsonObject.put("message", Constant.MESSAGE_SUCCESS);
        response.getWriter().println(jsonObject.toString());
    }
    
}
