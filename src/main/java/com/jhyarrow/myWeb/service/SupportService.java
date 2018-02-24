package com.jhyarrow.myWeb.service;

import java.util.ArrayList;

import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.domain.Support;

public interface SupportService {
	public void goldNeedle(StockDaily stock);
	public ArrayList<Support> getSupport();
	public void CheckGoldNeedle(int tradeDay);
}
