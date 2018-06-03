package com.jhyarrow.myWeb.domain;

public class Stock {
	private String stockName;
	private String stockCode;
	private String comName;
	private Integer lastTradeDay;
	
	public Integer getLastTradeDay() {
		return lastTradeDay;
	}
	public void setLastTradeDay(Integer lastTradeDay) {
		this.lastTradeDay = lastTradeDay;
	}
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
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	
	
}
