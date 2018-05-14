package com.jhyarrow.myWeb.service;

import java.util.ArrayList;

import com.jhyarrow.myWeb.domain.TradeDay;

public interface TradeDayService {
	public ArrayList<TradeDay> getTradeDayList();
	public Integer getTradeDayByDate(String date);
}
