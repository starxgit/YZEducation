package com.fstech.yzeduss.controller;

import com.fstech.yzeduss.mapper.AnnouncementMapper;
import com.fstech.yzeduss.mapper.InformationMapper;
import com.fstech.yzeduss.model.Announcement;
import com.fstech.yzeduss.model.Information;
import com.fstech.yzeduss.util.Constant;
import com.fstech.yzeduss.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created By shaoxin On 12/8/18
 */
@Controller
@CrossOrigin("*")
@RequestMapping("info")
public class InfoController {

    @Autowired
    private AnnouncementMapper announcementMapper;

    @Autowired
    private InformationMapper informationMapper;

    // 所有公告列表
    @RequestMapping(value = "announcementList", method = RequestMethod.GET)
    public void announcementList(HttpServletResponse response, @RequestParam int school_id) {
        List<Announcement> announcementList = announcementMapper.findListBySchoolId(school_id);
        ResponseUtil.normalResponse(response, announcementList);
    }

    // 发布公告
    @RequestMapping(value = "newAnnouncement", method = RequestMethod.POST)
    public void newAnnouncement(HttpServletResponse response, @RequestParam int school_id, @RequestParam String title,
                                @RequestParam String content, @RequestParam int isStick) {
        int result = announcementMapper.addAnnouncement(school_id, title, content, isStick);
        if (result > 0) {
            ResponseUtil.normalResponse(response, null);
        } else {
            ResponseUtil.errorResponse(response, null, 55555, Constant.UNKNOW_ERROR);
        }
    }

    // 删除公告
    @RequestMapping(value = "delAnnouncement", method = RequestMethod.POST)
    public void delAnnouncement(HttpServletResponse response, @RequestParam int announcement_id) {
        int result = announcementMapper.delAnnouncement(announcement_id);
        if (result > 0) {
            ResponseUtil.normalResponse(response, null);
        } else {
            ResponseUtil.errorResponse(response, null, 55555, Constant.UNKNOW_ERROR);
        }
    }

    // 所有资讯列表
    @RequestMapping(value = "informationList", method = RequestMethod.GET)
    public void informationList(HttpServletResponse response, @RequestParam int school_id) {
        List<Information> informationList = informationMapper.findListBySchoolId(school_id);
        ResponseUtil.normalResponse(response, informationList);
    }

    // TODO 添加资讯


    // TODO 删除资讯


}