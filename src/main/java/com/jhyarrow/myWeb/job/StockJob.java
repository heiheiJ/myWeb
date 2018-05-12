package com.jhyarrow.myWeb.job;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.jhyarrow.myWeb.service.SpiderService;
import com.jhyarrow.myWeb.service.StockService;
import com.jhyarrow.myWeb.service.SupportService;
import com.jhyarrow.myWeb.service.TradeDayService;

public class StockJob{
	private static Logger logger = Logger.getLogger("StockJob");
	@Autowired
	private SupportService supportService;
	@Autowired
	private SpiderService spiderService;
	@Autowired
	private TradeDayService tradeDayService;
	
	public void spide(){
		try {
			String date = "2018-05-02";
			Integer tradeDay = tradeDayService.getTradeDayByDate(date);
			if(tradeDay == null) {
				throw new Exception(date+"不是交易日！");
			}
			//step1 获取当天股票数据
			spiderService.spideStockDaily(date);
			
			//step2 获取当天指数数据
			spiderService.spideStockIndexDaily(date);
			
			//step3计算macd
			supportService.getMACD(tradeDay);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
