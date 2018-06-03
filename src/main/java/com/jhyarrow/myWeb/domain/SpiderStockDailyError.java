package com.jhyarrow.myWeb.domain;

public class SpiderStockDailyError {
	private String stockCode;
	private String date;
	private Integer tradeDay;
	private String stockName;
	
	
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getTradeDay() {
		return tradeDay;
	}
	public void setTradeDay(Integer tradeDay) {
		this.tradeDay = tradeDay;
	}
	
	
}
