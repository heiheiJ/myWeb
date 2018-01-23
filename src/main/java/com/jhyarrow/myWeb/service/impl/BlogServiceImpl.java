package com.jhyarrow.myWeb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jhyarrow.myWeb.domain.Blog;
import com.jhyarrow.myWeb.mapper.BlogMapper;
import com.jhyarrow.myWeb.service.BlogService;

public class BlogServiceImpl implements BlogService{

	@Autowired
	private BlogMapper blogmapper;
	
	public List<Blog> getBlogList() {
		return blogmapper.getBlogList();
	}

	public Blog getBlog(int id) {
		return blogmapper.getBlog(id);
	}
	
	public void addBlog(Blog blog) {
		blogmapper.addBlog(blog);
	}

}
