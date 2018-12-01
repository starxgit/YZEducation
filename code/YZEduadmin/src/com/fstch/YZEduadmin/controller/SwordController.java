package com.fstch.YZEduadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fstch.YZEduadmin.dao.SwordDao;
import com.fstch.YZEduadmin.models.Sword;

@Controller
@RequestMapping("/sword")
public class SwordController {
	@Autowired
	SwordDao swordDao;
	@RequestMapping(value="/swlist",method=RequestMethod.GET)
	public ModelAndView get(){
		ModelAndView mav = new ModelAndView("communicate/sword_list");
		List<Sword> swords = swordDao.findAll();
		mav.addObject("swords", swords);
		return mav;
	}
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView add(){
		ModelAndView mav = new ModelAndView("communicate/addSword");
		return mav;
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ModelAndView add(
			@RequestParam int school_id,
			@RequestParam String sensitive_word){
		
		Sword sword = new Sword();
		sword.setSchool_id(school_id);
		sword.setSensitive_word(sensitive_word);
		swordDao.addSword(sword);
		
		ModelAndView mav = new ModelAndView("redirect:/sword/swlist");
		return mav;
		
	}
	@RequestMapping(value="/del",method=RequestMethod.GET)
	public ModelAndView del(@RequestParam int sensitive_word_id){
		swordDao.delSword(sensitive_word_id);
		ModelAndView mav = new ModelAndView("redirect:/sword/swlist");
		return mav;
	}
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam int sensitive_word_id){
		Sword sword = swordDao.findById(sensitive_word_id);
		ModelAndView mav = new ModelAndView("communicate/editSword");
		mav.addObject("sword", sword);
		return mav;
	}
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public ModelAndView edit(
			@RequestParam int sensitive_word_id,
			@RequestParam String sensitive_word){
		Sword sword = new Sword();
		sword.setSensitive_word_id(sensitive_word_id);
		sword.setSensitive_word(sensitive_word);
		swordDao.modify(sword);
		ModelAndView mav = new ModelAndView("redirect:/sword/swlist");
		return mav;
	}
}
