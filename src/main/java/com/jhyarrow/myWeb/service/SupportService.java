package com.jhyarrow.myWeb.service;

import java.util.ArrayList;

import com.jhyarrow.myWeb.domain.SpiderStockDailyError;
import com.jhyarrow.myWeb.view.StockDailyView;

public interface SupportService {
	//获取MACD，从第一天开始
	public void getMACD(String stockCode) throws Exception;
	
	//获取第tradeDay天的MACD
	public void getMACD(int tradeDay);
	
	//更新第tradeDay交易日的近3日走势
	public void updateLine3(int tradeDay);
	
	//获取金针探底
	public void updateGoldenNeedle(String stockCode);
	
	//获取macd金叉
	public void addMacdGoldenCross(String stockCode);
	
	//更新所有金叉数据
	public void updateMacdGoldenCross(String stockCode);
	
	//获取tradeDay日金针探底的stockDaily
	public ArrayList<StockDailyView> getGoldenNeedle(String tradeDay);
	
	//获取MACD，从第tradeDay天开始
	public void getMACD(String stockCode,int tradeDay)  throws Exception;
	
	//获取爬虫错误记录列表
	public ArrayList<SpiderStockDailyError> getSpiderStockDailyErrorList();
		
	//删除爬虫错误记录
	public void deleteSpiderStockDailyError(SpiderStockDailyError ssde);
	
	//获取KDJ,从第一天开始
	public void getKDJ(String stockCode);
	
	//获取第tradeDay的KDJ
	public void getKDJ(int tradeDay);
	
	//获取9日内最高最低值
	public void get9(String stockCode);
}
