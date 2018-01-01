package com.jhyarrow.myWeb.servlet;

import java.io.IOException;
import java.io.Reader;
import java.sql.Timestamp;
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

import com.jhyarrow.myWeb.dao.BlogMapper;
import com.jhyarrow.myWeb.domain.Blog;

public class AddBlogServlet extends HttpServlet{
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
	    
		try{
			SqlSession session = SqlSessionFactory.openSession();
			request.setCharacterEncoding("UTF-8");
			BlogMapper blogMapper = session.getMapper(BlogMapper.class);
			Blog blog = new Blog();
			blog.setInfo(request.getParameter("info"));
			blog.setTitle(request.getParameter("title"));
			blogMapper.addBlog(blog);
			session.commit();
			session.close();
			RequestDispatcher rd = request.getRequestDispatcher("/success.jsp");
			rd.forward(request, response);
		}catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("/failure.jsp");
			rd.forward(request, response);
		}
	}	
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
