package com.jhyarrow.myWeb.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.jhyarrow.myWeb.domain.DualThrust;
import com.jhyarrow.myWeb.domain.HgtStockDaily;
import com.jhyarrow.myWeb.domain.Line;
import com.jhyarrow.myWeb.domain.Line5;
import com.jhyarrow.myWeb.domain.Line5Conclusion;
import com.jhyarrow.myWeb.domain.SpiderStockDailyAllError;
import com.jhyarrow.myWeb.domain.SpiderStockDailyError;
import com.jhyarrow.myWeb.domain.Support;
import com.jhyarrow.myWeb.domain.Support5;
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
	//添加五日趋势线
	public void addLine5(Line5 line5);
	//获取5日结论列表
	public ArrayList<Line5Conclusion> getLine5ConclusionList();
	//获取5日趋势
	public ArrayList<Line5> getLine5(@Param("day1") Integer day1,@Param("day2") Integer day2
			,@Param("day3") Integer day3,@Param("day4") Integer day4,@Param("day5") Integer day5);
	//更新5日结论
	public void updateLine5Conclusion(Line5Conclusion line5Conclusion);
	//获取5日结论
	public Line5Conclusion getLine5Conclusion(Line5Conclusion line5Conclusion);
	//添加5日推荐
	public void addSupport5(Support5 support5);
	//添加DualThrust记录
	public void addDualThrust(DualThrust dualThrust);
	//添加沪股通记录
	public void addHgtStockDaily(HgtStockDaily hgtStockDaily);
	//获取沪股通列表
	public ArrayList<HgtStockDaily> getHgtStockDailyList();
	//更新沪股通数据
	public void updateHgtStockDaily (HgtStockDaily hgtStockDaily);
	
	
	
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
