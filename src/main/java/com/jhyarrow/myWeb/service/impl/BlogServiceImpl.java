package com.jhyarrow.myWeb.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhyarrow.myWeb.domain.Blog;
import com.jhyarrow.myWeb.mapper.BlogMapper;
import com.jhyarrow.myWeb.service.BlogService;

public class BlogServiceImpl implements BlogService{

	@Autowired
	private BlogMapper blogmapper;
	
	public List<Blog> getBlogList(Map<String, Object> userMap) {
		return blogmapper.getBlogList(userMap);
	}

	public Blog getBlog(int id) {
		return blogmapper.getBlog(id);
	}
	
	public void addBlog(Blog blog) {
		blogmapper.addBlog(blog);
	}
	
	public int getBlogListCount(Map<String, Object> userMap) {
		return blogmapper.getBlogListCount(userMap);
	}

}
