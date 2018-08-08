package com.jhyarrow.myWeb.service;

import java.util.ArrayList;

import com.jhyarrow.myWeb.domain.SpiderStockDailyAllError;
import com.jhyarrow.myWeb.domain.SpiderStockDailyError;
import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.view.StockDailyView;

public interface SupportService {
	//获取MACD，从第一天开始
	public void getMACD() throws Exception;
	//获取9日内最高最低值
	public void get9();	
	//获取KDJ,从第一天开始
	public void getKDJ();
	//获取爬虫错误记录列表
	public ArrayList<SpiderStockDailyAllError> getSpiderStockDailyAllErrorList();
	//删除爬虫错误记录
	public void deleteSpiderStockDailyAllError(SpiderStockDailyAllError ssde);
	//获取趋势线
	public void getLine();
	//计算5日趋势推荐
	public void getSupport5(Stock s);
	//计算Dual Thrust
	public void getDualThrust(Stock s);
	
	
	
	//获取第tradeDay天的MACD
	public void getMACD(int tradeDay);
	
	//获取金针探底
	public void updateGoldenNeedle(String stockCode);
	
	//获取tradeDay日金针探底的stockDaily
	public ArrayList<StockDailyView> getGoldenNeedle(String tradeDay);
	
	//获取MACD，从第tradeDay天开始
	public void getMACD(String stockCode,int tradeDay)  throws Exception;
	
	//获取爬虫错误记录列表
	public ArrayList<SpiderStockDailyError> getSpiderStockDailyErrorList();
	//删除爬虫错误记录
	public void deleteSpiderStockDailyError(SpiderStockDailyError ssde);	
	//获取第tradeDay的KDJ
	public void getKDJ(int tradeDay);
	
}
