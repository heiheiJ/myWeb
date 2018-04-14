package com.jhyarrow.myWeb.mapper;

import java.util.ArrayList;
import java.util.List;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockDaily;

public interface StockMapper {
	public int insertStockList(List<Stock> stockList);
	public List<Stock> getStockList();
	public Stock getStockByCode(String code);
	
	public void updateStockDaily1990(StockDaily stockDaily);
	public void updateStockDaily1991(StockDaily stockDaily);
	public void updateStockDaily1992(StockDaily stockDaily);
	public void updateStockDaily1993(StockDaily stockDaily);
	public void updateStockDaily1994(StockDaily stockDaily);
	public void updateStockDaily1995(StockDaily stockDaily);
	public void updateStockDaily1996(StockDaily stockDaily);
	public void updateStockDaily1997(StockDaily stockDaily);
	public void updateStockDaily1998(StockDaily stockDaily);
	public void updateStockDaily1999(StockDaily stockDaily);
	public void updateStockDaily2000(StockDaily stockDaily);
	public void updateStockDaily2001(StockDaily stockDaily);
	public void updateStockDaily2002(StockDaily stockDaily);
	public void updateStockDaily2003(StockDaily stockDaily);
	public void updateStockDaily2004(StockDaily stockDaily);
	public void updateStockDaily2005(StockDaily stockDaily);
	public void updateStockDaily2006(StockDaily stockDaily);
	public void updateStockDaily2007(StockDaily stockDaily);
	public void updateStockDaily2008(StockDaily stockDaily);
	public void updateStockDaily2009(StockDaily stockDaily);
	public void updateStockDaily2010(StockDaily stockDaily);
	public void updateStockDaily2011(StockDaily stockDaily);
	public void updateStockDaily2012(StockDaily stockDaily);
	public void updateStockDaily2013(StockDaily stockDaily);
	public void updateStockDaily2014(StockDaily stockDaily);
	public void updateStockDaily2015(StockDaily stockDaily);
	public void updateStockDaily2016(StockDaily stockDaily);
	public void updateStockDaily2017(StockDaily stockDaily);
	public void updateStockDaily2018(StockDaily stockDaily);
	
	public ArrayList<StockDaily> getStockDailyList1990(String stockCode);
	public ArrayList<StockDaily> getStockDailyList1991(String stockCode);
	public ArrayList<StockDaily> getStockDailyList1992(String stockCode);
	public ArrayList<StockDaily> getStockDailyList1993(String stockCode);
	public ArrayList<StockDaily> getStockDailyList1994(String stockCode);
	public ArrayList<StockDaily> getStockDailyList1995(String stockCode);
	public ArrayList<StockDaily> getStockDailyList1996(String stockCode);
	public ArrayList<StockDaily> getStockDailyList1997(String stockCode);
	public ArrayList<StockDaily> getStockDailyList1998(String stockCode);
	public ArrayList<StockDaily> getStockDailyList1999(String stockCode);
	public ArrayList<StockDaily> getStockDailyList2000(String stockCode);
	public ArrayList<StockDaily> getStockDailyList2001(String stockCode);
	public ArrayList<StockDaily> getStockDailyList2002(String stockCode);
	public ArrayList<StockDaily> getStockDailyList2003(String stockCode);
	public ArrayList<StockDaily> getStockDailyList2004(String stockCode);
	public ArrayList<StockDaily> getStockDailyList2005(String stockCode);
	public ArrayList<StockDaily> getStockDailyList2006(String stockCode);
	public ArrayList<StockDaily> getStockDailyList2007(String stockCode);
	public ArrayList<StockDaily> getStockDailyList2008(String stockCode);
	public ArrayList<StockDaily> getStockDailyList2009(String stockCode);
	public ArrayList<StockDaily> getStockDailyList2010(String stockCode);
	public ArrayList<StockDaily> getStockDailyList2011(String stockCode);
	public ArrayList<StockDaily> getStockDailyList2012(String stockCode);
	public ArrayList<StockDaily> getStockDailyList2013(String stockCode);
	public ArrayList<StockDaily> getStockDailyList2014(String stockCode);
	public ArrayList<StockDaily> getStockDailyList2015(String stockCode);
	public ArrayList<StockDaily> getStockDailyList2016(String stockCode);
	public ArrayList<StockDaily> getStockDailyList2017(String stockCode);
	public ArrayList<StockDaily> getStockDailyList2018(String stockCode);
}
