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
		 int num = 2147483647 ; 
         long temp = num + 2L ; 
         System.out.println(num) ; 
	}
}
