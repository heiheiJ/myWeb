package com.jhyarrow.myWeb.domain.support;

public class SupportMacd {
	private String stockCode;
	private String stockName;
	private Integer startDay;
	private Integer endDay;
	private float startPrice;
	private float endPrice;
	private float upPer;
	private float startBar;
	private float endBar;
	
	public float getStartBar() {
		return startBar;
	}
	public void setStartBar(float startBar) {
		this.startBar = startBar;
	}
	public float getEndBar() {
		return endBar;
	}
	public void setEndBar(float endBar) {
		this.endBar = endBar;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
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
