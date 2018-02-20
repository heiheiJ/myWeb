package com.jhyarrow.myWeb.domain;

public class Stock {
	private String code;
	private String openToday;
	private String volumn;
	private String highest;
	private String harden;
	private String invol;
	private String turnVolume;
	private String theCommittee;
	private String famc;
	private String peRatio;
	private String earningPerShare;
	private String generalCapital;
	private String closeLastday;
	private String turnoverRate;
	private String lowest;
	private String lowLimit;
	private String outerDisc;
	private String swing;
	private String lmr;
	private String marketCap;
	private String pbRatio;
	private String assetPerStock;
	private String flowOfEquity;
	private String name;
	private String up;
	private String upPer;
	private String date;
	private String closeToday;
	
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
