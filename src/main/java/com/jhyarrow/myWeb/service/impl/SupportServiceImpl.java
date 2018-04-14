package com.jhyarrow.myWeb.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;

import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.service.StockService;
import com.jhyarrow.myWeb.service.SupportService;

public class SupportServiceImpl implements SupportService{
	@Autowired
	private StockService stockService;
	
	public void getAvgStatus(String stockCode) throws Exception {
		BigDecimal ema12lastDay = new BigDecimal(0);
		BigDecimal ema26lastDay = new BigDecimal(0);
		BigDecimal deaLastDay = new BigDecimal(0);
		int cnt = 0;
		for(int i=1990;i<=2018;i++) {
			ArrayList<StockDaily> list = stockService.getStockDailyList(String.valueOf(i), stockCode);
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
		}
		System.out.println("共计"+cnt+"条数据");
	}

}
