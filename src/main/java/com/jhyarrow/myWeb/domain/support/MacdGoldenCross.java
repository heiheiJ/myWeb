package com.jhyarrow.myWeb.domain.support;

public class MacdGoldenCross {
	private String stockCode;
	private Integer tradeDay;
	private float upPer1;
	private float upPer5;
	private float upPer10;
	private String bar;
	
	public String getBar() {
		return bar;
	}
	public void setBar(String bar) {
		this.bar = bar;
	}
	public float getUpPer1() {
		return upPer1;
	}
	public void setUpPer1(float upPer1) {
		this.upPer1 = upPer1;
	}
	public float getUpPer5() {
		return upPer5;
	}
	public void setUpPer5(float upPer5) {
		this.upPer5 = upPer5;
	}
	public float getUpPer10() {
		return upPer10;
	}
	public void setUpPer10(float upPer10) {
		this.upPer10 = upPer10;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public Integer getTradeDay() {
		return tradeDay;
	}
	public void setTradeDay(Integer tradeDay) {
		this.tradeDay = tradeDay;
	}
	
}
