package com.jhyarrow.myWeb.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.domain.Support;
import com.jhyarrow.myWeb.mapper.StockMapper;
import com.jhyarrow.myWeb.mapper.SupportMapper;
import com.jhyarrow.myWeb.service.SupportService;

public class SupportServiceImpl implements SupportService{
	private static Logger logger = Logger.getLogger("SupportServiceImpl");
	@Autowired
	private SupportMapper supportMapper;
	@Autowired
	private StockMapper stockMapper;
	
	public void goldNeedle(StockDaily stock) {
		BigDecimal openToday = new BigDecimal(stock.getOpenToday());
		BigDecimal lowest = new BigDecimal(stock.getLowest());
		if(lowest.compareTo(openToday.multiply(new BigDecimal(0.95))) < 0 
				&& new BigDecimal(stock.getUp()).abs().compareTo(new BigDecimal(0.01)) < 0){
			Support support = new Support();
			support.setCode(stock.getCode());
			support.setName(stock.getName());
			support.setDate(stock.getDate());
			support.setTradeDay(stock.getTradeDay());
			support.setReason("金针探底");
			supportMapper.addSupport(support);
			logger.info("添加股票推荐"+stock.getName()+"("+stock.getCode()+")");
		}
	}

	public ArrayList<Support> getSupport(String date) {
		return supportMapper.getSupport(date);
	}

	public void CheckGoldNeedle(int tradeDay) {
		ArrayList<Support> list = supportMapper.getSupportGoldNeedleList(tradeDay);
		for(int i=0;i<list.size();i++) {
			Support support = list.get(i);
			String stockCode = support.getCode();
			StockDaily stockDaily = new StockDaily();
			stockDaily.setCode(stockCode);
			stockDaily.setTradeDay(tradeDay+1);
			stockDaily = stockMapper.getStockDaily(stockDaily);
			String up = stockDaily.getUp();
			String upPer = stockDaily.getUpPer();
			if(up != null) {
				support.setUp(Double.parseDouble(upPer));
				if(Double.parseDouble(up) > 0) {
					support.setIsTrue("Y");
				}else {
					support.setIsTrue("N");
				}
				supportMapper.updateSupport(support);
			}
		}
	}

}
