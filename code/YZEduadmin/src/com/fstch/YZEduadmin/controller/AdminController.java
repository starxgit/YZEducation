package com.fstch.YZEduadmin.controller;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fstch.YZEduadmin.dao.AdminDao;
import com.fstch.YZEduadmin.models.Admin;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminDao adminDao;
	@RequestMapping(value="/adlist",method=RequestMethod.GET)
	public ModelAndView get(){
		ModelAndView mav = new ModelAndView("system/admin_list");
		List<Admin> admins = adminDao.findAll();
		mav.addObject("admins", admins);
		return mav;
	}
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView add(){
		ModelAndView mav = new ModelAndView("system/addAdmin");
		return mav;
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ModelAndView add(
			@RequestParam String admin_name,
			@RequestParam String admin_account,
			@RequestParam String admin_password,
			@RequestParam String admin_authority){
			Admin admin = new Admin();
			admin.setAdmin_name(admin_name);
			admin.setAdmin_account(admin_account);
			admin.setAdmin_password(admin_password);
			admin.setAdmin_authority(admin_authority);
			adminDao.addAdmin(admin);
		ModelAndView mav = new ModelAndView("redirect:/admin/adlist");
		return mav;
	}
	@RequestMapping(value="/del",method=RequestMethod.GET)
	public ModelAndView del(@RequestParam String admin_account){
		adminDao.delAdmin(admin_account);
		ModelAndView mav = new ModelAndView("redirect:/admin/adlist");
		return mav;
	}
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam String admin_account){
		adminDao.modify(admin_account);
		ModelAndView mav = new ModelAndView("redirect:/admin/adlist");
		return mav;
	}
}
