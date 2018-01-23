package com.jhyarrow.myWeb.service;

import java.util.List;

import com.jhyarrow.myWeb.domain.Blog;

public interface BlogService {
	public List<Blog> getBlogList();
	public Blog getBlog(int id);
	public void addBlog(Blog blog);
}
