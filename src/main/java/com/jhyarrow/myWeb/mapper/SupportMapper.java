package com.jhyarrow.myWeb.mapper;

import java.util.ArrayList;

import com.jhyarrow.myWeb.domain.Line3;
import com.jhyarrow.myWeb.domain.Support;
import com.jhyarrow.myWeb.domain.support.SupportGoldenNeedle;

public interface SupportMapper {
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
}
