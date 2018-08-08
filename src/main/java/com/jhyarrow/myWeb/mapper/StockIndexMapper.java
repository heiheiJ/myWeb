package com.jhyarrow.myWeb.mapper;

import java.util.ArrayList;

import com.jhyarrow.myWeb.domain.StockIndex;
import com.jhyarrow.myWeb.domain.StockIndexDaily;

public interface StockIndexMapper {
	//清空指数数据
	public void truncateStockIndexDaily();
	//添加指数数据
	public int addStockIndexDaily(StockIndexDaily stock);
	//更新指数交易日数据
	public void updateStockIndexDailyTradeDay();
	//获取stockCode的所有指数列表
	public ArrayList<StockIndexDaily> getStockIndexDailyByCode(String stockCode);
	//获取指数数据列表
	public ArrayList<StockIndexDaily> getStockIndexDailyList();
	//获取指数列表
	public ArrayList<StockIndex> getStockIndexList();
	
	
}
