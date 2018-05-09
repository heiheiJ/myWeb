package com.jhyarrow.myWeb.thread;

import java.util.concurrent.CountDownLatch;

import com.jhyarrow.myWeb.service.SupportService;

public class MacdThread extends Thread{
	private CountDownLatch countDownLatch; 
	private String stockCode;
	public static SupportService supportService;
	
	public MacdThread(String stockCode,CountDownLatch countDownLatch) {
		this.stockCode = stockCode;
		this.countDownLatch = countDownLatch;
	}
	
	public void run() {
		try {
			long start2 = System.currentTimeMillis();
			supportService.getMACD(stockCode);
			long end2 = System.currentTimeMillis();
			System.out.println(stockCode+"共耗时"+ (end2-start2)/1000+"秒");
			countDownLatch.countDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
