package com.jhyarrow.myWeb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlert extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
	      out.println("<h1>沉重纪念2017年12月24日考研失败，痛定思痛，特建次站。stay sense of crisis,stay freshman.</h1>");
	}
}
