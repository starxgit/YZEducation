package com.fstech.yzeduds.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fstech.yzeduds.dao.InformationDao;
import com.fstech.yzeduds.dao.SchoolDao;
import com.fstech.yzeduds.model.AnnouncementBean;
import com.fstech.yzeduds.model.InformationBean;
import com.fstech.yzeduds.model.InformationContentBean;
import com.fstech.yzeduds.model.SchoolBean;
import com.fstech.yzeduds.util.Constant;
import com.fstech.yzeduds.util.ErrorCode;
import com.fstech.yzeduds.util.ResponseUtil;
import com.fstech.yzeduds.util.TokenUtil;

@Controller
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private InformationDao informationDao;
    @Autowired
    private SchoolDao schoolDao;

    /**
     * 查看院校资讯列表
     */
    @RequestMapping(value = "information", method = RequestMethod.GET)
    private void Informations(@RequestParam Integer page,
            @RequestParam String token, HttpServletResponse response) {
        int school_id = TokenUtil.decodeSchoolId(token);
        List<InformationBean> informationList = informationDao
                .schoolInformations(school_id, (page - 1)
                        * Constant.INFORMATION_PAGE_SIZE,
                        Constant.INFORMATION_PAGE_SIZE);
        ResponseUtil.normalResponse(response, informationList);
    }

    /**
     * 查看院校公告列表
     */
    @RequestMapping(value = "announcement", method = RequestMethod.GET)
    public void announcementList(@RequestParam String token,
            HttpServletResponse response) {
        int school_id = TokenUtil.decodeSchoolId(token);
        List<AnnouncementBean> announcementList = schoolDao
                .findAnnouncementsBySchoolId(school_id);
        ResponseUtil.normalResponse(response, announcementList);
    }

    /**
     * 获取院校资讯详情的方法
     * */
    @RequestMapping(value = "informationDetail", method = RequestMethod.GET)
    public void informationDetail(@RequestParam Integer infomation_id,
            HttpServletResponse response) {
        List<InformationContentBean> contentList = informationDao
                .schoolInformationContent(infomation_id);
        ResponseUtil.normalResponse(response, contentList);
    }

    /**
     * 查看院校基本信息
     */
    @RequestMapping(value = "schoolInfo", method = RequestMethod.GET)
    public void schoolInfo(@RequestParam String token,
            HttpServletResponse response) {
        int school_id = TokenUtil.decodeSchoolId(token);
        SchoolBean schoolBean = schoolDao.findSchoolById(school_id);
        if (schoolBean != null) {
            ResponseUtil.normalResponse(response, schoolBean);
        } else {
            ResponseUtil.errorResponse(response, null,
                    ErrorCode.CODE_SCHOOL_NOT_EXIST,
                    ErrorCode.MESSAGE_SCHOOL_NOT_EXIST);
        }
    }

}
