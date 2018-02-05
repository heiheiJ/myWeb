package com.jhyarrow.myWeb.service;

import java.util.ArrayList;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.Support;

public interface SupportService {
	public void goldNeedle(Stock stock);
	public ArrayList<Support> getSupport(String date);
}
