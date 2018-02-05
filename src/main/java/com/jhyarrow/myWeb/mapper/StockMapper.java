package com.jhyarrow.myWeb.mapper;

import java.util.ArrayList;

import com.jhyarrow.myWeb.domain.Stock;

public interface StockMapper {
	public void addStock(Stock stock);
	public ArrayList<Stock> getStockListByDay(String date);
}