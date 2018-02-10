package com.jhyarrow.myWeb.domain;

import org.springframework.format.annotation.DateTimeFormat;

public class Question {
	private String date;
	private String id;
	private int rowno;
	private String question;
	private String type;
	private String answer;
	
	public int getRowno() {
		return rowno;
	}
	public void setRowno(int rowno) {
		this.rowno = rowno;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
