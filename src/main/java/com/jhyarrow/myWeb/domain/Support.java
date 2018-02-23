package com.jhyarrow.myWeb.domain;

public class Support {
	private String code;
	private String id ;
	private String name;
	private String date;
	private String reason;
	private int tradeDay;
	private double up;
	private String isTrue;
	
	public double getUp() {
		return up;
	}
	public void setUp(double up) {
		this.up = up;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIsTrue() {
		return isTrue;
	}
	public void setIsTrue(String isTrue) {
		this.isTrue = isTrue;
	}
	public int getTradeDay() {
		return tradeDay;
	}
	public void setTradeDay(int tradeDay) {
		this.tradeDay = tradeDay;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
