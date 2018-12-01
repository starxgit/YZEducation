package com.fstch.YZEduadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fstch.YZEduadmin.dao.PcourseDao;
import com.fstch.YZEduadmin.models.Pcourse;

@Controller
@RequestMapping("/pcourse")
public class PcourseController {
	@Autowired
	PcourseDao pcourseDao;
	@RequestMapping(value="/pclist",method=RequestMethod.GET)
	public ModelAndView get(){
		ModelAndView mav = new ModelAndView("gateway/pcourse_list");
		List<Pcourse> pcourses = pcourseDao.findAll();
		mav.addObject("pcourses", pcourses);
		return mav;
		
	}
	@RequestMapping(value="/clist",method=RequestMethod.GET)
	public ModelAndView getcourse(){
		ModelAndView mav = new ModelAndView("gateway/course_list");
		List<Pcourse> courses = pcourseDao.findCourse();
		mav.addObject("courses", courses);
		return mav;
	}
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView addPcourse(@RequestParam int course_id){
		
		pcourseDao.addPcourse(course_id);
		ModelAndView mav = new ModelAndView("redirect:/pcourse/pclist");
		return mav;
	}
	@RequestMapping(value="/del",method=RequestMethod.GET)
	public ModelAndView del(@RequestParam int platform_course_id){
		pcourseDao.delPcourse(platform_course_id);
		ModelAndView mav = new ModelAndView("redirect:/pcourse/pclist");
		return mav;
	}
}
