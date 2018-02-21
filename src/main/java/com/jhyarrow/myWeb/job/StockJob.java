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
		ArrayList<Stock> list = stockService.getStockList();
		for(int i=0;i<list.size();i++) {
			Stock stock = list.get(i);
			try {
				stockService.spiderStock(stock);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		long end = System.currentTimeMillis();
		logger.info("处理用时:"+String.valueOf(end-start));
		noahArk();
	}
	
	public void noahArk() {
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
			String date = df.format(System.currentTimeMillis());
			ArrayList<StockDaily> lists = stockService.getStockListByDay(date);
			for(StockDaily stock :lists) {
				supportService.goldNeedle(stock);
			}
			System.out.println("处理完成");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
