package com.jhyarrow.myWeb.view;

public class StockDailyView {
	private String stockCode;//股票代码
	private String stockName;//股票名称
	private String openToday;//今开
	private String closeToday;//今收
	private String highest;//最高
	private String lowest;//最低
	private String up;//涨跌
	private String upPer;//涨跌比率
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
	public String getOpenToday() {
		return openToday;
	}
	public void setOpenToday(String openToday) {
		this.openToday = openToday;
	}
	public String getCloseToday() {
		return closeToday;
	}
	public void setCloseToday(String closeToday) {
		this.closeToday = closeToday;
	}
	public String getHighest() {
		return highest;
	}
	public void setHighest(String highest) {
		this.highest = highest;
	}
	public String getLowest() {
		return lowest;
	}
	public void setLowest(String lowest) {
		this.lowest = lowest;
	}
	public String getUp() {
		return up;
	}
	public void setUp(String up) {
		this.up = up;
	}
	public String getUpPer() {
		return upPer;
	}
	public void setUpPer(String upPer) {
		this.upPer = upPer;
	}
	
	
}
