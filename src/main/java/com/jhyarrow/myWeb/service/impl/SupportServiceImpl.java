package com.jhyarrow.myWeb.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.Support;
import com.jhyarrow.myWeb.mapper.SupportMapper;
import com.jhyarrow.myWeb.service.SupportService;

public class SupportServiceImpl implements SupportService{
	private static Logger logger = Logger.getLogger("SupportServiceImpl");
	@Autowired
	private SupportMapper supportMapper;
	
	public void goldNeedle(Stock stock) {
		BigDecimal openToday = new BigDecimal(stock.getOpen_today());
		BigDecimal lowest = new BigDecimal(stock.getLowest());
		if(lowest.compareTo(openToday.multiply(new BigDecimal(0.95))) < 0 
				&& new BigDecimal(stock.getUp()).abs().compareTo(new BigDecimal(0.01)) < 0){
			Support support = new Support();
			support.setCode(stock.getCode());
			support.setName(stock.getName());
			support.setDate(stock.getDate());
			support.setReason("金针探底");
			supportMapper.addSupport(support);
			logger.info("添加股票推荐"+stock.getName()+"("+stock.getCode()+")");
		}
	}

	public ArrayList<Support> getSupport(String date) {
		return supportMapper.getSupport(date);
	}

}
