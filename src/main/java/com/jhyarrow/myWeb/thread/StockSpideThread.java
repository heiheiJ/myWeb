package com.jhyarrow.myWeb.thread;

import java.util.concurrent.CountDownLatch;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.service.SpiderService;

public class StockSpideThread implements Runnable{
	private SpiderService spiderService;
	private Stock stock;
	private CountDownLatch countDownLatch;
	private String date;
	
	public StockSpideThread(SpiderService spiderService,Stock stock,String date,CountDownLatch countDownLatch) {
		this.stock = stock;
		this.spiderService = spiderService;
		this.countDownLatch = countDownLatch;
		this.date = date;
	}
	
	public void run() {
		try {
			spiderService.spideStockDaily(stock.getStockCode(), stock.getStockName(), "2018-07-30", date);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			countDownLatch.countDown();
		}
	}
	
		
}
