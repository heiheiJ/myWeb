package com.jhyarrow.myWeb.service;

import java.util.ArrayList;

import com.jhyarrow.myWeb.domain.Stock;

public interface StockService {
	public ArrayList<Stock> getStockListByDay(String date);
}
