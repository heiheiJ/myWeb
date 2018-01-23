package com.jhyarrow.myWeb.domain;

import org.springframework.format.annotation.DateTimeFormat;

public class Blog {
	private int id ;
	private int rowno;
	private String title;
	private String info;
	private String input_date;
	private String type;
	
	public int getRowno() {
		return rowno;
	}
	public void setRowno(int rowno) {
		this.rowno = rowno;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	public String getInput_date() {
		return input_date;
	}
	public void setInput_date(String input_date) {
		this.input_date = input_date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	public String toString(){
		return "title:" + title + "info:" + info;
	}

}
