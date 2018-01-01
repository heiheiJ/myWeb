package com.jhyarrow.myWeb.servlet;

import java.io.IOException;
import java.io.Reader;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.jhyarrow.myWeb.dao.BlogMapper;
import com.jhyarrow.myWeb.domain.Blog;

public class Test {
	private static Reader reader;
	private static SqlSessionFactory SqlSessionFactory;
	public static void main(String[] args) {
		try {
			reader = Resources.getResourceAsReader("config/myBatis.xml");
			SqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SqlSession session = SqlSessionFactory.openSession();
		BlogMapper mapper = session.getMapper(BlogMapper.class);
		Blog blog = new Blog();
		blog.setInfo("info");
		blog.setInput_date(new Timestamp(new Date().getTime()));
		blog.setTitle("title");
		mapper.addBlog(blog);
		session.commit();
		session.close();
	}
}
