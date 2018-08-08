package com.jhyarrow.myWeb.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.domain.StockIndexDaily;
import com.jhyarrow.myWeb.mapper.StockIndexMapper;
import com.jhyarrow.myWeb.mapper.StockMapper;
import com.jhyarrow.myWeb.service.StockService;

public class StockServiceImpl implements StockService{
	@Autowired
	private StockMapper stockMapper;
	@Autowired
	private StockIndexMapper stockIndexMapper;
	
	//清空指数表
	public void truncateStockIndexDaily() {
		stockIndexMapper.truncateStockIndexDaily();
	}
	//更新指数交易日数据
	public void updateStockIndexDailyTradeDay() {
		stockIndexMapper.updateStockIndexDailyTradeDay();
	}
	//更新股票交易日数据
	public void updateStockDailyTradeDay() {
		stockMapper.updateStockDailyTradeDay();
	}
	//获取指数数据列表
	public ArrayList<StockIndexDaily> getStockIndexDailyList(){
		return stockIndexMapper.getStockIndexDailyList();
	}
	//清空股票记录表
	public void truncateStockDaily() {
		stockMapper.truncateStockDaily();
	}
	//更新股票数据
	public void updateStockData() {
		ArrayList<Stock> stockList = stockMapper.getStockList();
		for(int i=0;i<stockList.size();i++) {
			Stock s = stockList.get(i);
			String stockCode = s.getStockCode();
			ArrayList<StockDaily> stockDailyList = stockMapper.getStockDailyList(stockCode);
			for(int j=0;j<stockDailyList.size();j++) {
				StockDaily sd = stockDailyList.get(j);
				if(j<stockDailyList.size()-1) {
					sd.setNextTradeDay(stockDailyList.get(j+1).getTradeDay());
				}
				if(j>0) {
					sd.setPrevTradeDay(stockDailyList.get(j-1).getTradeDay());
				}
				String upPer = sd.getUpPer();
				if(upPer == null) {
				}else {
					BigDecimal upPerBd =  new BigDecimal(upPer).multiply(new BigDecimal(100)).divide(new BigDecimal(1),0,BigDecimal.ROUND_HALF_UP);
					sd.setUpLevel(upPerBd.toString());
				}
				stockMapper.updateStockDaily(sd);
			}
			if(stockDailyList.size() == 0) {
				continue;
			}
			s.setLastTradeDay(stockDailyList.get(stockDailyList.size()-1).getTradeDay());
			stockMapper.updateStock(s);
		}
	}
	
	public int insertStockList(List<Stock> stockList) {
		return stockMapper.insertStockList(stockList);
	}

	public ArrayList<Stock> getStockList() {
		return stockMapper.getStockList();
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
