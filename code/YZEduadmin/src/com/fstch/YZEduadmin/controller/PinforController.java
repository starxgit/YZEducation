package com.fstch.YZEduadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fstch.YZEduadmin.dao.PinforDao;
import com.fstch.YZEduadmin.models.Pinfor;

@Controller
@RequestMapping("/pinfor")
public class PinforController {
	@Autowired
	PinforDao pinforDao;
	@RequestMapping(value="/plist",method=RequestMethod.GET)
	public ModelAndView get(){
		ModelAndView mav = new ModelAndView("gateway/pinfor_list");
		List<Pinfor> pinfors = pinforDao.findAll();
		mav.addObject("pinfors", pinfors);
		return mav;
	}
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView add(){
		ModelAndView mav = new ModelAndView("gateway/addPinfor");
		return mav;
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ModelAndView add(
			@RequestParam String platform_information_title,
			@RequestParam String platform_information_content,
			@RequestParam String platform_information_author,
			@RequestParam String platform_image_url){
		Pinfor pinfor = new Pinfor();
		
		pinfor.setPlatform_information_title(platform_information_title);
		pinfor.setPlatform_information_content(platform_information_content);
		pinfor.setPlatform_information_author(platform_information_author);
		pinfor.setPlatform_image_url(platform_image_url);
		
		pinforDao.addPinfor(pinfor);
		pinforDao.addPinforImg(pinfor);
		ModelAndView mav = new ModelAndView("redirect:/pinfor/plist");
		return mav;
	}
	@RequestMapping(value="/del",method=RequestMethod.GET)
	public ModelAndView del(@RequestParam int platform_information_id){
		pinforDao.delPinfor(platform_information_id);
		ModelAndView mav = new ModelAndView("redirect:/pinfor/plist");
		return mav;
	}
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam int platform_information_id){
		Pinfor pinfor = pinforDao.findById(platform_information_id);
		ModelAndView mav = new ModelAndView("gateway/editPinfor");
		mav.addObject("pinfor", pinfor);
		return mav;
	}
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public ModelAndView edit(
			@RequestParam int platform_information_id,
			@RequestParam String platform_information_title,
			@RequestParam String platform_information_content,
			@RequestParam String platform_information_author){
		Pinfor pinfor = new Pinfor();
		pinfor.setPlatform_information_id(platform_information_id);
		pinfor.setPlatform_information_title(platform_information_title);
		pinfor.setPlatform_information_content(platform_information_content);
		pinfor.setPlatform_information_author(platform_information_author);
		pinforDao.modify(pinfor);
		ModelAndView mav = new ModelAndView("redirect:/pinfor/plist");
		return mav;
		
		
	}
}
