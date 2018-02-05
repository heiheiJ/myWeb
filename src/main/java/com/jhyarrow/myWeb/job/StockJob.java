package com.jhyarrow.myWeb.job;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.service.JobService;
import com.jhyarrow.myWeb.service.StockService;
import com.jhyarrow.myWeb.service.SupportService;

public class StockJob{
	private static Logger logger = Logger.getLogger("StockJob");
	@Autowired
	private JobService jobService;
	@Autowired
	private StockService stockService;
	@Autowired
	private SupportService supportService;
	
	public void spide(){
		Properties prop = new Properties();
		try {
			ClassPathResource classPathResource = new ClassPathResource("shell.properties");
			InputStream in =new BufferedInputStream(classPathResource.getInputStream()); 
			prop.load(in);
			String bashPath= prop.getProperty("bashPath");
			String jsonPath = prop.getProperty("jsonPath");
			logger.info("调用爬虫start");
			//jobService.doSpider(bashPath);
			logger.info("调用爬虫end");
			logger.info("插入数据start");
			jobService.handleData(jsonPath);
			logger.info("插入数据end");
			logger.info("诺亚方舟start");
			noahArk();
			logger.info("诺亚方舟end");
			logger.info("复核数据start");
			logger.info("复核数据end");
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void noahArk() {
		try {
			logger.info("开始处理");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
			String date = df.format(System.currentTimeMillis());
			ArrayList<Stock> lists = stockService.getStockListByDay(date);
			for(Stock stock :lists) {
				supportService.goldNeedle(stock);
			}
			logger.info("处理完成");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
