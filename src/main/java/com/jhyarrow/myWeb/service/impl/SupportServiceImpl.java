package com.jhyarrow.myWeb.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.jhyarrow.myWeb.domain.DualThrust;
import com.jhyarrow.myWeb.domain.Line;
import com.jhyarrow.myWeb.domain.Line5Conclusion;
import com.jhyarrow.myWeb.domain.SpiderStockDailyAllError;
import com.jhyarrow.myWeb.domain.SpiderStockDailyError;
import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.domain.Support5;
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
	
	//计算macd,从第一天开始
	public void getMACD() {
		ArrayList<Stock> szList = (ArrayList<Stock>) stockMapper.getStockListSz();
		for(int i=0;i<szList.size();i++) {
			String stockCode = szList.get(i).getStockCode();
			try {
				getMACD(stockCode);
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println(stockCode+"计算MACD失败");
			}
		}
		
		ArrayList<Stock> shList = (ArrayList<Stock>) stockMapper.getStockListSh();
		for(int i=0;i<shList.size();i++) {
			String stockCode = shList.get(i).getStockCode();
			try {
				getMACD(stockCode);
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println(stockCode+"计算MACD失败");
			}
		}
		
		ArrayList<Stock> cyList = (ArrayList<Stock>) stockMapper.getStockListCy();
		for(int i=0;i<cyList.size();i++) {
			String stockCode = cyList.get(i).getStockCode();
			try {
				getMACD(stockCode);
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println(stockCode+"计算MACD失败");
			}
		}
	}
	//计算stockCODE的macd
	private void getMACD(String stockCode) throws Exception {
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
			stockMapper.updateStockDailyMACD(sd);
		}
	}
	//计算九日内最大最小值
	public void get9() {
		ArrayList<Stock> stockList = stockMapper.getStockList();
		for(int j=0;j<stockList.size();j++) {
			Stock s = stockList.get(j);
			String stockCode = s.getStockCode();
			ArrayList<StockDaily> list = stockMapper.getStockDailyList(stockCode);
			for(int i=0;i<list.size();i++) {
				BigDecimal max9 = new BigDecimal(-1);
				BigDecimal min9 = new BigDecimal(1000000);	
				StockDaily sd = list.get(i);
				int cnt = 0;
				while(cnt<9) {
					if(i-cnt>=0) {
						StockDaily sdTmp = list.get(i-cnt);
						if(max9.compareTo(new BigDecimal(sdTmp.getHighest()))<0) {
							max9 = new BigDecimal(sdTmp.getHighest());
						}
						if(min9.compareTo(new BigDecimal(sdTmp.getLowest())) > 0) {
							min9 = new BigDecimal(sdTmp.getLowest());
						}
					}else {
						break;					
					}
					cnt++;
				}
				sd.setMax9(max9.floatValue());
				sd.setMin9(min9.floatValue());
				stockMapper.updateStockDailyKdj(sd);
			}
		}			
	}
	//获取KDJ,从第一天开始
	public void getKDJ() {
		ArrayList<Stock> szList = (ArrayList<Stock>) stockMapper.getStockListSz();
		for(int i=0;i<szList.size();i++) {
			String stockCode = szList.get(i).getStockCode();
			try {
				getKDJ(stockCode);
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println(stockCode+"计算KDJ失败");
			}
		}
		
		ArrayList<Stock> shList = (ArrayList<Stock>) stockMapper.getStockListSh();
		for(int i=0;i<shList.size();i++) {
			String stockCode = shList.get(i).getStockCode();
			try {
				getKDJ(stockCode);
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println(stockCode+"计算KDJ失败");
			}
		}
		
		ArrayList<Stock> cyList = (ArrayList<Stock>) stockMapper.getStockListCy();
		for(int i=0;i<cyList.size();i++) {
			String stockCode = cyList.get(i).getStockCode();
			try {
				getKDJ(stockCode);
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println(stockCode+"计算KDJ失败");
			}
		}
	}
	//计算stockCODE的KDJ
	private void getKDJ(String stockCode) {
		BigDecimal kYesterday = new BigDecimal(0);
		BigDecimal dYesterday = new BigDecimal(0);
		BigDecimal jYesterday = new BigDecimal(0);
		ArrayList<StockDaily> list = stockMapper.getStockDailyList(stockCode);
		for(int i=0;i<list.size();i++) {
			StockDaily sd = list.get(i);
			String closeToday = sd.getCloseToday();
			float lowest = sd.getMin9();
			float highest = sd.getMax9();
			BigDecimal rsv;
			if(new BigDecimal(highest).subtract(new BigDecimal(lowest)).compareTo(new BigDecimal(0)) == 0){
				rsv = new BigDecimal(100);
			}else {
				rsv = (new BigDecimal(closeToday).subtract(new BigDecimal(lowest)))
						.divide(new BigDecimal(highest).subtract(new BigDecimal(lowest)),6,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
			}
			
			if(i == 0) {
				kYesterday = rsv.divide(new BigDecimal(3),4,BigDecimal.ROUND_HALF_UP);
				dYesterday = kYesterday.divide(new BigDecimal(3),4,BigDecimal.ROUND_HALF_UP);
				jYesterday = kYesterday.multiply(new BigDecimal(3)).subtract(dYesterday.multiply(new BigDecimal(2)));
				sd.setK(kYesterday.floatValue());
				sd.setD(dYesterday.floatValue());
				sd.setJ(jYesterday.floatValue());
				sd.setRsv(rsv.floatValue());
				stockMapper.updateStockDailyKdj(sd);
			}else {
				kYesterday = kYesterday.multiply(new BigDecimal(2)).divide(new BigDecimal(3),4,BigDecimal.ROUND_HALF_UP)
						.add(rsv.divide(new BigDecimal(3),4,BigDecimal.ROUND_HALF_UP));
				dYesterday = dYesterday.multiply(new BigDecimal(2)).divide(new BigDecimal(3),4,BigDecimal.ROUND_HALF_UP)
						.add(kYesterday.divide(new BigDecimal(3),4,BigDecimal.ROUND_HALF_UP));
				jYesterday = kYesterday.multiply(new BigDecimal(3)).subtract(dYesterday.multiply(new BigDecimal(2)));
				sd.setK(kYesterday.floatValue());
				sd.setD(dYesterday.floatValue());
				sd.setJ(jYesterday.floatValue());
				sd.setRsv(rsv.floatValue());
				stockMapper.updateStockDailyKdj(sd);
			}
		}
	}
	//获取爬虫错误记录列表
	public ArrayList<SpiderStockDailyAllError> getSpiderStockDailyAllErrorList() {
		return supportMapper.getSpiderStockDailyAllErrorList();
	}
	//删除爬虫错误记录
	public void deleteSpiderStockDailyAllError(SpiderStockDailyAllError ssde) {
		supportMapper.deleteSpiderStockDailyAllError(ssde);
	}
	//计算5日趋势推荐
	public void getSupport5(Stock s) {
		ArrayList<StockDaily> list = stockMapper.getStockDailyListN(s.getStockCode(), 5, null);
		if(list.size() == 5) {
			Integer day5 = new BigDecimal(list.get(0).getUpPer()).multiply(new BigDecimal(100)).divide(new BigDecimal(1),BigDecimal.ROUND_HALF_UP).intValue();
			Integer day4 = new BigDecimal(list.get(1).getUpPer()).multiply(new BigDecimal(100)).divide(new BigDecimal(1),BigDecimal.ROUND_HALF_UP).intValue();
			Integer day3 = new BigDecimal(list.get(2).getUpPer()).multiply(new BigDecimal(100)).divide(new BigDecimal(1),BigDecimal.ROUND_HALF_UP).intValue();
			Integer day2 = new BigDecimal(list.get(3).getUpPer()).multiply(new BigDecimal(100)).divide(new BigDecimal(1),BigDecimal.ROUND_HALF_UP).intValue();
			Integer day1 = new BigDecimal(list.get(4).getUpPer()).multiply(new BigDecimal(100)).divide(new BigDecimal(1),BigDecimal.ROUND_HALF_UP).intValue();
			Line5Conclusion line5Conclusion = new Line5Conclusion();
			line5Conclusion.setDay1(day1);
			line5Conclusion.setDay2(day2);
			line5Conclusion.setDay3(day3);
			line5Conclusion.setDay4(day4);
			line5Conclusion.setDay5(day5);
			line5Conclusion = supportMapper.getLine5Conclusion(line5Conclusion);
			if(line5Conclusion == null) {
				return;
			}
			float p = line5Conclusion.getP();
			if(p<0.8) {
				return;
			}
			int cnt = line5Conclusion.getCnt();
			if(cnt < 10) {
				return;
			}
			Support5 support = new Support5();
			support.setAvg(line5Conclusion.getAvg());
			support.setDate("2017-01-16");
			support.setP(line5Conclusion.getP());
			support.setStockName(s.getStockName());
			support.setStockCode(s.getStockCode());
			supportMapper.addSupport5(support);
		}
	}
	
	//DualThrust策略
	public void getDualThrust(Stock s) {
		String stockCode = s.getStockCode();
		ArrayList<StockDaily> stockDailyList = stockMapper.getStockDailyList(stockCode);
		if(stockDailyList.size() == 0) {
			return;
		}
		boolean isIn = false;//是否建仓
		BigDecimal high = new BigDecimal(stockDailyList.get(0).getHighest());
		BigDecimal close = new BigDecimal(stockDailyList.get(0).getCloseToday());
		BigDecimal low = new BigDecimal(stockDailyList.get(0).getLowest());
		BigDecimal range;
		BigDecimal k1 = new BigDecimal(0.5);
		BigDecimal k2 = new BigDecimal(0.5);
		if(high.subtract(close).abs().compareTo(low.subtract(close).abs()) > 0) {
			range = high.subtract(close).abs();
		}else {
			range = low.subtract(close).abs();
		}
		DualThrust d = new DualThrust();
		
		for(int i=1;i<stockDailyList.size();i++) {
			StockDaily stockDaily = stockDailyList.get(i);
			BigDecimal open = new BigDecimal(stockDaily.getOpenToday());
			BigDecimal buyLine1 = k1.multiply(range).add(open);
			BigDecimal buyLine2 = open.subtract(k2.multiply(range));
			high = new BigDecimal(stockDaily.getHighest());
			if(!isIn && high.compareTo(buyLine1) >=0) {//建仓
				String date = stockDaily.getDate();
				d.setBuyDate(date);
				d.setStockCode(stockCode);
				d.setBuyAmt(buyLine1.floatValue());
				isIn = true;
				continue;
			}
			if(isIn && low.compareTo(buyLine2) <=0) {//清仓
				String date = stockDaily.getDate();
				d.setSellDate(date);
				d.setSellAmt(buyLine2.floatValue());
				supportMapper.addDualThrust(d);
				isIn = false;
				continue;
			}
		}
	}
	
	
	//获取趋势线
	public void getLine() {
		
		
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
	
	//获取第tradeDay天的KDJ
	public void getKDJ(int tradeDay){
		ArrayList<Stock> list = (ArrayList<Stock>) stockMapper.getStockList();
		for(int i=0;i<list.size();i++) {
			Stock stock = list.get(i);
			String code = stock.getStockCode();
			try {
				getKDJ(code,tradeDay);
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println(code+"计算KDJ失败！");
			}
		}
	}
	
	public void getKDJ(String stockCode,int tradeDay) {
		StockDaily sdToday = stockMapper.getStockDaily(stockCode, tradeDay);
		if(sdToday.getPrevTradeDay() == null) {//只有一天
			String closeToday = sdToday.getCloseToday();
			String lowest = sdToday.getLowest();
			String highest = sdToday.getHighest();
			BigDecimal rsv;
			if(new BigDecimal(highest).subtract(new BigDecimal(lowest)).compareTo(new BigDecimal(0)) == 0){
				rsv = new BigDecimal(100);
			}else {
				rsv = (new BigDecimal(closeToday).subtract(new BigDecimal(lowest)))
						.divide(new BigDecimal(highest).subtract(new BigDecimal(lowest)),6,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
			}
			BigDecimal kYesterday = rsv.divide(new BigDecimal(3),4,BigDecimal.ROUND_HALF_UP);
			BigDecimal dYesterday = kYesterday.divide(new BigDecimal(3),4,BigDecimal.ROUND_HALF_UP);
			BigDecimal jYesterday = kYesterday.multiply(new BigDecimal(3)).subtract(dYesterday.multiply(new BigDecimal(2)));
			if(jYesterday.compareTo(new BigDecimal(100)) > 0) {
				jYesterday = new BigDecimal(100);
			}else if (jYesterday.compareTo(new BigDecimal(0)) < 0) {
				jYesterday = new BigDecimal(0);
			}
			sdToday.setK(kYesterday.floatValue());
			sdToday.setD(dYesterday.floatValue());
			sdToday.setJ(jYesterday.floatValue());
			sdToday.setRsv(rsv.floatValue());
			stockMapper.updateStockDailyKdj(sdToday);
		}else {
			int cnt = 8;
			BigDecimal max9 = new BigDecimal(sdToday.getHighest());
			BigDecimal min9 = new BigDecimal(sdToday.getLowest());
			StockDaily sdYesterDay = stockMapper.getStockDaily(stockCode, sdToday.getPrevTradeDay());
			BigDecimal kYesterday = new BigDecimal(sdYesterDay.getK());
			BigDecimal dYesterday = new BigDecimal(sdYesterDay.getD());
			BigDecimal jYesterday = new BigDecimal(sdYesterDay.getJ());
			while(cnt>0) {
				if(sdYesterDay.getPrevTradeDay() == null) {
					break;
				}
				BigDecimal highest = new BigDecimal(sdYesterDay.getHighest());
				BigDecimal lowest = new BigDecimal(sdYesterDay.getLowest());
				if(highest.compareTo(max9) > 0) {
					max9 = highest;
				}
				if(lowest.compareTo(min9) < 0) {
					min9 = lowest;
				}
				cnt--;
			}
			BigDecimal rsv;
			if(new BigDecimal(sdToday.getHighest()).subtract(new BigDecimal(sdToday.getLowest())).compareTo(new BigDecimal(0)) == 0){
				rsv = new BigDecimal(100);
			}else {
				rsv = (new BigDecimal(sdToday.getCloseToday()).subtract(new BigDecimal(sdToday.getLowest())))
						.divide(new BigDecimal(sdToday.getHighest()).subtract(new BigDecimal(sdToday.getLowest())),6,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
			}
			kYesterday = rsv.divide(new BigDecimal(3),4,BigDecimal.ROUND_HALF_UP);
			dYesterday = kYesterday.divide(new BigDecimal(3),4,BigDecimal.ROUND_HALF_UP);
			jYesterday = kYesterday.multiply(new BigDecimal(3)).subtract(dYesterday.multiply(new BigDecimal(2)));
			if(jYesterday.compareTo(new BigDecimal(100)) > 0) {
				jYesterday = new BigDecimal(100);
			}else if (jYesterday.compareTo(new BigDecimal(0)) < 0) {
				jYesterday = new BigDecimal(0);
			}
			sdToday.setK(kYesterday.floatValue());
			sdToday.setD(dYesterday.floatValue());
			sdToday.setJ(jYesterday.floatValue());
			sdToday.setRsv(rsv.floatValue());
			stockMapper.updateStockDailyKdj(sdToday);
		}
	}
	
	//获取MACD，从第tradeDay天开始
	public void getMACD(String stockCode,int tradeDay) throws Exception {
		StockDaily today = stockMapper.getStockDaily(stockCode,tradeDay);
		if(today == null)
			return;
		StockDaily yesterDay = stockMapper.getStockDaily(stockCode,today.getPrevTradeDay());
		if(yesterDay == null) {
			String closeToday = today.getCloseToday();
			if(closeToday == null || closeToday.length() == 0) {
				closeToday = "0";
			}
			today.setEma12(new BigDecimal(closeToday).toString());
			today.setEma26(new BigDecimal(closeToday).toString());
			today.setDiff("0");
			today.setDea("0");
			today.setBar("0");
			stockMapper.updateStockDailyMACD(today);
		}else {
			BigDecimal ema12lastDay = new BigDecimal(yesterDay.getEma12());
			BigDecimal ema26lastDay = new BigDecimal(yesterDay.getEma26());
			BigDecimal deaLastDay = new BigDecimal(yesterDay.getDea());

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
			stockMapper.updateStockDailyMACD(today);
		}
		
	}

	//获取金针探底
	public void updateGoldenNeedle(String stockCode) {
		ArrayList<StockDaily> list = stockMapper.getStockDailyList(stockCode);
		if(list.size() <3) {
			return;
		}
		StockDaily sd1,sd2,sd3;
		BigDecimal upPer1,min1,upPer3,min3,upLine,downLine,max2,openToday,closeToday,highestToday,lowest;
		for(int i=0;i<list.size()-3;i++) {
			sd1 = list.get(i);
			sd2 = list.get(i+1);
			sd3 = list.get(i+2);
			
			upPer1 = new BigDecimal(sd1.getUpPer());
			min1 = new BigDecimal(sd1.getOpenToday()).compareTo(new BigDecimal(sd1.getCloseToday())) > 0 
					? new BigDecimal(sd1.getCloseToday()) : new BigDecimal(sd1.getOpenToday());
			
			upPer3 = new BigDecimal(sd3.getUpPer());
			min3 = new BigDecimal(sd3.getOpenToday()).compareTo(new BigDecimal(sd3.getCloseToday())) > 0 
					? new BigDecimal(sd3.getCloseToday()) : new BigDecimal(sd3.getOpenToday());
			
			openToday = new BigDecimal(sd2.getOpenToday());
			closeToday = new BigDecimal(sd2.getCloseToday());
			highestToday = new BigDecimal(sd2.getHighest());
			lowest = new BigDecimal(sd2.getLowest());
			//实体
			BigDecimal entity = openToday.subtract(closeToday).abs();
			//上影线
			upLine = new BigDecimal(0);
			//下影线
			downLine = new BigDecimal(0);
			max2 = new BigDecimal(0);
			if(openToday.compareTo(closeToday) < 0) {
				max2 = closeToday;
				upLine = highestToday.subtract(closeToday);
				downLine = openToday.subtract(lowest);
			}else {
				max2 = openToday;
				upLine = highestToday.subtract(openToday);
				downLine = closeToday.subtract(lowest);
			}
			
			if(upPer1.compareTo(new BigDecimal(0)) < 0 && upPer3.compareTo(new BigDecimal(0)) > 0 
					&& min1.compareTo(max2) > 0 && min3.compareTo(max2) > 0 &&
					 downLine.compareTo(entity.multiply(new BigDecimal(2))) > 0 
					&& entity.compareTo(upLine.multiply(new BigDecimal(10))) > 0) {
				SupportGoldenNeedle sgn = new SupportGoldenNeedle();
				sgn.setDate(sd3.getDate());
				sgn.setStockCode(stockCode);
				sgn.setStockName(sd3.getStockName());
				sgn.setTradeDay(sd3.getTradeDay());
				supportMapper.addSupportGoldenNeedle(sgn);
				System.out.println("发现金针探底"+stockCode + sd3.getStockName());
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
			StockDaily sd = stockMapper.getStockDaily(stockCode,tradeDay);
			
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

	public ArrayList<SpiderStockDailyError> getSpiderStockDailyErrorList() {
		return supportMapper.getSpiderStockDailyErrorList();
	}

	public void deleteSpiderStockDailyError(SpiderStockDailyError ssde) {
		supportMapper.deleteSpiderStockDailyError(ssde);
	}
}
