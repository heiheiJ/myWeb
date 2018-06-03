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
	
	public void addStockDaily(StockDaily stockDaily);
	public StockDaily getStockDaily(@Param("stockCode") String stockCode,@Param("tradeDay") int tradeDay);
	
	//更新股票日常数据
	public void updateStockDaily(StockDaily stockDaily);
	
	//获取stockCode记录，按trade_day增序
	public ArrayList<StockDaily> getStockDailyList(String stockCode);
	
	//获取近N天记录，按date降序
	public ArrayList<StockDaily> getStockDailyListN(@Param("stockCode") String stockCode,@Param("n") int n,@Param("tradeDay") Integer trade_day);
	
	//获取上证股票
	public List<Stock> getStockListSh();
	
	//获取深证股票
	public List<Stock> getStockListSz();
		
	//获取创业板股票
	public List<Stock> getStockListCy();
	
	//获取其他
	public List<Stock> getStockListOther();
	
	//获取当天记录数
	public int getStockDailyCount(String date);
	
	//更新股票最新交易日
	public void updateStock(Stock stock);
	
	//更新MACD数值
	public void updateStockDailyMACD(StockDaily stockDaily);
	
	//更新kdj数值
	public void updateStockDailyKdj(StockDaily stockDaily);
}
