package com.jhyarrow.myWeb.service;

import java.util.ArrayList;

import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.domain.Support;

public interface SupportService {
	public ArrayList<Support> getSupport();
	public void check(int tradeDay);
	public void getLine(StockDaily stock);
}
