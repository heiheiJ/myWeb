package com.jhyarrow.myWeb.servlet;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.jhyarrow.myWeb.dao.BlogMapper;
import com.jhyarrow.myWeb.domain.Blog;

public class GetBlogServlet extends HttpServlet{
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
		BlogMapper blogMapper = session.getMapper(BlogMapper.class);
		int id = Integer.parseInt(request.getParameter("id"));
		Blog blog = blogMapper.getBlog(id);
		request.setAttribute("blog", blog);
		session.close();
		RequestDispatcher rd = request.getRequestDispatcher("/blog/getBlog.jsp");
		rd.forward(request, response);
	}	
}
