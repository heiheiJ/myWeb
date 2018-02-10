package com.jhyarrow.myWeb.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jhyarrow.myWeb.domain.Blog;
import com.jhyarrow.myWeb.service.BlogService;

@Controller
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	//博客列表页面
	@RequestMapping("/getBlogList")
	public ModelAndView getBlogList(){
		ArrayList<Blog> blogList = (ArrayList<Blog>) blogService.getBlogList();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("blogList",blogList);
        modelAndView.setViewName("/blog/getBlogList");

        return modelAndView;
    }
	
	//查看博客页面
	@RequestMapping("/getBlog")
	public ModelAndView getBlog(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		Blog blog = blogService.getBlog(id);
		blog.setInfo(blog.getInfo().replaceAll("<", "&lt;").replaceAll(">", "&gt;")
				.replaceAll("	", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;").replaceAll("\\n", "<br>"));
		ModelAndView mv = new ModelAndView();
		mv.addObject("blog",blog);
		mv.setViewName("/blog/getBlog");
		return mv;
	}

	//添加博客初始页面
	@RequestMapping("/addBlogView")
	public ModelAndView addBlogView() {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/blog/addBlog");
		return mv;
	}
	
	//添加博客数据
	@RequestMapping("/addBlog")
	public ModelAndView addBlog(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		try {
			Blog blog = new Blog();
			blog.setInfo(request.getParameter("info"));
			blog.setTitle(request.getParameter("title"));
			String type = request.getParameter("type");
			if("1".equals(type)) {
				type = "读书笔记";
			}else if("2".equals(type)){
				type = "工作计划";
			}
			blog.setType(type);
			blogService.addBlog(blog);
		}catch(Exception e) {
			e.printStackTrace();
			mv.setViewName("failure");
			return mv;
		}
		mv.setViewName("success");
		return mv;
	}
}
