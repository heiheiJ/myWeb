package com.jhyarrow.myWeb.mapper;

import java.util.ArrayList;

import com.jhyarrow.myWeb.domain.TradeDay;

public interface TradeDayMapper {
	//清空trade_day表
	public void truncateTradeDay();
	//添加tradeDay
	public void addTradeDay();
	//获取date的tradeDay
	public Integer getTradeDayByDate(String date);
	//获取tradeDay的date
	public String getTradeDayByTradeDay(Integer tradeDay);
	
	public ArrayList<TradeDay> getTradeDayList();
	
	
}
