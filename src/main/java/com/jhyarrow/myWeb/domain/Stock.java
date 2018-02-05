package com.jhyarrow.myWeb.domain;

public class Stock {
	private String code;
	private String open_today;
	private String volumn;
	private String highest;
	private String harden;
	private String invol;
	private String turn_volume;
	private String the_committee;
	private String famc;
	private String pe_ratio;
	private String earning_per_share;
	private String general_capital;
	private String close_lastday;
	private String turnover_rate;
	private String lowest;
	private String low_limit;
	private String outer_disc;
	private String swing;
	private String lmr;
	private String market_cap;
	private String pb_ratio;
	private String asset_per_stock;
	private String flow_of_equity;
	private String name;
	private String up;
	private String up_per;
	private String date;
	private String close_today;
	public String getClose_today() {
		return close_today;
	}
	public void setClose_today(String close_today) {
		this.close_today = close_today;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getOpen_today() {
		return open_today;
	}
	public void setOpen_today(String open_today) {
		this.open_today = open_today;
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
	public String getTurn_volume() {
		return turn_volume;
	}
	public void setTurn_volume(String turn_volume) {
		this.turn_volume = turn_volume;
	}
	public String getThe_committee() {
		return the_committee;
	}
	public void setThe_committee(String the_committee) {
		this.the_committee = the_committee;
	}
	public String getFamc() {
		return famc;
	}
	public void setFamc(String famc) {
		this.famc = famc;
	}
	public String getPe_ratio() {
		return pe_ratio;
	}
	public void setPe_ratio(String pe_ratio) {
		this.pe_ratio = pe_ratio;
	}
	public String getEarning_per_share() {
		return earning_per_share;
	}
	public void setEarning_per_share(String earning_per_share) {
		this.earning_per_share = earning_per_share;
	}
	public String getGeneral_capital() {
		return general_capital;
	}
	public void setGeneral_capital(String general_capital) {
		this.general_capital = general_capital;
	}
	public String getClose_lastday() {
		return close_lastday;
	}
	public void setClose_lastday(String close_lastday) {
		this.close_lastday = close_lastday;
	}
	public String getTurnover_rate() {
		return turnover_rate;
	}
	public void setTurnover_rate(String turnover_rate) {
		this.turnover_rate = turnover_rate;
	}
	public String getLowest() {
		return lowest;
	}
	public void setLowest(String lowest) {
		this.lowest = lowest;
	}
	public String getLow_limit() {
		return low_limit;
	}
	public void setLow_limit(String low_limit) {
		this.low_limit = low_limit;
	}
	public String getOuter_disc() {
		return outer_disc;
	}
	public void setOuter_disc(String outer_disc) {
		this.outer_disc = outer_disc;
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
	public String getMarket_cap() {
		return market_cap;
	}
	public void setMarket_cap(String market_cap) {
		this.market_cap = market_cap;
	}
	public String getPb_ratio() {
		return pb_ratio;
	}
	public void setPb_ratio(String pb_ratio) {
		this.pb_ratio = pb_ratio;
	}
	public String getAsset_per_stock() {
		return asset_per_stock;
	}
	public void setAsset_per_stock(String asset_per_stock) {
		this.asset_per_stock = asset_per_stock;
	}
	public String getFlow_of_equity() {
		return flow_of_equity;
	}
	public void setFlow_of_equity(String flow_of_equity) {
		this.flow_of_equity = flow_of_equity;
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
	public String getUp_per() {
		return up_per;
	}
	public void setUp_per(String up_per) {
		this.up_per = up_per;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String toString() {
		return "股票代码："+code+"股票名称："+name+"日期："+date+"涨跌："+up+"涨跌比率："+up_per+"今开："+open_today+"昨收："+close_lastday+"成交量："+
				volumn+"最高："+highest+"涨停："+harden+"内盘："+invol+"成交额："+turn_volume+"委比："+the_committee+"流通市值："+famc+"市盈率："+
				pe_ratio+"每股收益："+earning_per_share+"总股本："+general_capital+"换手率："+turnover_rate+"最低："+lowest+"跌停："+low_limit+
				"外盘："+outer_disc+"振幅："+swing+"量比："+lmr+"总市值："+market_cap+"市净率："+pb_ratio+"每股净资产："+asset_per_stock+
				"流通股本："+flow_of_equity;
	}
}
