package com.jhyarrow.myWeb.mapper;

import java.util.ArrayList;
import java.util.List;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockDaily;

public interface StockMapper {
	public int addStock(StockDaily stock);
	public ArrayList<StockDaily> getStockListByDay(String date);
	public int insertStockList(List<Stock> stockList);
	public ArrayList<Stock> getStockList();
}
