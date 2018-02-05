package com.jhyarrow.myWeb.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.mapper.StockMapper;
import com.jhyarrow.myWeb.service.StockService;

public class StockServiceImpl implements StockService{
	@Autowired
	private StockMapper stockMapper;
	
	public ArrayList<Stock> getStockListByDay(String date) {
		return stockMapper.getStockListByDay(date);
	}

}
