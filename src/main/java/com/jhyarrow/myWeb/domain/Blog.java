package com.jhyarrow.myWeb.domain;

import java.sql.Timestamp;

public class Blog {
	private int id ;
	private String title;
	private String info;
	private Timestamp input_date;
	private String type;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Timestamp getInput_date() {
		return input_date;
	}
	public void setInput_date(Timestamp input_date) {
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
