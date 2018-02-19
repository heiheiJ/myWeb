package com.jhyarrow.myWeb.service;

import java.util.List;
import java.util.Map;

import com.jhyarrow.myWeb.domain.Blog;

public interface BlogService {
	public List<Blog> getBlogList(Map<String, Object> userMap);
	public Blog getBlog(int id);
	public void addBlog(Blog blog);
	public int getBlogListCount(Map<String, Object> userMap);
}
