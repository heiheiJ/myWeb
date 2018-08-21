package com.jhyarrow.myWeb.service;

import java.util.List;
import java.util.Map;

import com.jhyarrow.myWeb.domain.Blog;
import com.jhyarrow.myWeb.exception.MyException;

public interface BlogService {
	//获取博客列表
	public List<Blog> getBlogList();
	//添加博客
	public void addBlog(Blog blog);
	//查询博客
	public Blog getBlog(int id) throws Exception;
	
	public int getBlogListCount(Map<String, Object> userMap);
}
