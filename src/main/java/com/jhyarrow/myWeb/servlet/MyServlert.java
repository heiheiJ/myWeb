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
	      out.println("<h1>���ؼ���2017��12��24�տ���ʧ�ܣ�ʹ��˼ʹ���ؽ���վ��stay sense of crisis,stay freshman.</h1>");
	}
}
