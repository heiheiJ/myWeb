package com.jhyarrow.myWeb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jhyarrow.myWeb.domain.User;
import com.jhyarrow.myWeb.exception.MyException;
import com.jhyarrow.myWeb.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String login(HttpSession session,String username,String password) throws Exception{
		User user = userService.getUser(username);
		if(user == null) {
			throw new MyException("用户名不存在");
		}else if(password.equals(user.getPassword())){
			session.setAttribute("username", username);
		}else {
			throw new MyException("密码错误");
		}
		return "redirect:/getMain.action";
	}
	
	@RequestMapping("/getLogIn")
	public ModelAndView getLogin() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/user/login");
		return mv;
	}
	
	@RequestMapping("/logOff")
	public String logOff(HttpSession session) {
		session.invalidate();
		
		return "redirect:/getMain.action";
	}
}
