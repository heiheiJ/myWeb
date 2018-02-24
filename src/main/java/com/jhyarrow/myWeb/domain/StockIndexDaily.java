package com.jhyarrow.myWeb.domain;

public class StockIndexDaily {
	private String code;
	private String highest;//最高
	private String lowest;//最低
	private String openToday;//今开
	private String closeToday;//今收
	private String turnVolume;//成交额
	private String volumn;//成交量
	private String higher;//涨家数
	private String lower;//跌家数
	private String equal;//平家数
	private String up;//涨跌
	private String upPer;//涨跌比率
	private String name;//指数名称
	private Integer tradeDay;//当年第几交易日
	private Integer weekDay;//星期几
	private String date;//日期
	
	
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
	public Integer getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(Integer weekDay) {
		this.weekDay = weekDay;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getTurnVolume() {
		return turnVolume;
	}
	public void setTurnVolume(String turnVolume) {
		this.turnVolume = turnVolume;
	}
	public String getVolumn() {
		return volumn;
	}
	public void setVolumn(String volumn) {
		this.volumn = volumn;
	}
	public String getHigher() {
		return higher;
	}
	public void setHigher(String higher) {
		this.higher = higher;
	}
	public String getLower() {
		return lower;
	}
	public void setLower(String lower) {
		this.lower = lower;
	}
	public String getEqual() {
		return equal;
	}
	public void setEqual(String equal) {
		this.equal = equal;
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
