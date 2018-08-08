package com.jhyarrow.myWeb.job;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.jhyarrow.myWeb.domain.SpiderStockDailyAllError;
import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockIndexDaily;
import com.jhyarrow.myWeb.service.SpiderService;
import com.jhyarrow.myWeb.service.StockService;
import com.jhyarrow.myWeb.service.SupportService;
import com.jhyarrow.myWeb.service.TradeDayService;
import com.jhyarrow.myWeb.thread.StockSpideThread;
import com.jhyarrow.myWeb.util.MailUtil;

public class StockJobWeek {
	private static Logger logger = Logger.getLogger("StockJobWeek");
	@Autowired
	private SpiderService spiderService;
	@Autowired
	private StockService stockService;
	@Autowired
	private TradeDayService tradeDayService;
	@Autowired
	private SupportService supportService;
	
	public void spideWeek(){
		long start ,end;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		StringBuffer sb = new StringBuffer();
		ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		try {
			logger.info(date+"每周跑批开始");
			sb.append(date+"每周跑批开始<br>");
			
			//step1 获取所有股票
			start = System.currentTimeMillis();
			spiderService.spideStock();
			end = System.currentTimeMillis();
			logger.info(date+"股票下载完成，用时"+(end-start)/1000+"秒");
			sb.append(date+"股票下载完成，用时"+(end-start)/1000+"秒<br>");
			
			//step2获取所有指数数据
			start = System.currentTimeMillis();
			stockService.truncateStockIndexDaily();
			spiderService.spideStockIndexDaily("1990-01-01",date);
			end = System.currentTimeMillis();
			logger.info(date+"指数数据下载完成，用时"+(end-start)/1000+"秒");
			sb.append(date+"指数数据下载完成，用时"+(end-start)/1000+"秒<br>");
			
			//step3更新trade_day表
			start = System.currentTimeMillis();
			tradeDayService.truncateTradeDay();
			tradeDayService.addTradeDayList();
			end = System.currentTimeMillis();
			logger.info(date+"trade_day表更新完成，用时"+(end-start)/1000+"秒");
			sb.append(date+"trade_day表更新完成，用时"+(end-start)/1000+"秒<br>");
			
			//step4更新指数表trade_day
			start = System.currentTimeMillis();
			stockService.updateStockIndexDailyTradeDay();
			end = System.currentTimeMillis();
			logger.info(date+"更新指数数据完成，用时"+(end-start)/1000+"秒");
			sb.append(date+"更新指数数据完成，用时"+(end-start)/1000+"秒<br>");
			
			//step5获取所有股票数据
			start = System.currentTimeMillis();
			stockService.truncateStockDaily();
			ArrayList<Stock> stockList = stockService.getStockList();
			CountDownLatch countDownLatch  =  new  CountDownLatch (stockList.size());
			for(int i=0;i<stockList.size();i++) {
				Stock stock = stockList.get(i);
				StockSpideThread sst = new StockSpideThread(spiderService, stock,date,countDownLatch);
				executor.execute(sst);
			}
			try {
				countDownLatch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			ArrayList<SpiderStockDailyAllError> spiderStockDailyAllErrorList = supportService.getSpiderStockDailyAllErrorList();
			while(spiderStockDailyAllErrorList.size()!=0) {
				for(int i=0;i<spiderStockDailyAllErrorList.size();i++) {
					SpiderStockDailyAllError ssdae = spiderStockDailyAllErrorList.get(i);
					supportService.deleteSpiderStockDailyAllError(ssdae);
					spiderService.spideStockDaily(ssdae.getStockCode(), ssdae.getStockName(), "1990-01-01", date);
				}
				spiderStockDailyAllErrorList = supportService.getSpiderStockDailyAllErrorList();
			}
			stockService.updateStockDailyTradeDay();
			end = System.currentTimeMillis();
			logger.info(date+"获取所有股票数据完成，用时"+(end-start)/1000+"秒");
			sb.append(date+"获取所有股票数据完成，用时"+(end-start)/1000+"秒<br>");
			
			//step6更新数据
			start = System.currentTimeMillis();
			stockService.updateStockData();
			end = System.currentTimeMillis();
			logger.info(date+"更新数据，用时"+(end-start)/1000+"秒");
			sb.append(date+"更新数据，用时"+(end-start)/1000+"秒<br>");
			
//			//step7计算macd
//			start = System.currentTimeMillis();
//			supportService.getMACD();
//			end = System.currentTimeMillis();
//			logger.info(date+"计算MACD完成，用时"+(end-start)/1000+"秒");
//			sb.append(date+"计算MACD完成，用时"+(end-start)/1000+"秒<br>");
//			
//			//step8计算kdj
//			start = System.currentTimeMillis();
//			supportService.get9();
//			supportService.getKDJ();
//			end = System.currentTimeMillis();
//			logger.info(date+"计算kdj完成，用时"+(end-start)/1000+"秒");
//			sb.append(date+"计算kdj完成，用时"+(end-start)/1000+"秒<br>");
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.info(date+"，周跑批失败");
			sb.append(date+"，周跑批失败<br>");
		}finally {
			try {
				String to = "632849309@qq.com";
				String subject = date+"结果";
				MailUtil.sendMail(to, subject, sb.toString());
			}catch (Exception e) {
				e.printStackTrace();
			}
			executor.shutdown();
		}
	}
}
