package com.jhyarrow.myWeb.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.jhyarrow.myWeb.domain.Line3;
import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.domain.support.SupportGoldenNeedle;
import com.jhyarrow.myWeb.mapper.StockMapper;
import com.jhyarrow.myWeb.mapper.SupportMapper;
import com.jhyarrow.myWeb.mapper.TradeDayMapper;
import com.jhyarrow.myWeb.service.SupportService;
import com.jhyarrow.myWeb.view.StockDailyView;

public class SupportServiceImpl implements SupportService{
	@Autowired
	private StockMapper stockMapper;
	@Autowired
	private SupportMapper supportMapper;
	@Autowired
	private TradeDayMapper tradeDayMapper;
	
	//获取MACD，从第一天开始
	public void getMACD(String stockCode) throws Exception {
		BigDecimal ema12lastDay = new BigDecimal(0);
		BigDecimal ema26lastDay = new BigDecimal(0);
		BigDecimal deaLastDay = new BigDecimal(0);
		int cnt = 0;
		ArrayList<StockDaily> list = stockMapper.getStockDailyList(stockCode);
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
			stockMapper.updateStockDaily(sd);
		}
		System.out.println("共计"+cnt+"条数据");
	}
	
	//获取第tradeDay天的MACD
	public void getMACD(int tradeDay){
		ArrayList<Stock> list = (ArrayList<Stock>) stockMapper.getStockList();
		for(int i=0;i<list.size();i++) {
			Stock stock = list.get(i);
			String code = stock.getStockCode();
			try {
				getMACD(code,tradeDay);
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println(code+"计算MACD失败！");
			}
		}
	}
	
	//获取MACD，从第tradeDay天开始
	private void getMACD(String stockCode,int tradeDay) throws Exception {
		StockDaily today = new StockDaily();
		today.setStockCode(stockCode);
		today.setTradeDay(tradeDay);
		today = stockMapper.getStockDaily(today);
		
		StockDaily yesterDay = new StockDaily();
		yesterDay.setStockCode(stockCode);
		yesterDay.setTradeDay(tradeDay-1);
		yesterDay = stockMapper.getStockDaily(yesterDay);
		BigDecimal ema12lastDay = new BigDecimal(yesterDay.getEma12());
		BigDecimal ema26lastDay = new BigDecimal(yesterDay.getEma26());
		BigDecimal deaLastDay = new BigDecimal(yesterDay.getDea());

		if(today == null) {
			return;
		}
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
		stockMapper.updateStockDaily(today);
	}

	//更新第tradeDay交易日的近3日走势
	public void updateLine3(int tradeDay) {
		ArrayList<Stock> stockList = (ArrayList<Stock>) stockMapper.getStockList();
		for(int i=0;i<stockList.size();i++) {
			Stock s = stockList.get(i);
			String code = s.getStockCode();
			//根据最新的一天判断，所以需要4天的记录
			ArrayList<StockDaily> list = (ArrayList<StockDaily>)stockMapper.getStockDailyListN(code,4);
			if(list.size() <=3) {
				continue;
			}
			String level1 = list.get(0).getUpLevel();
			String level2 = list.get(1).getUpLevel();
			String level3 = list.get(2).getUpLevel();
			int levelInt1 = Integer.parseInt(level1);
			int levelInt2 = Integer.parseInt(level2);
			int levelInt3 = Integer.parseInt(level3);
			if(levelInt1 > 10 || levelInt2 > 10 || levelInt3 > 10 || levelInt1 < -10 || levelInt2 < -10 || levelInt3 < -10) {
				continue;
			}
			Line3 tmp = new Line3();
			tmp.setDay1(level1);
			tmp.setDay2(level2);
			tmp.setDay3(level3);
			Line3 line3 = supportMapper.getLine3(tmp);
			if(line3 == null) {
				StockDaily sd = list.get(3);
				BigDecimal upPer = new BigDecimal(sd.getUpPer());
				if(upPer.compareTo(new BigDecimal(0)) > 0) {
					tmp.setUpDays(1);
					tmp.setAvgUp(upPer.floatValue());
					tmp.setDownDays(0);
					tmp.setAvgDown(0);
					supportMapper.addLine3(tmp);
				}else if (upPer.compareTo(new BigDecimal(0)) < 0){
					tmp.setUpDays(0);
					tmp.setDownDays(1);
					tmp.setAvgUp(0);
					tmp.setAvgDown(upPer.floatValue());
					supportMapper.addLine3(tmp);
				}
			}else {
				StockDaily sd = list.get(3);
				BigDecimal upPer = new BigDecimal(sd.getUpPer());
				if(upPer.compareTo(new BigDecimal(0)) > 0) {
					BigDecimal ans = new BigDecimal(line3.getAvgUp()).multiply(new BigDecimal(line3.getUpDays()));
					ans = ans.add(upPer);
					ans = ans.divide(new BigDecimal(line3.getUpDays()+1),4,BigDecimal.ROUND_HALF_UP);
					line3.setAvgUp(ans.floatValue());
					line3.setUpDays(line3.getUpDays() + 1);
					supportMapper.updateLine3(line3);
				}else if (upPer.compareTo(new BigDecimal(0)) < 0){
					BigDecimal ans = new BigDecimal(line3.getAvgDown()).multiply(new BigDecimal(line3.getDownDays()));
					ans = ans.add(upPer);
					ans = ans.divide(new BigDecimal(line3.getDownDays()+1),4,BigDecimal.ROUND_HALF_UP);
					line3.setAvgDown(ans.floatValue());
					line3.setDownDays(line3.getDownDays() + 1);
					supportMapper.updateLine3(line3);
				}
			}
			System.out.println(code+"操作完成");
		}
	}

	//获取金针探底
	public void updateGoldenNeedle(String stockCode) {
		ArrayList<StockDaily> list = stockMapper.getStockDailyList(stockCode);
		if(list.size() <6) {
			return;
		}
		for(int i=0;i<list.size()-6;i++) {
			StockDaily sd = list.get(i+5);
			BigDecimal openToday = new BigDecimal(sd.getOpenToday());
			BigDecimal closeToday = new BigDecimal(sd.getCloseToday());
			BigDecimal highestToday = new BigDecimal(sd.getHighest());
			BigDecimal lowest = new BigDecimal(sd.getLowest());
			BigDecimal upPer = new BigDecimal(sd.getUpPer());
			//实体
			BigDecimal entity = openToday.subtract(closeToday).abs();
			//上影线
			BigDecimal upLine = new BigDecimal(0);
			//下影线
			BigDecimal downLine = new BigDecimal(0);
			if(openToday.compareTo(closeToday) < 0) {
				upLine = highestToday.subtract(closeToday);
				downLine = openToday.subtract(lowest);
			}else {
				upLine = highestToday.subtract(openToday);
				downLine = closeToday.subtract(lowest);
			}
			
			if(upPer.abs().compareTo(new BigDecimal(1)) >= 0 && downLine.compareTo(entity.multiply(new BigDecimal(2))) > 0 && entity.compareTo(upLine.multiply(new BigDecimal(10))) > 0) {
				BigDecimal lowestYester4 = new BigDecimal(list.get(i).getLowest());
				if(lowestYester4.compareTo(lowest) < 0) {
					continue;
				}
				BigDecimal lowestYester3 = new BigDecimal(list.get(i+1).getLowest());
				if(lowestYester3.compareTo(lowest) < 0) {
					continue;
				}
				BigDecimal lowestYester2 = new BigDecimal(list.get(i+2).getLowest());
				if(lowestYester2.compareTo(lowest) < 0) {
					continue;
				}
				BigDecimal lowestYester1 = new BigDecimal(list.get(i+3).getLowest());
				if(lowestYester1.compareTo(lowest) < 0) {
					continue;
				}
				
				SupportGoldenNeedle sgn = new SupportGoldenNeedle();
				sgn.setStockCode(stockCode);
				sgn.setStockName(sd.getStockName());
				sgn.setDate(sd.getDate());
				sgn.setTradeDay(sd.getTradeDay());
				sgn.setCloseToday(sd.getCloseToday());
				if(i+6 < list.size()) {
					StockDaily sd1 = list.get(i+5);
					sgn.setMaxDay1(sd1.getHighest());
					sgn.setUpPer1(sd1.getUpPer());
					sgn.setCloseDay1(sd1.getCloseToday());
					sgn.setMinDay1(sd1.getLowest());
				}
				
				if(i+8 < list.size()) {
					StockDaily sd3 = list.get(i+7);
					sgn.setMaxDay3(sd3.getHighest());
					sgn.setCloseDay3(sd3.getCloseToday());
					sgn.setMinDay3(sd3.getLowest());
					sgn.setUpPer3((new BigDecimal(sd3.getCloseToday()).subtract(new BigDecimal(sd.getCloseToday())))
							.divide(new BigDecimal(sd.getCloseToday()),4,BigDecimal.ROUND_HALF_UP).toString());
				}
				
				if(i+10 < list.size()) {
					StockDaily sd5 = list.get(i+9);
					sgn.setMaxDay5(sd5.getHighest());
					sgn.setCloseDay5(sd5.getCloseToday());
					sgn.setMinDay5(sd5.getLowest());
					sgn.setUpPer5((new BigDecimal(sd5.getCloseToday()).subtract(new BigDecimal(sd.getCloseToday())))
							.divide(new BigDecimal(sd.getCloseToday()),4,BigDecimal.ROUND_HALF_UP).toString());
				}
				supportMapper.addSupportGoldenNeedle(sgn);
			}
		}
	}

	//获取tradeDay下金针探底
	public ArrayList<StockDailyView> getGoldenNeedle(String date){
		int tradeDay = tradeDayMapper.getTradeDayByDate(date);
		ArrayList<SupportGoldenNeedle> sgnList =  supportMapper.getSupportGoldenNeedle(tradeDay);
		ArrayList<StockDailyView> sdvList = new ArrayList<StockDailyView>();
		for(int i=0;i<sgnList.size();i++) {
			SupportGoldenNeedle sgn = sgnList.get(i);
			String stockCode = sgn.getStockCode();
			StockDaily sd  = new StockDaily();
			sd.setTradeDay(tradeDay);
			sd.setStockCode(stockCode);
			sd = stockMapper.getStockDaily(sd);
			
			StockDailyView sdv = new StockDailyView();
			sdv.setStockCode(sd.getStockCode());
			sdv.setStockName(sd.getStockName());
			sdv.setHighest(sd.getHighest());
			sdv.setLowest(sd.getLowest());
			sdv.setOpenToday(sd.getOpenToday());
			sdv.setCloseToday(sd.getCloseToday());
			sdv.setUp(sd.getUp());
			sdv.setUpPer(sd.getUpPer());
			
			sdvList.add(sdv);
		}
		return sdvList;
	}
}
