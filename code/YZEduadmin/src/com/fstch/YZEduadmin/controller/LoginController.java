package com.fstch.YZEduadmin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fstch.YZEduadmin.dao.LoginDao;
import com.fstch.YZEduadmin.models.Login;
import com.fstch.YZEduadmin.tools.CreateMD5;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private LoginDao loginDao;
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView get(){
		return new ModelAndView("login/login");
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView post(HttpSession session,
			HttpServletRequest req,
			@RequestParam String admin_name,
			@RequestParam String admin_password,
			@RequestParam(required=false) String returnUrl){
		
		Login login=loginDao.findByName(admin_name);
		if(login!=null){
		if (admin_name.equals(login.getAdmin_name()) && 
				CreateMD5.getMd5(admin_password).equals(login.getAdmin_password())) {
			session.setAttribute("admin_name",admin_name);
			if (returnUrl!=null) {
				return new ModelAndView("redirect:"+
			returnUrl.replace(req.getContextPath(), ""));
			}else {
				return new ModelAndView("index/index");
			}
		}else{
			ModelAndView mav =new ModelAndView("/login/login");
			mav.addObject("error","用户名或密码错误！！");
			return mav;
		}
		}else{
			ModelAndView mav =new ModelAndView("/login/login");
			mav.addObject("error","用户名或密码错误！！");
			return mav;
		}
	}
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam String admin_name){
		ModelAndView mav=new ModelAndView("login/mflogin");
		return mav;
	}
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public ModelAndView edit(
				@RequestParam String admin_name,
				@RequestParam String admin_password){
		ModelAndView mav=new ModelAndView("index/index");
		return mav;
	}
}
