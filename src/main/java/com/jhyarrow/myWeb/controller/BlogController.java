package com.jhyarrow.myWeb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.jhyarrow.myWeb.domain.Blog;
import com.jhyarrow.myWeb.exception.MyException;
import com.jhyarrow.myWeb.service.BlogService;

@Controller
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@ModelAttribute("typeList")
	public Map<String,String> getType(){
		Map<String,String> typeList = new HashMap<String,String>();
		typeList.put("1", "读书笔记");
		typeList.put("2", "工作计划");
		return typeList;
	}
	
	//博客列表页面
	@RequestMapping("/getBlogList")
	public ModelAndView getBlogList(int page){
		PageHelper.offsetPage(page*5, 5);
		ArrayList<Blog> blogList = (ArrayList<Blog>) blogService.getBlogList();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("blogList",blogList);
        modelAndView.setViewName("/blog/getBlogList");
        modelAndView.addObject("page",page);
        return modelAndView;
    }
	
	//查看博客页面
	@RequestMapping("/getBlog")
	public ModelAndView getBlog(HttpServletRequest request,@RequestParam(required = true) Integer id) throws Exception {
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
	public ModelAndView addBlog(HttpServletRequest request,Blog blog) {
		ModelAndView mv = new ModelAndView();
		try {
			String type = blog.getType();
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
	public ModelAndView getBlogListPrevious(int page) {
		if(page == 0) {
			page = 0;
		}else {
			page --;
		}
		return getBlogList(page);
	}
	
	@RequestMapping("/getBlogListNext")
	public ModelAndView getBlogListNext(HttpServletRequest request,int page) {
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		Map<String, Object> userMap = new HashMap<String,Object>();	
		if(username == null || username == "") {
			userMap.put("type", "读书笔记");
		}else {
			userMap.put("type", null);
		}
		int count = blogService.getBlogListCount(userMap);
		if(page+1 >= (count-1)/5) {
			page = (count-1)/5;
		}else {
			page ++;
		}
		
		return getBlogList(page);
	}
}
