package com.jhyarrow.myWeb.job;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.domain.StockIndex;
import com.jhyarrow.myWeb.domain.StockIndexDaily;
import com.jhyarrow.myWeb.service.StockService;
import com.jhyarrow.myWeb.service.SupportService;
import com.jhyarrow.myWeb.thread.StockIndexSpideThread;
import com.jhyarrow.myWeb.thread.StockSpideThread;

public class StockJob{
	private static Logger logger = Logger.getLogger("StockJob");
	@Autowired
	private StockService stockService;
	@Autowired
	private SupportService supportService;
	
	public void spide(){
		long start = System.currentTimeMillis();
		logger.info("开始获取股票数据");
		ArrayList<Stock> list = (ArrayList<Stock>) stockService.getStockList();
		ExecutorService exec = Executors.newFixedThreadPool(10);
		ArrayList<Future<StockDaily>> results = new ArrayList<Future<StockDaily>>();
		int tradeDay = stockService.getMaxTradeDay();
		for(int i=0;i<list.size();i++) {
			Stock stock = list.get(i);
			try {
				logger.info("添加"+stock.getStockCode());
				results.add(exec.submit(new StockSpideThread(stock,tradeDay+1)));  
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			for(Future<StockDaily> fs :results) {
				stockService.addStockDaily(fs.get());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("处理完成，共处理"+results.size()+"条数据，用时:"+String.valueOf((end-start)/1000)+"秒");
		
		logger.info("开始获取指数数据");
		ArrayList<StockIndex> indexList = (ArrayList<StockIndex>) stockService.getStockIndexList();
		ArrayList<Future<StockIndexDaily>> indexResults = new ArrayList<Future<StockIndexDaily>>();
		for(int i=0;i<indexList.size();i++) {
			StockIndex stockIndex = indexList.get(i);
			try {
				indexResults.add(exec.submit(new StockIndexSpideThread(stockIndex,tradeDay+1)));  
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			for(Future<StockIndexDaily> fs :indexResults) {
				stockService.addStockIndexDaily(fs.get());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		long end2 = System.currentTimeMillis();
		logger.info("处理完成，共处理"+indexResults.size()+"条数据，用时:"+String.valueOf((end2-end)/1000)+"秒");
		//noahArk();
	}
	
	public void noahArk() {
		try {
			System.out.println("开始分析数据");
			long start = System.currentTimeMillis();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
			String date = df.format(System.currentTimeMillis());
			ArrayList<StockDaily> lists = stockService.getStockListByDay(date);
			for(StockDaily stock :lists) {
				supportService.getLine(stock);
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
			supportService.check(tradeDay-1);
			long end = System.currentTimeMillis();
			System.out.println("核查完成，用时:"+String.valueOf((end-start)/1000)+"秒");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
