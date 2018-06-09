package com.jhyarrow.myWeb.mapper;

import java.util.ArrayList;

import com.jhyarrow.myWeb.domain.Line3;
import com.jhyarrow.myWeb.domain.SpiderStockDailyAllError;
import com.jhyarrow.myWeb.domain.SpiderStockDailyError;
import com.jhyarrow.myWeb.domain.Support;
import com.jhyarrow.myWeb.domain.support.MacdGoldenCross;
import com.jhyarrow.myWeb.domain.support.SupportGoldenNeedle;

public interface SupportMapper {
	//添加爬虫错误记录
	public void addSpiderStockDailyError(SpiderStockDailyError ssde);
	//添加爬虫错误记录
	public void addSpiderStockDailyAllError(SpiderStockDailyAllError ssde);
	
	
	public void addSupport(Support support);
	public ArrayList<Support> getSupport();
	public ArrayList<Support> getSupportList(int tradeDay);
	public int updateSupport(Support support);
	public Line3 getLine3(Line3 line3);
	public void updateLine3(Line3 line3);
	public void addLine3(Line3 line3);
	
	//添加金针探底
	public void addSupportGoldenNeedle(SupportGoldenNeedle sgn);
	
	//获取tradeDay下金针探底
	public ArrayList<SupportGoldenNeedle> getSupportGoldenNeedle(int tradeDay);
	
	//获取所有金针探底
	public ArrayList<SupportGoldenNeedle> getSupportGoldenNeedleList();
	
	public void updateSupportGoldenNeedle(SupportGoldenNeedle sgn);
	
	//添加Macd金叉
	public void addMacdGoldenCross(MacdGoldenCross mgc);
	
	//获取所有Macd金叉
	public ArrayList<MacdGoldenCross> getMacdGoldenCrossList(String stockCode);
	
	//更新Macd金叉
	public void updateMacdGoldenCross(MacdGoldenCross mgc);
	
	
	
	//获取爬虫错误记录列表
	public ArrayList<SpiderStockDailyError> getSpiderStockDailyErrorList();
	
	//删除爬虫错误记录
	public void deleteSpiderStockDailyError(SpiderStockDailyError ssde);
}
