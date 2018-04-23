package com.jhyarrow.myWeb.service;

public interface SupportService {
	public void getAvgStatusNew(String stockCode) throws Exception;
	public void getAvgStatusNewDay(String stockCode,int tradeDay)  throws Exception;
}
