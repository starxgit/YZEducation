package com.fstch.YZEduadmin.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fstch.YZEduadmin.dao.BannerDao;
import com.fstch.YZEduadmin.models.Banner;

@Controller
@RequestMapping("/banner")
public class BannerController {
	@Autowired
	BannerDao bannerDao;
	@RequestMapping(value="/balist",method=RequestMethod.GET)
	public ModelAndView get(){
		ModelAndView mav =new ModelAndView("gateway/banner_list");
		List<Banner> banners = bannerDao.findAll();
		mav.addObject("banners", banners);
		return mav;
	}
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView add(){
		ModelAndView mav = new ModelAndView("gateway/addBanner");
		return mav;
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ModelAndView add(
			
			@RequestParam String banner_image,
			@RequestParam String banner_type,
			@RequestParam String banner_link){
			Banner banner = new Banner();
			
			banner.setBanner_image(banner_image);
			banner.setBanner_type(banner_type);
			banner.setBanner_link(banner_link);
			bannerDao.addBanner(banner);
		ModelAndView mav = new ModelAndView("redirect:/banner/balist");
		return mav;
	}
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public ModelAndView deleteBanner(@RequestParam int banner_id){
		bannerDao.deleteBanner(banner_id);
		ModelAndView mav = new ModelAndView("redirect:/banner/balist");
		return mav;
	}
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam int banner_id){
		Banner banner = bannerDao.findById(banner_id);
		ModelAndView mav = new ModelAndView("gateway/editBanner");
		mav.addObject("banner", banner);
		return mav;
	}
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public ModelAndView edit(
			@RequestParam int banner_id,
			@RequestParam String banner_image,
			@RequestParam String banner_type,
			@RequestParam String banner_link){
		Banner banner = new Banner();
		banner.setBanner_id(banner_id);
		banner.setBanner_image(banner_image);
		banner.setBanner_type(banner_type);
		banner.setBanner_link(banner_link);
		bannerDao.modify(banner);
		ModelAndView mav = new ModelAndView("redirect:/banner/balist");
		return mav;
	}
}
