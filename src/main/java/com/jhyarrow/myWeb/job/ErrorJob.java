package com.jhyarrow.myWeb.job;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.jhyarrow.myWeb.domain.SpiderStockDailyError;
import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.service.SpiderService;
import com.jhyarrow.myWeb.service.StockService;
import com.jhyarrow.myWeb.service.SupportService;

public class ErrorJob {
	@Autowired
	private SupportService supportService;
	@Autowired
	private SpiderService spiderService;
	@Autowired
	private StockService stockService;
	
	public void clearStockDailyError(){
		try {
//			ArrayList<SpiderStockDailyError> list = supportService.getSpiderStockDailyErrorList();
//			for(int i=0;i<list.size();i++) {
//				SpiderStockDailyError ssde = list.get(i);
//				Stock stock = stockService.getStockByCode(ssde.getStockCode());
//				spiderService.spideStockDaily(ssde.getStockCode(), ssde.getStockName(),ssde.getTradeDay(),stock.getLastTradeDay(),ssde.getDate().substring(0, 10).replaceAll("-", ""));
//				supportService.getMACD(ssde.getStockCode(),ssde.getTradeDay());
//				supportService.deleteSpiderStockDailyError(ssde);
//			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
