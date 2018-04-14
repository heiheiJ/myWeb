package com.jhyarrow.myWeb.domain;

import org.springframework.format.annotation.DateTimeFormat;

public class PlateDaily {
	private int id;
	private int date;
	private String name;
	private String per;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")  
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPer() {
		return per;
	}
	public void setPer(String per) {
		this.per = per;
	}
	
	
}
