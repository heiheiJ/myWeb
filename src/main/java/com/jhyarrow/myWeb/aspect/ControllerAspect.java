package com.jhyarrow.myWeb.aspect;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ControllerAspect {
	private static Logger logger = Logger.getLogger("ControllerAspect");
	
	@Pointcut("execution(* com.jhyarrow.myWeb.controller.*.*(..))")
	public void pointCut() {
		
	}
	
	@Before("pointCut()")
	public void doBefore(JoinPoint joinPoint) {
		HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
		String url = request.getRequestURL().toString();
		logger.info("get request :"+url);
	}
	
	@After("pointCut()")
	public void doAfter(JoinPoint joinPoint) {
		HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
		String url = request.getRequestURL().toString();
		logger.info("finish request "+url);
	}
}
