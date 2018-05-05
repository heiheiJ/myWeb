package com.jhyarrow.myWeb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.domain.StockIndex;
import com.jhyarrow.myWeb.mapper.StockIndexMapper;
import com.jhyarrow.myWeb.mapper.StockMapper;
import com.jhyarrow.myWeb.service.StockService;

public class StockServiceImpl implements StockService{
	@Autowired
	private StockMapper stockMapper;
	@Autowired
	private StockIndexMapper stockIndexMapper;
	
	public int insertStockList(List<Stock> stockList) {
		return stockMapper.insertStockList(stockList);
	}

	public List<Stock> getStockList() {
		return stockMapper.getStockList();
	}
	
	public List<StockIndex> getStockIndexList() {
		return stockIndexMapper.getStockIndexList();
	}

	public Stock getStockByCode(String code) {
		return stockMapper.getStockByCode(code);
	}

	public ArrayList<StockDaily> getStockDailyList(String stockCode){
		return stockMapper.getStockDailyList(stockCode);
	}

	public void updateStockDaily(StockDaily stockDaily) {
		stockMapper.updateStockDaily(stockDaily);
		
	}

	public void addStockDaily(StockDaily stockDaily) {
		stockMapper.addStockDaily(stockDaily);
	}
}
