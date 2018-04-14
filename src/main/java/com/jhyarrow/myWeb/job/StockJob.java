package com.jhyarrow.myWeb.job;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.jhyarrow.myWeb.service.StockService;
import com.jhyarrow.myWeb.service.SupportService;

public class StockJob{
	private static Logger logger = Logger.getLogger("StockJob");
	@Autowired
	private StockService stockService;
	@Autowired
	private SupportService supportService;
	
	public void spide(){
	}
	
}
