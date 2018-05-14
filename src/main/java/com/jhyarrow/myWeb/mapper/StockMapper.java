package com.jhyarrow.myWeb.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockDaily;

public interface StockMapper {
	public int insertStockList(List<Stock> stockList);
	public List<Stock> getStockList();
	public Stock getStockByCode(String code);
	public void updateStockDaily(StockDaily stockDaily);
	public void addStockDaily(StockDaily stockDaily);
	public StockDaily getStockDaily(StockDaily stockDaily);
	
	//获取stockCode记录，按trade_day增序
	public ArrayList<StockDaily> getStockDailyList(String stockCode);
	
	//获取近N天记录，按date降序
	public ArrayList<StockDaily> getStockDailyListN(String stockCode,int n);
	
	//获取tradeDay前一天的股票数据
	public StockDaily getStockDailyYesterDay(@Param("stockCode") String stockCode);
	
	//获取上证股票
	public List<Stock> getStockListSh();
	
	//获取深证股票
	public List<Stock> getStockListSz();
		
	//获取创业板股票
	public List<Stock> getStockListCy();
}
