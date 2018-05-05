package com.jhyarrow.myWeb.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.jhyarrow.myWeb.domain.TradeDay;
import com.jhyarrow.myWeb.mapper.TradeDayMapper;
import com.jhyarrow.myWeb.service.TradeDayService;

public class TradeDayServiceImpl implements TradeDayService{
	@Autowired
	private TradeDayMapper tradeDayMapper;

	public ArrayList<TradeDay> getTradeDayList() {
		return tradeDayMapper.getTradeDayList();
	}

	public int getTradeDayByDate(String date) {
		return tradeDayMapper.getTradeDayByDate(date);
	}
	
}
