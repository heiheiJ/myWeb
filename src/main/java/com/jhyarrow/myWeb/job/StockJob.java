package com.jhyarrow.myWeb.job;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import com.jhyarrow.myWeb.service.JobService;

public class StockJob{
	private static Logger logger = Logger.getLogger("StockJob");
	@Autowired
	private JobService jobService;
	
	public void spide(){
		Properties prop = new Properties();
		try {
			ClassPathResource classPathResource = new ClassPathResource("shell.properties");
			InputStream in =new BufferedInputStream(classPathResource.getInputStream()); 
			prop.load(in);
			String bashPath= prop.getProperty("bashPath");
			String jsonPath = prop.getProperty("jsonPath");
			logger.info("调用爬虫");
			jobService.doSpider(bashPath);
			logger.info("调用成功");
			logger.info("插入数据");
			jobService.handleData(jsonPath);
			logger.info("插入成功");
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
}
