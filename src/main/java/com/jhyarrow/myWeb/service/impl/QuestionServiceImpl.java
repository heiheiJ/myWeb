package com.jhyarrow.myWeb.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.jhyarrow.myWeb.domain.Question;
import com.jhyarrow.myWeb.mapper.QuestionMapper;
import com.jhyarrow.myWeb.service.QuestionService;

public class QuestionServiceImpl implements QuestionService{
	@Autowired
	private QuestionMapper questionMapper;

	public void addQuestion(Question question) {
		questionMapper.addQuestion(question);
	}

	public ArrayList<Question> getQuestionList() {
		return questionMapper.getQuestionList();
	}

	public Question getQuestion(String id) {
		return questionMapper.getQuestion(id);
	}

	public void updateQuestion(Question question) {
		questionMapper.updateQuestion(question);
	}
	
}
