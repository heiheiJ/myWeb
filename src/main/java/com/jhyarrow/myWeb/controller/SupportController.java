package com.jhyarrow.myWeb.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jhyarrow.myWeb.domain.Support;
import com.jhyarrow.myWeb.service.SupportService;

@Controller
public class SupportController {
	@Autowired
	private SupportService supportService;
	
	@RequestMapping("/getSupport")
	public ModelAndView getIndex(HttpServletRequest request) throws Exception{
		Calendar c = Calendar.getInstance();
		int dayForWeek = 0;
		if(c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		}else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		if(dayForWeek == 7) {
			c.set(Calendar.DATE,c.get(Calendar.DATE) - 2);
		}else if (dayForWeek == 6) {
			c.set(Calendar.DATE,c.get(Calendar.DATE) - 1);
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
		String date = df.format(c.getTime());
		
		ArrayList<Support> lists = supportService.getSupport(date);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/stock/getStockList");
		mv.addObject("stockList",lists);

        return mv;
    }
}
