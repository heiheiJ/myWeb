package com.jhyarrow.myWeb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.jhyarrow.myWeb.dao.BlogMapper;
import com.jhyarrow.myWeb.dao.VisitorsMapper;
import com.jhyarrow.myWeb.domain.Blog;

public class MyServlet extends HttpServlet{
	private static Reader reader;
	private static SqlSessionFactory SqlSessionFactory;
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
	    out.println("<h1>沉重纪念2017年12月24日考研失败，痛定思痛，特建此站。stay sense of crisis,stay freshman.<br>2017年12月27日</h1>");
	     
	    try {
	    	reader = Resources.getResourceAsReader("config/myBatis.xml");
			SqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		SqlSession session = SqlSessionFactory.openSession();
		VisitorsMapper visitorsMapper = session.getMapper(VisitorsMapper.class);
		int cnt = visitorsMapper.getCnt();
		cnt++;
		out.println("<h1>您是第"+cnt+"位拜访本站游客</h1>");
		visitorsMapper.updateCnt(cnt);
		session.commit();
		session.close();
	}	
}
