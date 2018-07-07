package com.jhyarrow.myWeb.domain;

public class Line {
	private String stockCode;
	private Integer startDay;
	private Integer endDay;
	private float startPrice;
	private float endPrice;
	private float upPer;
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public Integer getStartDay() {
		return startDay;
	}
	public void setStartDay(Integer startDay) {
		this.startDay = startDay;
	}
	public Integer getEndDay() {
		return endDay;
	}
	public void setEndDay(Integer endDay) {
		this.endDay = endDay;
	}
	public float getStartPrice() {
		return startPrice;
	}
	public void setStartPrice(float startPrice) {
		this.startPrice = startPrice;
	}
	public float getEndPrice() {
		return endPrice;
	}
	public void setEndPrice(float endPrice) {
		this.endPrice = endPrice;
	}
	public float getUpPer() {
		return upPer;
	}
	public void setUpPer(float upPer) {
		this.upPer = upPer;
	}
	
	
	
}
