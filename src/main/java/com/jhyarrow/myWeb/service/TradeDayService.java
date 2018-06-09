package com.jhyarrow.myWeb.service;

import java.util.ArrayList;

import com.jhyarrow.myWeb.domain.TradeDay;

public interface TradeDayService {
	//清空trade_day表
	public void truncateTradeDay();
	//添加所有trade_day
	public void addTradeDayList();
	//获取date的tradeDay
	public Integer getTradeDayByDate(String date);
	
	public ArrayList<TradeDay> getTradeDayList();
}
