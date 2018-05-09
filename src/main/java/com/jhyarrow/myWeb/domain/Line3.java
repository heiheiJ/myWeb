package com.jhyarrow.myWeb.domain;

public class Line3 {
	private String day1;
	private String day2;
	private String day3;
	private Integer upDays;
	private Integer downDays;
	private float avgUp;
	private float avgDown;
	
	public float getAvgUp() {
		return avgUp;
	}
	public void setAvgUp(float avgUp) {
		this.avgUp = avgUp;
	}
	public float getAvgDown() {
		return avgDown;
	}
	public void setAvgDown(float avgDown) {
		this.avgDown = avgDown;
	}
	public String getDay1() {
		return day1;
	}
	public void setDay1(String day1) {
		this.day1 = day1;
	}
	public String getDay2() {
		return day2;
	}
	public void setDay2(String day2) {
		this.day2 = day2;
	}
	public String getDay3() {
		return day3;
	}
	public void setDay3(String day3) {
		this.day3 = day3;
	}
	public Integer getUpDays() {
		return upDays;
	}
	public void setUpDays(Integer upDays) {
		this.upDays = upDays;
	}
	public Integer getDownDays() {
		return downDays;
	}
	public void setDownDays(Integer downDays) {
		this.downDays = downDays;
	}
	
}
