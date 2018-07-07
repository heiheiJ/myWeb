package com.jhyarrow.myWeb.mapper;

import java.util.ArrayList;

import com.jhyarrow.myWeb.domain.Line;
import com.jhyarrow.myWeb.domain.SpiderStockDailyAllError;
import com.jhyarrow.myWeb.domain.SpiderStockDailyError;
import com.jhyarrow.myWeb.domain.Support;
import com.jhyarrow.myWeb.domain.support.SupportGoldenNeedle;
import com.jhyarrow.myWeb.domain.support.SupportKdj;
import com.jhyarrow.myWeb.domain.support.SupportMacd;

public interface SupportMapper {
	//添加爬虫错误记录
	public void addSpiderStockDailyError(SpiderStockDailyError ssde);
	//添加爬虫错误记录
	public void addSpiderStockDailyAllError(SpiderStockDailyAllError ssde);
	//获取爬虫错误记录列表
	public ArrayList<SpiderStockDailyAllError> getSpiderStockDailyAllErrorList();
	//删除爬虫错误记录
	public void deleteSpiderStockDailyAllError(SpiderStockDailyAllError ssde);
	//添加macd样本
	public void addSupportMacd(SupportMacd sm);
	//添加macd样本
	public void addSupportKdj(SupportKdj sk);
	//添加趋势线
	public void addLine(Line line);
	
		
	public void addSupport(Support support);
	public ArrayList<Support> getSupport();
	public ArrayList<Support> getSupportList(int tradeDay);
	public int updateSupport(Support support);
	
	//添加金针探底
	public void addSupportGoldenNeedle(SupportGoldenNeedle sgn);
	
	//获取tradeDay下金针探底
	public ArrayList<SupportGoldenNeedle> getSupportGoldenNeedle(int tradeDay);
	
	//获取所有金针探底
	public ArrayList<SupportGoldenNeedle> getSupportGoldenNeedleList();
	
	public void updateSupportGoldenNeedle(SupportGoldenNeedle sgn);
	
	
	//获取爬虫错误记录列表
	public ArrayList<SpiderStockDailyError> getSpiderStockDailyErrorList();
	
	//删除爬虫错误记录
	public void deleteSpiderStockDailyError(SpiderStockDailyError ssde);
}
