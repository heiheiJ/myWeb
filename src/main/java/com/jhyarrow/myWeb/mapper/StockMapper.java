package com.jhyarrow.myWeb.mapper;

import java.util.ArrayList;
import java.util.List;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.domain.StockIndexDaily;

public interface StockMapper {
	public int addStockDaily(StockDaily stock);
	public ArrayList<StockDaily> getStockListByDay(String date);
	public int insertStockList(List<Stock> stockList);
	public List<Stock> getStockList();
	public int getMaxTradeDay();
	public StockDaily getStockDaily(StockDaily stockDaily);
	public int addStockDailyList(List<StockDaily> list);
	public int addStockIndexDaily(StockIndexDaily stockIndex);
	public String getDateByTradeDay(int tradeDay);
}
