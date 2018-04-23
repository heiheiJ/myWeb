package com.jhyarrow.myWeb.service;

import java.util.ArrayList;
import java.util.List;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockDaily;

public interface StockService {
	public int insertStockList(List<Stock> stockList);
	public List<Stock> getStockList();
	public Stock getStockByCode(String code);
	public void updateStockDailyNew(StockDaily stockDaily);
	public ArrayList<StockDaily> getStockDailyListNew(String stockCode);
	public void addStockDaily(StockDaily stockDaily);
}
