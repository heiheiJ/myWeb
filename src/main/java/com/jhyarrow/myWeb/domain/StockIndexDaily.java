package com.jhyarrow.myWeb.domain;

import org.springframework.format.annotation.DateTimeFormat;

public class StockIndexDaily {
	private String stockCode;//股票代码
	private String stockName;//股票名称
	private String date;//日期
	private Integer tradeDay;//当前第几交易日
	private String openToday;//今开
	private String closeToday;//今收
	private String up;//涨跌
	private String upPer;//涨跌比率
	private String highest;//最高
	private String lowest;//最低
	private String volumn;//成交量
	private String turnVolume;//成交额(万元)
	private String turnoverRate;//换手率
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
	
	@DateTimeFormat(pattern="yyyy-MM-dd") 
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
	public String getVolumn() {
		return volumn;
	}
	public void setVolumn(String volumn) {
		this.volumn = volumn;
	}
	public String getTurnVolume() {
		return turnVolume;
	}
	public void setTurnVolume(String turnVolume) {
		this.turnVolume = turnVolume;
	}
	public String getTurnoverRate() {
		return turnoverRate;
	}
	public void setTurnoverRate(String turnoverRate) {
		this.turnoverRate = turnoverRate;
	}
	
}
