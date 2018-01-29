package com.jhyarrow.myWeb.job;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.jhyarrow.myWeb.service.JobService;

public class StockJob{
	private static Logger logger = Logger.getLogger("StockJob");
	@Autowired
	private JobService jobService;
	
	public void spide() {
		String bashPath="F:/heihei.bat";
		logger.info("调用爬虫");
		jobService.doSpider(bashPath);
		logger.info("调用成功");
	}
	
}
