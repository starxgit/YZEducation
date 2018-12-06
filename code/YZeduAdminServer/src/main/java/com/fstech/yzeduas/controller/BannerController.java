package com.fstech.yzeduas.controller;

import com.fstech.yzeduas.dao.BannerDao;
import com.fstech.yzeduas.model.Banner;
import com.fstech.yzeduas.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created By shaoxin On 12/6/18
 */
@Controller
@RequestMapping("banner")
public class BannerController {

    @Autowired
    private BannerDao bannerDao;

    // 所有Banner列表
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String bannerList(Model model, HttpServletRequest request) {
        if (SessionUtil.isLogin(request) == true) {
            List<Banner> banners = bannerDao.findList();
            model.addAttribute("types", Banner.TYPES);
            model.addAttribute("banners", banners);
            return "/banner_list";
        } else {
            return "/relogin";
        }
    }

    // 到添加Banner页
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String toAddBanner(HttpServletRequest request) {
        if (SessionUtil.isLogin(request) == true) {
            return "/add_banner";
        } else {
            return "/relogin";
        }
    }

    // 添加Banner
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addBanner(HttpServletRequest request, @RequestParam String banner_image, @RequestParam int banner_type,
                            @RequestParam String banner_link) {
        if (SessionUtil.isLogin(request) == true) {
            Banner banner = new Banner();
            banner.setBanner_image(banner_image);
            banner.setBanner_link(banner_link);
            banner.setBanner_type(banner_type);
            bannerDao.addBanner(banner);
            return "redirect:/banner/list";
        } else {
            return "/relogin";
        }
    }

    // 删除Banner
    @RequestMapping(value = "delBanner", method = RequestMethod.GET)
    public String deleteBanner(HttpServletRequest request, @RequestParam int banner_id) {
        if (SessionUtil.isLogin(request) == true) {
            System.out.println(banner_id);
            bannerDao.delBanner(banner_id);
            return "redirect:/banner/list";
        } else {
            return "/relogin";
        }
    }
}