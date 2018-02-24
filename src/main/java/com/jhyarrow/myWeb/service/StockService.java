package com.jhyarrow.myWeb.service;

import java.util.ArrayList;
import java.util.List;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.domain.StockIndex;
import com.jhyarrow.myWeb.domain.StockIndexDaily;

public interface StockService {
	public ArrayList<StockDaily> getStockListByDay(String date);
	public int insertStockList(List<Stock> stockList);
	public List<Stock> getStockList();
	public void spiderStock(Stock stock,int tradeDay);
	public int getMaxTradeDay();
	public int addStockDailyList(List<StockDaily> list);
	public int addStockDaily(StockDaily stockDaily);
	public List<StockIndex> getStockIndexList();
	public int addStockIndexDaily(StockIndexDaily stockIndexDaily);
}
