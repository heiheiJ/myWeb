package com.jhyarrow.myWeb.domain;

import java.util.List;

public class Stock {
	private String stockName;
	private String stockCode;
	private String url;
	private List<StockDaily> stockDailyList;
	
	public List<StockDaily> getStockDailyList() {
		return stockDailyList;
	}
	public void setStockDailyList(List<StockDaily> stockDailyList) {
		this.stockDailyList = stockDailyList;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
