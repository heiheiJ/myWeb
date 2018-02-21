package com.jhyarrow.myWeb.service;

import java.util.ArrayList;
import java.util.List;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockDaily;

public interface StockService {
	public ArrayList<StockDaily> getStockListByDay(String date);
	public int insertStockList(List<Stock> stockList);
	public ArrayList<Stock> getStockList();
	public void spiderStock(Stock stock);
}
