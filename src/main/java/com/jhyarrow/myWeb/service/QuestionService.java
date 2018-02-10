package com.jhyarrow.myWeb.service;

import java.util.ArrayList;

import com.jhyarrow.myWeb.domain.Question;

public interface QuestionService {
	public void addQuestion(Question question);
	public ArrayList<Question> getQuestionList();
	public Question getQuestion(String id);
	public void updateQuestion(Question question);
}
