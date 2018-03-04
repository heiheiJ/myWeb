package com.jhyarrow.myWeb.domain;

public class StockDaily {
	private String code;//股票代码
	private String openToday;//今开
	private String volumn;//成交量
	private String highest;//最高
	private String harden;//涨停
	private String invol;//内盘
	private String turnVolume;//成交额
	private String theCommittee;//委比
	private String famc;//流通市值
	private String peRatio;//市盈率
	private String earningPerShare;//每股收益
	private String generalCapital;//总股本
	private String closeLastday;//昨收
	private String turnoverRate;//换手率
	private String lowest;//最低
	private String lowLimit;//跌停
	private String outerDisc;//外盘
	private String swing;//振幅
	private String lmr;//量比
	private String marketCap;//总市值
	private String pbRatio;//市净率
	private String assetPerStock;//每股净资产
	private String flowOfEquity;//流通股本
	private String name;//名称
	private String up;//涨跌
	private String upPer;//涨跌比率
	private String date;//日期
	private String closeToday;//今收
	private Integer tradeDay;//当年第几交易日
	private Integer weekDay;//星期几
	
	public StockDaily() {
		
	}
	
	public StockDaily(String code,int tradeDay) {
		this.code = code;
		this.tradeDay = tradeDay;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOpenToday() {
		return openToday;
	}

	public void setOpenToday(String openToday) {
		this.openToday = openToday;
	}

	public String getVolumn() {
		return volumn;
	}

	public void setVolumn(String volumn) {
		this.volumn = volumn;
	}

	public String getHighest() {
		return highest;
	}

	public void setHighest(String highest) {
		this.highest = highest;
	}

	public String getHarden() {
		return harden;
	}

	public void setHarden(String harden) {
		this.harden = harden;
	}

	public String getInvol() {
		return invol;
	}

	public void setInvol(String invol) {
		this.invol = invol;
	}

	public String getTurnVolume() {
		return turnVolume;
	}

	public void setTurnVolume(String turnVolume) {
		this.turnVolume = turnVolume;
	}

	public String getTheCommittee() {
		return theCommittee;
	}

	public void setTheCommittee(String theCommittee) {
		this.theCommittee = theCommittee;
	}

	public String getFamc() {
		return famc;
	}

	public void setFamc(String famc) {
		this.famc = famc;
	}

	public String getPeRatio() {
		return peRatio;
	}

	public void setPeRatio(String peRatio) {
		this.peRatio = peRatio;
	}

	public String getEarningPerShare() {
		return earningPerShare;
	}

	public void setEarningPerShare(String earningPerShare) {
		this.earningPerShare = earningPerShare;
	}

	public String getGeneralCapital() {
		return generalCapital;
	}

	public void setGeneralCapital(String generalCapital) {
		this.generalCapital = generalCapital;
	}

	public String getCloseLastday() {
		return closeLastday;
	}

	public void setCloseLastday(String closeLastday) {
		this.closeLastday = closeLastday;
	}

	public String getTurnoverRate() {
		return turnoverRate;
	}

	public void setTurnoverRate(String turnoverRate) {
		this.turnoverRate = turnoverRate;
	}

	public String getLowest() {
		return lowest;
	}

	public void setLowest(String lowest) {
		this.lowest = lowest;
	}

	public String getLowLimit() {
		return lowLimit;
	}

	public void setLowLimit(String lowLimit) {
		this.lowLimit = lowLimit;
	}

	public String getOuterDisc() {
		return outerDisc;
	}

	public void setOuterDisc(String outerDisc) {
		this.outerDisc = outerDisc;
	}

	public String getSwing() {
		return swing;
	}

	public void setSwing(String swing) {
		this.swing = swing;
	}

	public String getLmr() {
		return lmr;
	}

	public void setLmr(String lmr) {
		this.lmr = lmr;
	}

	public String getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(String marketCap) {
		this.marketCap = marketCap;
	}

	public String getPbRatio() {
		return pbRatio;
	}

	public void setPbRatio(String pbRatio) {
		this.pbRatio = pbRatio;
	}

	public String getAssetPerStock() {
		return assetPerStock;
	}

	public void setAssetPerStock(String assetPerStock) {
		this.assetPerStock = assetPerStock;
	}

	public String getFlowOfEquity() {
		return flowOfEquity;
	}

	public void setFlowOfEquity(String flowOfEquity) {
		this.flowOfEquity = flowOfEquity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCloseToday() {
		return closeToday;
	}

	public void setCloseToday(String closeToday) {
		this.closeToday = closeToday;
	}

	public String toString() {
		return "股票代码："+code+"股票名称："+name+"日期："+date+"涨跌："+up+"涨跌比率："+upPer+"今开："+openToday+"昨收："+closeLastday+"成交量："+
				volumn+"最高："+highest+"涨停："+harden+"内盘："+invol+"成交额："+turnVolume+"委比："+theCommittee+"流通市值："+famc+"市盈率："+
				peRatio+"每股收益："+earningPerShare+"总股本："+generalCapital+"换手率："+turnoverRate+"最低："+lowest+"跌停："+lowLimit+
				"外盘："+outerDisc+"振幅："+swing+"量比："+lmr+"总市值："+marketCap+"市净率："+pbRatio+"每股净资产："+assetPerStock+
				"流通股本："+flowOfEquity;
	}
}
