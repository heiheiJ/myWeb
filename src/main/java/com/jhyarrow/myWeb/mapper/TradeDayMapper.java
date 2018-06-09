package com.jhyarrow.myWeb.mapper;

import java.util.ArrayList;

import com.jhyarrow.myWeb.domain.TradeDay;

public interface TradeDayMapper {
	//清空trade_day表
	public void truncateTradeDay();
	//添加tradeDay
	public void addTradeDay(TradeDay tradeDay);
	//获取date的tradeDay
	public Integer getTradeDayByDate(String date);
	
	
	public ArrayList<TradeDay> getTradeDayList();
	
	
}
