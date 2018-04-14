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
	
}
