package com.jhyarrow.myWeb.dao;

import java.util.List;

import com.jhyarrow.myWeb.domain.Blog;

public interface BlogMapper {
	public Blog getBlog(int id);
	public List<Blog> getBlogList();
	public void addBlog(Blog blog);
}
