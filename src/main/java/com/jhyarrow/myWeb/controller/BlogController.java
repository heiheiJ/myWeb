package com.jhyarrow.myWeb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
	public ModelAndView getBlogList(HttpServletRequest request){
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		int page = request.getAttribute("page") == null ? Integer.parseInt(request.getParameter("page")) 
				:Integer.parseInt((String)request.getAttribute("page"));
		Map<String, Object> userMap = new HashMap<String,Object>();	
		if(username == null || username == "") {
			userMap.put("type", "读书笔记");
		}else {
			userMap.put("type", null);
		}
		userMap.put("offset", page*10);
		userMap.put("rows", 10);
		ArrayList<Blog> blogList = (ArrayList<Blog>) blogService.getBlogList(userMap);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("blogList",blogList);
        modelAndView.setViewName("/blog/getBlogList");
        modelAndView.addObject("page",page);
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
	
	@RequestMapping("/getBlogListPrevious")
	public ModelAndView getBlogListPrevious(HttpServletRequest request) {
		int page = Integer.parseInt(request.getParameter("page"));
		if(page == 0) {
			page = 0;
		}else {
			page --;
		}
		request.setAttribute("page", String.valueOf(page));
		return getBlogList(request);
	}
	
	@RequestMapping("/getBlogListNext")
	public ModelAndView getBlogListNext(HttpServletRequest request) {
		int page = Integer.parseInt(request.getParameter("page"));
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		Map<String, Object> userMap = new HashMap<String,Object>();	
		if(username == null || username == "") {
			userMap.put("type", "读书笔记");
		}else {
			userMap.put("type", null);
		}
		int count = blogService.getBlogListCount(userMap);
		if(page+1 >= (count-1)/10) {
			page = (count-1)/10;
		}else {
			page ++;
		}
		request.setAttribute("page", String.valueOf(page));
		return getBlogList(request);
	}
}
