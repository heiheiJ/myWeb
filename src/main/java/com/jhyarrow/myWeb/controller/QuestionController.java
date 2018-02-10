package com.jhyarrow.myWeb.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jhyarrow.myWeb.domain.Blog;
import com.jhyarrow.myWeb.domain.Question;
import com.jhyarrow.myWeb.service.QuestionService;

@Controller
public class QuestionController {
	@Autowired
	private QuestionService questionService;
	
	@RequestMapping("/getQuestionList")
	public ModelAndView getQuestionList() {
		ArrayList<Question> list = questionService.getQuestionList();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", list);
        modelAndView.setViewName("/question/getQuestionList");

        return modelAndView;
	}
	
	@RequestMapping("/getQuestion")
	public ModelAndView getQuestion(HttpServletRequest request) {
		String id = request.getParameter("id");
		Question question = questionService.getQuestion(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("question",question);
		modelAndView.setViewName("/question/getQuestion");
		return modelAndView;
	}
	
	@RequestMapping("/addQuestionView")
	public ModelAndView addQuestionView() {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/question/addQuestion");
		return mv;
	}
	
	@RequestMapping("/addQuestion")
	public ModelAndView addBlog(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		try {
			Question question = new Question();
			question.setQuestion(request.getParameter("question"));
			question.setType("未解答");
			questionService.addQuestion(question);
		}catch(Exception e) {
			e.printStackTrace();
			mv.setViewName("failure");
			return mv;
		}
		mv.setViewName("success");
		return mv;
	}
	
	@RequestMapping("/updateQuestion")
	public ModelAndView updateQuestion(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		try {
			String id = request.getParameter("id");
			Question question = questionService.getQuestion(id);
			question.setAnswer(request.getParameter("answer"));
			question.setType("已解答");
			questionService.updateQuestion(question);
		}catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("failure");
			return mv;
		}
		mv.setViewName("success");
		return mv;
	}
}
