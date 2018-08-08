package com.jhyarrow.myWeb.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/*
 * 异常处理器
 */
public class MyExceptionResolver implements HandlerExceptionResolver{

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		MyException myException = null;
		if(ex instanceof MyException) {
			myException = ((MyException)ex);
		}else {
			myException = new MyException("未知错误");
		}
		ex.printStackTrace();
		
		String message = myException.getMessage();
		ModelAndView mv = new ModelAndView();
		mv.addObject("message",message);
		mv.setViewName("error");
		
		return mv;
	}

}
