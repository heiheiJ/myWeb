package com.jhyarrow.myWeb.service;

public interface SpiderService {
	//获取当天指数数据
	public void spideStockIndexDaily(String start,String end);
	
	//获取股票数据
	public void spideStockDaily(String code,String stockName,String start,String end);
	
	//获取所有股票
	public void spideStock();
	
}
