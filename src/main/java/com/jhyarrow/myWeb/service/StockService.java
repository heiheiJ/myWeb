package com.jhyarrow.myWeb.service;

import java.util.ArrayList;
import java.util.List;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.domain.StockIndexDaily;

public interface StockService {
	//清空指数数据
	public void truncateStockIndexDaily();
	//更新指数交易日数据
	public void updateStockIndexDailyTradeDay();
	//更新股票交易日数据
	public void updateStockDailyTradeDay();
	//获取指数数据列表
	public ArrayList<StockIndexDaily> getStockIndexDailyList();
	//获取股票列表
	public ArrayList<Stock> getStockList();
	//清空指数数据
	public void truncateStockDaily();
	//更新股票数据
	public void updateStockData();
	
	public int insertStockList(List<Stock> stockList);
	public Stock getStockByCode(String code);
	public void updateStockDaily(StockDaily stockDaily);
	public ArrayList<StockDaily> getStockDailyList(String stockCode);
	public void addStockDaily(StockDaily stockDaily);
}
