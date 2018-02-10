package com.jhyarrow.myWeb.mapper;

import java.util.ArrayList;

import com.jhyarrow.myWeb.domain.Question;

public interface QuestionMapper {
	public void addQuestion(Question question);
	public ArrayList<Question> getQuestionList();
	public Question getQuestion(String id);
	public void updateQuestion(Question question);
}
