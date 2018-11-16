package com.fstech.yzeduds.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fstech.yzeduds.dao.BannerDao;
import com.fstech.yzeduds.dao.InformationDao;
import com.fstech.yzeduds.model.BannerBean;
import com.fstech.yzeduds.model.InformationBean;
import com.fstech.yzeduds.model.InformationContentBean;
import com.fstech.yzeduds.util.Constant;
import com.fstech.yzeduds.util.ResponseUtil;

@Controller
@RequestMapping("/platform")
public class PlatformController {
    @Autowired
    private BannerDao bannerDao;
    @Autowired
    private InformationDao informationDao;

    /**
     * 获取首页Banner列表的方法
     * */
    @RequestMapping(value = "banner", method = RequestMethod.GET)
    public void banner(HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
        List<BannerBean> bannerList = bannerDao.bannerList();
        ResponseUtil.normalResponse(response, bannerList);
    }

    /**
     * 获取首页资讯列表的方法
     * */
    @RequestMapping(value = "information", method = RequestMethod.GET)
    public void information(@RequestParam Integer page,
            HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        List<InformationBean> informationList = informationDao
                .platformInformations((page - 1)
                        * Constant.INFORMATION_PAGE_SIZE,
                        Constant.INFORMATION_PAGE_SIZE);
        ResponseUtil.normalResponse(response, informationList);
    }

    /**
     * 获取首页资讯详情的方法
     * */
    @RequestMapping(value = "informationDetail", method = RequestMethod.GET)
    public void informationDetail(@RequestParam Integer infomation_id,
            HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
        List<InformationContentBean> contentList = informationDao
                .platformInformationContent(infomation_id);
        ResponseUtil.normalResponse(response, contentList);
    }

}
