package com.jhyarrow.myWeb.service;

import java.util.ArrayList;

import com.jhyarrow.myWeb.view.StockDailyView;

public interface SupportService {
	//获取MACD，从第一天开始
	public void getMACD(String stockCode) throws Exception;
	
	//获取MACD，从第tradeDay天开始
	public void getMACDDay(String stockCode,int tradeDay)  throws Exception;
	
	//更新第tradeDay交易日的近3日走势
	public void updateLine3(int tradeDay);
	
	//获取金针探底
	public void updateGoldenNeedle(String stockCode);
	
	//获取tradeDay日金针探底的stockDaily
	public ArrayList<StockDailyView> getGoldenNeedle(String tradeDay);
}
