package com.jhyarrow.myWeb.thread;

import java.util.concurrent.locks.ReentrantLock;

import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.service.StockService;

public class CaculatorThread implements Runnable{
	public static StockService stockService;
	public static ReentrantLock lock;
	private StockDaily stockDaily;
	
	public CaculatorThread(StockDaily stockDaily) {
		this.stockDaily = stockDaily;
	}
	
	public void run() {
		stockService.updateStockDaily(stockDaily);
	}
}
