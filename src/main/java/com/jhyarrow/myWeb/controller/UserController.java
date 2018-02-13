package com.jhyarrow.myWeb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jhyarrow.myWeb.domain.User;
import com.jhyarrow.myWeb.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userService.getUser(username);
		if(user == null) {
			mv.setViewName("goAway");
		}else if(password.equals(user.getPassword())){
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			mv.setViewName("main");
		}else {
			mv.setViewName("goAway");
		}
		return mv;
	}
	
	@RequestMapping("/getLogIn")
	public ModelAndView getLogin() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/user/login");
		return mv;
	}
	
	@RequestMapping("/logOff")
	public ModelAndView logOff(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("username","");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/main");
		return mv;
	}
}
