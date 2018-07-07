package com.jhyarrow.myWeb.service;

import java.util.List;
import java.util.Map;

import com.jhyarrow.myWeb.domain.Blog;

public interface BlogService {
	//获取博客列表
	public List<Blog> getBlogList(int pageNum,int pageSize);
	
	
	
	
	public Blog getBlog(int id);
	public void addBlog(Blog blog);
	public int getBlogListCount(Map<String, Object> userMap);
}
