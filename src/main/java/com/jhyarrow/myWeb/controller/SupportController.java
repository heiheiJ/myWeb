package com.jhyarrow.myWeb.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.jhyarrow.myWeb.service.SupportService;
import com.jhyarrow.myWeb.view.StockDailyView;

@Controller
public class SupportController {
	@Autowired
	private SupportService supportService;
	
	@RequestMapping(value="/support/getGoldenNeedle",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getGoldenNeedle(HttpServletRequest request) {
		String date = "2016-08-25";
		ArrayList<StockDailyView> sdList = supportService.getGoldenNeedle(date);
		Gson gson = new Gson();
		String jsonString = gson.toJson(sdList);
		return jsonString;
	}
}
