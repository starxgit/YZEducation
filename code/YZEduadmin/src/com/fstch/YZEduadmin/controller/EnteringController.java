package com.fstch.YZEduadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fstch.YZEduadmin.dao.EnterDao;
import com.fstch.YZEduadmin.models.Enter;

@Controller
@RequestMapping("/enter")
public class EnteringController {
	@Autowired
	EnterDao enterDao;
	@RequestMapping(value="/eilist",method=RequestMethod.GET)
	public ModelAndView get(){
		ModelAndView mav = new ModelAndView("enter/entering_list");
		List<Enter> enterings = enterDao.findAll();
		mav.addObject("enterings", enterings);
		return mav;
	}
	@RequestMapping(value="/edlist",method=RequestMethod.GET)
	public ModelAndView getEnter(){
		ModelAndView mav = new ModelAndView("enter/entered_list");
		List<Enter> entereds = enterDao.findEntered();
		mav.addObject("entereds", entereds);
		return mav;
	}
	@RequestMapping(value="/addto",method=RequestMethod.GET)
	public ModelAndView addto(@RequestParam int enter_id){
		enterDao.addToEntered(enter_id);
		ModelAndView mav = new ModelAndView("redirect:/enter/eilist");
		return mav;
	}
	@RequestMapping(value="/del",method=RequestMethod.GET)
	public ModelAndView del(@RequestParam int enter_id){
		enterDao.delEnter(enter_id);
		ModelAndView mav = new ModelAndView("redirect:/enter/eilist");
		return mav;
	}
	@RequestMapping(value="/deld",method=RequestMethod.GET)
	public ModelAndView deld(@RequestParam int enter_id){
		enterDao.delEnter(enter_id);
		ModelAndView mav = new ModelAndView("redirect:/enter/edlist");
		return mav;
	}
}
