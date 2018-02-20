package com.jhyarrow.myWeb.domain;

import org.springframework.format.annotation.DateTimeFormat;

public class Blog {
	private int id ;
	private int rowno;
	private String title;
	private String info;
	private String inputDate;
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
	public String getInputDate() {
		return inputDate;
	}
	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
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
