package com.jhyarrow.myWeb.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.service.SpiderService;

public class StockSpideCallable implements Callable<Boolean>{
	private SpiderService spiderService;
	private Stock stock;
	private String date;
	
	public StockSpideCallable(SpiderService spiderService,Stock stock,String date) {
		this.stock = stock;
		this.spiderService = spiderService;
		this.date = date;
	}
	
	public Boolean call() {
		try {
			spiderService.spideStockDaily(stock.getStockCode(), stock.getStockName(), "2018-07-30", date);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
