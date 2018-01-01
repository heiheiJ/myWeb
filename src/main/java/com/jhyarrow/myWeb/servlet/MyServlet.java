package com.jhyarrow.myWeb.servlet;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.jhyarrow.myWeb.dao.VisitorMapper;
import com.jhyarrow.myWeb.domain.Visitor;
import java.sql.Timestamp;

public class MyServlet extends HttpServlet{
	private static Reader reader;
	private static SqlSessionFactory SqlSessionFactory;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	    try {
	    	reader = Resources.getResourceAsReader("config/myBatis.xml");
			SqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		SqlSession session = SqlSessionFactory.openSession();
		VisitorMapper visitorMapper = session.getMapper(VisitorMapper.class);
		int tmp = visitorMapper.hasCome(request.getRemoteAddr());
		if(tmp == 0){
			Visitor visitor = new Visitor();
			visitor.setIp(request.getRemoteAddr());
			visitor.setPort(request.getRemoteHost());
			visitorMapper.addVisitor(visitor);
			session.commit();
		}
		int cnt = visitorMapper.getCnt();
		request.setAttribute("cnt", cnt);
		
		session.close();
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}	
}
