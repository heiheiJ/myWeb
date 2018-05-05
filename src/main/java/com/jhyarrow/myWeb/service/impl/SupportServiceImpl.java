package com.jhyarrow.myWeb.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.mapper.StockMapper;
import com.jhyarrow.myWeb.service.StockService;
import com.jhyarrow.myWeb.service.SupportService;

public class SupportServiceImpl implements SupportService{
	@Autowired
	private StockService stockService;
	@Autowired
	private StockMapper stockMapper;
	
	public void getAvgStatusNew(String stockCode) throws Exception {
		BigDecimal ema12lastDay = new BigDecimal(0);
		BigDecimal ema26lastDay = new BigDecimal(0);
		BigDecimal deaLastDay = new BigDecimal(0);
		int cnt = 0;
			ArrayList<StockDaily> list = stockService.getStockDailyList(stockCode);
			for(int j=0;j<list.size();j++) {
				StockDaily sd = list.get(j);
				String closeToday = sd.getCloseToday();
				if(closeToday == null || closeToday.length() == 0) {
					closeToday = "0";
				}
				
				if(cnt == 0) {
					sd.setEma12(new BigDecimal(closeToday).toString());
					sd.setEma26(new BigDecimal(closeToday).toString());
					sd.setDiff("0");
					sd.setDea("0");
					sd.setBar("0");
					ema12lastDay = new BigDecimal(closeToday);
					ema26lastDay = new BigDecimal(closeToday);
				}else {
					BigDecimal ema12 = (new BigDecimal(closeToday).multiply(new BigDecimal(2)).divide(new BigDecimal(13),4,BigDecimal.ROUND_HALF_UP))
							.add(ema12lastDay.multiply(new BigDecimal(11)).divide(new BigDecimal(13),4,BigDecimal.ROUND_HALF_UP));
					ema12lastDay = ema12.add(new BigDecimal(0));
					BigDecimal ema26 = (new BigDecimal(closeToday).multiply(new BigDecimal(2)).divide(new BigDecimal(27),4,BigDecimal.ROUND_HALF_UP))
							.add(ema26lastDay.multiply(new BigDecimal(25)).divide(new BigDecimal(27),4,BigDecimal.ROUND_HALF_UP));
					ema26lastDay = ema26.add(new BigDecimal(0));
					BigDecimal diff = ema12.subtract(ema26);
					BigDecimal dea = (deaLastDay.multiply(new BigDecimal(0.8))).add(diff.multiply(new BigDecimal(0.2))).setScale(4,BigDecimal.ROUND_HALF_UP);
					deaLastDay = dea.add(new BigDecimal(0));
					sd.setEma12(ema12.toString());
					sd.setEma26(ema26.toString());
					sd.setDiff(diff.toString());
					sd.setDea(dea.toString());
					sd.setBar((diff.subtract(dea)).multiply(new BigDecimal(2)).toString());
				}
				cnt++;
				stockService.updateStockDaily(sd);
			}
		System.out.println("共计"+cnt+"条数据");
	}
	
	public void getAvgStatusNewDay(String stockCode,int tradeDay) throws Exception {
		StockDaily lastDay = new StockDaily();
		lastDay.setStockCode(stockCode);
		lastDay.setTradeDay(tradeDay - 1);
		lastDay = stockMapper.getStockDaily(lastDay);
		BigDecimal ema12lastDay = new BigDecimal(lastDay.getEma12());
		BigDecimal ema26lastDay = new BigDecimal(lastDay.getEma26());
		BigDecimal deaLastDay = new BigDecimal(lastDay.getDea());
		StockDaily today = new StockDaily();
		today.setStockCode(stockCode);
		today.setTradeDay(tradeDay);
		today = stockMapper.getStockDaily(today);
		String closeToday = today.getCloseToday();
		if(closeToday == null || closeToday.length() == 0) {
			closeToday = "0";
		}
		BigDecimal ema12 = (new BigDecimal(closeToday).multiply(new BigDecimal(2)).divide(new BigDecimal(13),4,BigDecimal.ROUND_HALF_UP))
				.add(ema12lastDay.multiply(new BigDecimal(11)).divide(new BigDecimal(13),4,BigDecimal.ROUND_HALF_UP));
		ema12lastDay = ema12.add(new BigDecimal(0));
		BigDecimal ema26 = (new BigDecimal(closeToday).multiply(new BigDecimal(2)).divide(new BigDecimal(27),4,BigDecimal.ROUND_HALF_UP))
				.add(ema26lastDay.multiply(new BigDecimal(25)).divide(new BigDecimal(27),4,BigDecimal.ROUND_HALF_UP));
		ema26lastDay = ema26.add(new BigDecimal(0));
		BigDecimal diff = ema12.subtract(ema26);
		BigDecimal dea = (deaLastDay.multiply(new BigDecimal(0.8))).add(diff.multiply(new BigDecimal(0.2))).setScale(4,BigDecimal.ROUND_HALF_UP);
		deaLastDay = dea.add(new BigDecimal(0));
		today.setEma12(ema12.toString());
		today.setEma26(ema26.toString());
		today.setDiff(diff.toString());
		today.setDea(dea.toString());
		today.setBar((diff.subtract(dea)).multiply(new BigDecimal(2)).toString());
		stockService.updateStockDaily(today);
	}
}
