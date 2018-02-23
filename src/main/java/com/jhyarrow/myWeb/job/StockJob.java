package com.jhyarrow.myWeb.job;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.service.StockService;
import com.jhyarrow.myWeb.service.SupportService;

public class StockJob{
	private static Logger logger = Logger.getLogger("StockJob");
	@Autowired
	private StockService stockService;
	@Autowired
	private SupportService supportService;
	
	public void spide(){
		long start = System.currentTimeMillis();
		System.out.println("开始获取数据");
		ArrayList<Stock> list = stockService.getStockList();
		int tradeDay = stockService.getMaxTradeDay();
		for(int i=0;i<list.size();i++) {
			Stock stock = list.get(i);
			try {
				stockService.spiderStock(stock,tradeDay+1);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		long end = System.currentTimeMillis();
		logger.info("处理完成，用时:"+String.valueOf((end-start)/1000)+"秒");
		noahArk();
	}
	
	public void noahArk() {
		try {
			System.out.println("开始分析数据");
			long start = System.currentTimeMillis();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
			String date = df.format(System.currentTimeMillis());
			ArrayList<StockDaily> lists = stockService.getStockListByDay(date);
			for(StockDaily stock :lists) {
				supportService.goldNeedle(stock);
			}
			long end = System.currentTimeMillis();
			System.out.println("分析完成，用时:"+String.valueOf((end-start)/1000)+"秒");
			checkData();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void checkData() {
		try {
			System.out.println("开始核查数据");
			long start = System.currentTimeMillis();
			int tradeDay = stockService.getMaxTradeDay();
			supportService.CheckGoldNeedle(tradeDay-1);
			long end = System.currentTimeMillis();
			System.out.println("核查完成，用时:"+String.valueOf((end-start)/1000)+"秒");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
