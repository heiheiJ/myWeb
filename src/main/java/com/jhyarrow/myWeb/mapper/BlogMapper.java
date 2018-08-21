package com.jhyarrow.myWeb.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jhyarrow.myWeb.domain.Blog;

public interface BlogMapper {
	//获取博客列表
	public List<Blog> getBlogList();
	
	
	
	public Blog getBlog(int id);
	public void addBlog(Blog blog);
	public int getBlogListCount(Map<String, Object> userMap);
}
