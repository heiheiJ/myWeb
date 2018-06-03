package com.jhyarrow.myWeb.service;

public interface SpiderService {
	//获取当天股票数据
	public int spideStockDaily(String date);
	
	//获取当天指数数据
	public void spideStockIndexDaily(String date);
	
	//获取新的股票数据
	public void spideStockDaily(String code,String stockName,String start,String end);
	
	//获取新的股票数据
	public void spideStockDaily(String code,String stockName,int tradeDay,int lastTradeDay,String date);
}
