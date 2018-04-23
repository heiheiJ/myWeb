package com.jhyarrow.myWeb.mapper;

import java.util.ArrayList;
import java.util.List;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockDaily;

public interface StockMapper {
	public int insertStockList(List<Stock> stockList);
	public List<Stock> getStockList();
	public Stock getStockByCode(String code);
	public void updateStockDaily(StockDaily stockDaily);
	public ArrayList<StockDaily> getStockDailyList(String stockCode);
	public void addStockDaily(StockDaily stockDaily);
	public StockDaily getStockDaily(StockDaily stockDaily);
}
