package com.fstch.YZEduadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fstch.YZEduadmin.dao.BillDao;
import com.fstch.YZEduadmin.models.Bill;

@Controller
@RequestMapping("/bill")
public class BillController {
	@Autowired
	BillDao billDao;
	@RequestMapping(value="/blist",method=RequestMethod.GET)
	public ModelAndView get(){
		ModelAndView mav = new ModelAndView("finance/bill_list");
		List<Bill> bills = billDao.findAll();
		mav.addObject("bills", bills);
		return mav;
	}
	@RequestMapping(value="/del",method=RequestMethod.GET)
	public ModelAndView del(@RequestParam int bill_id){
		billDao.delBill(bill_id);
		ModelAndView mav = new ModelAndView("redirect:/bill/blist");
		return mav;
	}
}
