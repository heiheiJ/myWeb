package com.jhyarrow.myWeb.mapper;

import java.util.List;
import java.util.Map;

import com.jhyarrow.myWeb.domain.Blog;

public interface BlogMapper {
	public Blog getBlog(int id);
	public List<Blog> getBlogList(Map<String, Object> userMap);
	public void addBlog(Blog blog);
	public int getBlogListCount(Map<String, Object> userMap);
}
