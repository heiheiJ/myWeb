package com.jhyarrow.myWeb.domain;

public class StockDaily {
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
	private String ema12;
	private String ema26;
	private String diff;
	private String dea;
	private String bar;
	private String upLevel;
	private Integer nextTradeDay;//下一个交易日
	private Integer prevTradeDay;//上一个交易日
	private float rsv;
	private float k;
	private float d;
	private float j;
	private float max9;
	private float min9;
	
	public float getMax9() {
		return max9;
	}

	public void setMax9(float max9) {
		this.max9 = max9;
	}

	public float getMin9() {
		return min9;
	}

	public void setMin9(float min9) {
		this.min9 = min9;
	}

	public Integer getPrevTradeDay() {
		return prevTradeDay;
	}

	public void setPrevTradeDay(Integer prevTradeDay) {
		this.prevTradeDay = prevTradeDay;
	}

	public float getRsv() {
		return rsv;
	}

	public void setRsv(float rsv) {
		this.rsv = rsv;
	}

	public float getK() {
		return k;
	}

	public void setK(float k) {
		this.k = k;
	}

	public float getD() {
		return d;
	}

	public void setD(float d) {
		this.d = d;
	}

	public float getJ() {
		return j;
	}

	public void setJ(float j) {
		this.j = j;
	}

	public Integer getNextTradeDay() {
		return nextTradeDay;
	}

	public void setNextTradeDay(Integer nextTradeDay) {
		this.nextTradeDay = nextTradeDay;
	}

	public String getUpLevel() {
		return upLevel;
	}

	public void setUpLevel(String upLevel) {
		this.upLevel = upLevel;
	}

	public String getDea() {
		return dea;
	}

	public void setDea(String dea) {
		this.dea = dea;
	}

	public String getBar() {
		return bar;
	}

	public void setBar(String bar) {
		this.bar = bar;
	}

	public String getEma12() {
		return ema12;
	}

	public void setEma12(String ema12) {
		this.ema12 = ema12;
	}

	public String getEma26() {
		return ema26;
	}

	public void setEma26(String ema26) {
		this.ema26 = ema26;
	}

	public String getDiff() {
		return diff;
	}

	public void setDiff(String diff) {
		this.diff = diff;
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

	public String toString() {
		return "股票代码："+stockCode+"股票名称："+stockName+"日期："+date+"交易日"+tradeDay+"涨跌："+up+"涨跌比率："+upPer+"今开："+openToday+"今收"+closeToday
				+"最高"+highest+"最低"+lowest+"成交量"+volumn+"成交额"+turnVolume+"万元，换手率"+turnoverRate;
	}
}
