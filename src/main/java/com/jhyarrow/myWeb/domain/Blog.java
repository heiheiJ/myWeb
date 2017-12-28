package com.jhyarrow.myWeb.domain;

public class Blog {
	private int id ;
	private String title;
	private String info;
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
