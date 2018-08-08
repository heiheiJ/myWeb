package com.jhyarrow.myWeb.domain;

public class HgtStockDaily {
	private Integer tradeDay;
	private String stockName;
	private String stockCode;
	private String closeToday;
	private float jingAmt;//净买入额（万元）
	private float buyAmt;//买入额（万元）
	private float sellAmt;//卖出额（万元）
	private float totalAmt;//交易总额（万元）
	private float nextUp;
	private float nextOpenToday;
	public Integer getTradeDay() {
		return tradeDay;
	}
	public void setTradeDay(Integer tradeDay) {
		this.tradeDay = tradeDay;
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
	public String getCloseToday() {
		return closeToday;
	}
	public void setCloseToday(String closeToday) {
		this.closeToday = closeToday;
	}
	public float getJingAmt() {
		return jingAmt;
	}
	public void setJingAmt(float jingAmt) {
		this.jingAmt = jingAmt;
	}
	public float getBuyAmt() {
		return buyAmt;
	}
	public void setBuyAmt(float buyAmt) {
		this.buyAmt = buyAmt;
	}
	public float getSellAmt() {
		return sellAmt;
	}
	public void setSellAmt(float sellAmt) {
		this.sellAmt = sellAmt;
	}
	public float getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(float totalAmt) {
		this.totalAmt = totalAmt;
	}
	public float getNextUp() {
		return nextUp;
	}
	public void setNextUp(float nextUp) {
		this.nextUp = nextUp;
	}
	public float getNextOpenToday() {
		return nextOpenToday;
	}
	public void setNextOpenToday(float nextOpenToday) {
		this.nextOpenToday = nextOpenToday;
	}
	
}
