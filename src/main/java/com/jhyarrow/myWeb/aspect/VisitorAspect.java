package com.jhyarrow.myWeb.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jhyarrow.myWeb.domain.Visitor;
import com.jhyarrow.myWeb.service.VisitorService;

@Aspect
@Component
public class VisitorAspect{
	@Autowired
	private VisitorService visitorService;
	
	@Pointcut("execution(* com.jhyarrow.myWeb.controller.IndexController.getIndex(*)) && args(request)")
	public void pointCut(HttpServletRequest request) {
	}

	@Before("pointCut(request)")
	public void deBefore(JoinPoint joinPoint,HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		String port = request.getRemoteHost();
		if(!visitorService.hasCome(ip)) {
			Visitor visitor = new Visitor();
			visitor.setIp(ip);
			visitor.setPort(port);
			visitorService.addVisitor(visitor);
		}
	}
	
}
