package com.jhyarrow.myWeb.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.jhyarrow.myWeb.domain.StockIndexDaily;
import com.jhyarrow.myWeb.domain.TradeDay;
import com.jhyarrow.myWeb.mapper.StockIndexMapper;
import com.jhyarrow.myWeb.mapper.TradeDayMapper;
import com.jhyarrow.myWeb.service.TradeDayService;

public class TradeDayServiceImpl implements TradeDayService{
	@Autowired
	private TradeDayMapper tradeDayMapper;
	@Autowired
	private StockIndexMapper stockIndexMapper;

	//清空trade_day表
	public void truncateTradeDay() {
		tradeDayMapper.truncateTradeDay();
	}
	
	//添加所有trade_day
	public void addTradeDayList() {
		ArrayList<StockIndexDaily> list = stockIndexMapper.getStockIndexDailyByCode("000001");
		for(int i=0;i<list.size();i++) {
			StockIndexDaily sid = list.get(i);
			TradeDay tradeDay = new TradeDay();
			tradeDay.setDate(sid.getDate());
			tradeDay.setTradeDay(i);
			tradeDayMapper.addTradeDay(tradeDay);
		}
	}
	//获取date的tradeDay
	public Integer getTradeDayByDate(String date) {
		return tradeDayMapper.getTradeDayByDate(date);
	}
	
	public ArrayList<TradeDay> getTradeDayList() {
		return tradeDayMapper.getTradeDayList();
	}
	
	


	
}
