package com.jhyarrow.myWeb.service.impl;

import java.lang.reflect.Method;
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

	public void updateStockDaily(StockDaily stockDaily) {
		String date = stockDaily.getDate();
		String year = date.substring(0, 4);
		String methodName = "updateStockDaily";
		Class stockMapperClass = stockMapper.getClass();
		try {
			Method method = stockMapperClass.getMethod(methodName+year, StockDaily.class);
			method.invoke(stockMapper, stockDaily);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<StockDaily> getStockDailyList(String year,String stockCode){
		ArrayList<StockDaily> list = null;
		String methodName = "getStockDailyList";
		Class stockMapperClass = stockMapper.getClass();
		try {
			Method method = stockMapperClass.getMethod(methodName+year, String.class);
			list =  (ArrayList<StockDaily>) method.invoke(stockMapper, stockCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
