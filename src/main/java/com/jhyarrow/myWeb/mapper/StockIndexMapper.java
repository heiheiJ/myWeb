package com.jhyarrow.myWeb.mapper;

import java.util.ArrayList;
import java.util.List;

import com.jhyarrow.myWeb.domain.StockIndex;
import com.jhyarrow.myWeb.domain.StockIndexDaily;

public interface StockIndexMapper {
	public List<StockIndex> getStockIndexList();
	
	//添加指数数据
	public int addStockIndexDaily(StockIndexDaily stock);
	public ArrayList<StockIndexDaily> getStockIndexDailyByCode(String stockCode);
}
