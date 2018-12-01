package com.fstch.YZEduadmin.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fstch.YZEduadmin.dao.LoginDao;
import com.fstch.YZEduadmin.dao.SchoolDao;
import com.fstch.YZEduadmin.models.Login;
import com.fstch.YZEduadmin.models.School;
import com.fstch.YZEduadmin.tools.CreateMD5;



@Controller
@RequestMapping("/school")
public class SchoolController {
	@Autowired
	SchoolDao schoolDao;
	@Autowired
	LoginDao loginDao;
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView get(){
		ModelAndView mav = new ModelAndView("system/school_list");
		List<School> schools = schoolDao.findAll();
		mav.addObject("schools", schools);
		return mav;
	}
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView add(){
		ModelAndView mav = new ModelAndView("system/addSchool");
		return mav;
		
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ModelAndView add(
			@RequestParam String school_id,
			@RequestParam String school_name,
			@RequestParam String school_introduce,
			@RequestParam String school_city,
			@RequestParam String school_province,
			@RequestParam String school_type,
			@RequestParam String school_badge,
			@RequestParam String school_background){
		
		School school = new School();
		school.setSchool_name(school_name);
		school.setSchool_introduce(school_introduce);
		school.setSchool_city(school_city);
		school.setSchool_province(school_province);
		school.setSchool_type(school_type);
		school.setSchool_badge(school_badge);
		school.setSchool_background(school_background);
		schoolDao.addSchool(school);
		System.out.print(school_name);
		ModelAndView mav = new ModelAndView("redirect:/school");
		return mav;
	}
	@RequestMapping(value="/del",method=RequestMethod.GET)
	public ModelAndView del(@RequestParam String school_id){
		schoolDao.delSchool(school_id);
		ModelAndView mav = new ModelAndView("redirect:/school");
		return mav;
	}
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam String school_id){
		School school = schoolDao.findById(school_id);
		ModelAndView mav = new ModelAndView("system/editSchool");
		mav.addObject("school", school);
		return mav;
	}
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public ModelAndView edit(
			@RequestParam String school_id,
			@RequestParam String school_name,
			@RequestParam String school_introduce,
			@RequestParam String school_city,
			@RequestParam String school_province,
			@RequestParam String school_type
			){
		School school = new School();
		school.setSchool_name(school_name);
		school.setSchool_introduce(school_introduce);
		school.setSchool_city(school_city);
		school.setSchool_province(school_province);
		school.setSchool_type(school_type);
		
		schoolDao.modify(school);
		ModelAndView mav = new ModelAndView("redirect:/school");
		return mav;
	}
	//机构
	@RequestMapping(value="/orglist",method=RequestMethod.GET)
	public ModelAndView getorg(){
		ModelAndView mav = new ModelAndView("system/org_list");
		List<School> schools = schoolDao.findOrg();
		mav.addObject("schools", schools);
		return mav;
	}
	@RequestMapping(value="/add1",method=RequestMethod.GET)
	public ModelAndView add1(){
		ModelAndView mav = new ModelAndView("system/addOrg");
		return mav;
	}
	@RequestMapping(value="/add1",method=RequestMethod.POST)
	public ModelAndView add1(
			@RequestParam String school_id,
			@RequestParam String school_name,
			@RequestParam String school_introduce,
			@RequestParam String school_city,
			@RequestParam String school_province,
			@RequestParam String school_type,
			@RequestParam String school_badge,
			@RequestParam String school_background){
		
		School school = new School();
		school.setSchool_name(school_name);
		school.setSchool_introduce(school_introduce);
		school.setSchool_city(school_city);
		school.setSchool_province(school_province);
		school.setSchool_type(school_type);
		school.setSchool_badge(school_badge);
		school.setSchool_background(school_background);
		schoolDao.addSchool(school);
		
		ModelAndView mav = new ModelAndView("redirect:/school/orglist");
		return mav;
	}
	@RequestMapping(value="/del1",method=RequestMethod.GET)
	public ModelAndView del1(@RequestParam String school_id){
		schoolDao.delSchool(school_id);
		ModelAndView mav = new ModelAndView("redirect:/school/orglist");
		return mav;
	}
	@RequestMapping(value="/edit1",method=RequestMethod.GET)
	public ModelAndView edit1(@RequestParam String school_id){
		School school = schoolDao.findById(school_id);
		ModelAndView mav = new ModelAndView("system/editOrg");
		mav.addObject("school", school);
		return mav;
	}
	@RequestMapping(value="/edit1",method=RequestMethod.POST)
	public ModelAndView edit1(
			@RequestParam String school_id,
			@RequestParam String school_name,
			@RequestParam String school_introduce,
			@RequestParam String school_city,
			@RequestParam String school_province,
			@RequestParam String school_type
			){
		School school = new School();
		school.setSchool_name(school_name);
		school.setSchool_introduce(school_introduce);
		school.setSchool_city(school_city);
		school.setSchool_province(school_province);
		school.setSchool_type(school_type);
		
		schoolDao.modify(school);
		ModelAndView mav = new ModelAndView("redirect:/school/orglist");
		return mav;
	}
	//退出和修改密码
	@RequestMapping(value="/loginout",method=RequestMethod.GET)
	public ModelAndView loginout(){
		ModelAndView mav=new ModelAndView("redirect:/login");
		return mav;
	}
	@RequestMapping(value="/mflogin",method=RequestMethod.GET)
	public ModelAndView getMdfPwd(){
		ModelAndView mav=new ModelAndView("login/mflogin");
		return mav; 
	}
	
	@RequestMapping(value="/mflogin",method=RequestMethod.POST)
	public ModelAndView getMdfPwd(
			@RequestParam String mname,
			@RequestParam String pass,
			@RequestParam String newpass){
		ModelAndView mav=new ModelAndView("login/mflogin");
		String result="";
		Login ad=loginDao.findByName(mname);
		if(CreateMD5.getMd5(pass).equals(ad.getAdmin_password())){
			Login ad2=new Login();
			ad2.setAdmin_name(mname);
			ad2.setAdmin_password(CreateMD5.getMd5(newpass));
			loginDao.modify(ad2);
			result="修改成功！";
		}else{
			result="修改失败：原密码错误！";
		}
		mav.addObject("result",result);
		mav.addObject("admin_name",mname);
		return mav;
	}
}
