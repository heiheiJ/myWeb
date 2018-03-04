package com.jhyarrow.myWeb.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.domain.Support;
import com.jhyarrow.myWeb.mapper.StockMapper;
import com.jhyarrow.myWeb.mapper.SupportMapper;
import com.jhyarrow.myWeb.service.SupportService;

public class SupportServiceImpl implements SupportService{
	private static Logger logger = Logger.getLogger("SupportServiceImpl");
	@Autowired
	private SupportMapper supportMapper;
	@Autowired
	private StockMapper stockMapper;
	
	
	public BigDecimal max(BigDecimal a,BigDecimal b) {
		if(a.compareTo(b)>=0) {
			return a;
		}else {
			return b;
		}
	}
	
	public BigDecimal min(BigDecimal a,BigDecimal b) {
		if(a.compareTo(b)<=0) {
			return a;
		}else {
			return b;
		}
	}
	
	//上升或下降趋势
	/*
	 * @return 1上升2下降0其他
	 */
	public int getTrend(StockDaily stock) {
		try {
			StockDaily sd = new StockDaily(stock.getCode(),stock.getTradeDay()-1);
			StockDaily stockYesterDay = stockMapper.getStockDaily(sd);
			sd = new StockDaily(stock.getCode(),stockYesterDay.getTradeDay()-1);
			StockDaily stockYesterDay2 = stockMapper.getStockDaily(sd);
			
			
			BigDecimal closeToday = new BigDecimal(stock.getCloseToday());
			BigDecimal openToday = new BigDecimal(stock.getOpenToday());
			BigDecimal closeYesterDay = new BigDecimal(stockYesterDay.getCloseToday());
			BigDecimal openYesterDay = new BigDecimal(stockYesterDay.getOpenToday());
			BigDecimal closeYesterDay2 = new BigDecimal(stockYesterDay2.getCloseToday());
			BigDecimal openYesterDay2 = new BigDecimal(stockYesterDay2.getOpenToday());

			if(max(closeYesterDay,openYesterDay).compareTo(max(closeToday,openToday)) <=0 
					&& max(closeYesterDay2,openYesterDay2).compareTo(max(closeYesterDay,openYesterDay)) <=0
					&& min(closeYesterDay,openYesterDay).compareTo(min(closeToday,openToday)) <=0 
					&& min(closeYesterDay2,openYesterDay2).compareTo(min(closeYesterDay,openYesterDay)) <=0
					&& (closeToday.subtract(openYesterDay2)).divide(openYesterDay2,2).compareTo(new BigDecimal(0.03))>=0) {
				return 1;
			}else if (min(closeYesterDay,openYesterDay).compareTo(min(closeToday,openToday)) >=0 
					&& min(closeYesterDay2,openYesterDay2).compareTo(min(closeYesterDay,openYesterDay)) >=0
					&& max(closeYesterDay,openYesterDay).compareTo(max(closeToday,openToday)) >=0 
					&& max(closeYesterDay2,openYesterDay2).compareTo(max(closeYesterDay,openYesterDay)) >=0
					&& (closeToday.subtract(openYesterDay2)).divide(openYesterDay2,2).compareTo(new BigDecimal(-0.03))<=0) {
				return -1;
			}else {
				return 0;
			}
		}catch(Exception e) {
			logger.info("股票"+stock.getCode()+"出错");
			e.printStackTrace();
			return 0;
		}
		
	}

	public ArrayList<Support> getSupport() {
		return supportMapper.getSupport();
	}

	//检验
	public void check(int tradeDay) {
		ArrayList<Support> list = supportMapper.getSupportList(tradeDay);
		for(int i=0;i<list.size();i++) {
			Support support = list.get(i);
			String stockCode = support.getCode();
			StockDaily stockDaily = new StockDaily();
			stockDaily.setCode(stockCode);
			stockDaily.setTradeDay(tradeDay+1);
			stockDaily = stockMapper.getStockDaily(stockDaily);
			String up = stockDaily.getUp();
			if(up == null) {
				up = "0";
			}
			try {
				String upPer = stockDaily.getUpPer();
				if(upPer == null) {
					upPer = "0";
				}
				String upOrDown = support.getUpOrDown();
				StockDaily sd = new StockDaily(stockDaily.getCode(),stockDaily.getTradeDay()-1);
				support.setUp(Double.parseDouble(upPer));
				if(new BigDecimal(up).compareTo(new BigDecimal(0)) >0) {
					if("涨".equals(upOrDown)) {
						support.setIsTrue("Y");
					}else if ("关注涨".equals(upOrDown)) {
						Support su = new Support();
						su.setReason(support.getReason());
						su.setUpOrDown("涨");
						su.setCode(support.getCode());
						su.setName(support.getName());
						su.setDate(stockMapper.getDateByTradeDay(tradeDay+1));
						su.setTradeDay(tradeDay + 1);
						supportMapper.addSupport(su);
						logger.info("添加"+support.getReason()+support.getName()+"("+support.getCode()+")");
						support.setIsTrue("Y");
					}else {
						support.setIsTrue("N");
					}
				}else if(new BigDecimal(up).compareTo(new BigDecimal(0)) <0){
					if("跌".equals(upOrDown)) {
						support.setIsTrue("Y");
					}else if ("关注跌".equals(upOrDown)) {
						Support su = new Support();
						su.setReason(support.getReason());
						su.setUpOrDown("跌");
						su.setCode(support.getCode());
						su.setName(support.getName());
						su.setDate(stockMapper.getDateByTradeDay(tradeDay+1));
						su.setTradeDay(tradeDay + 1);
						supportMapper.addSupport(su);
						logger.info("添加"+support.getReason()+support.getName()+"("+support.getCode()+")");
						support.setIsTrue("Y");
					}else {
						support.setIsTrue("N");
					}
				}else {
					support.setIsTrue("N");
				}
				supportMapper.updateSupport(support);
			}catch(Exception e) {
				logger.info("股票"+support.getCode()+"出错");
				e.printStackTrace();
			}
			
		}
	}


	//计算
	public void getLine(StockDaily stock) {
		try {
			if(stock.getOpenToday()==null || stock.getCloseToday()==null) {
				return;
			}
			StockDaily sd = new StockDaily(stock.getCode(),stock.getTradeDay()-1);
			StockDaily stockYesterDay = stockMapper.getStockDaily(sd);
			if(stockYesterDay == null || stockYesterDay.getOpenToday()==null || stockYesterDay.getCloseToday()==null) {
				return;
			}
			sd = new StockDaily(stockYesterDay.getCode(),stockYesterDay.getTradeDay()-1);
			StockDaily stockYesterDay2 = stockMapper.getStockDaily(sd);
			if(stockYesterDay2== null || stockYesterDay2.getOpenToday()==null || stockYesterDay2.getCloseToday()==null) {
				return;
			}
			
			BigDecimal openToday = new BigDecimal(stock.getOpenToday());
			BigDecimal closeToday = new BigDecimal(stock.getCloseToday());
			BigDecimal openTodayYesterDay = new BigDecimal(stockYesterDay.getOpenToday());
			BigDecimal closeTodayYesterDay = new BigDecimal(stockYesterDay.getCloseToday());
			BigDecimal openTodayYesterDay2 = new BigDecimal(stockYesterDay2.getOpenToday());
			BigDecimal closeTodayYesterDay2 = new BigDecimal(stockYesterDay2.getCloseToday());
			BigDecimal highest = new BigDecimal(stock.getHighest());
			BigDecimal lowest = new BigDecimal(stock.getLowest());
			BigDecimal entityYesterDay = closeTodayYesterDay.subtract(openTodayYesterDay).abs();
			BigDecimal entityYesterDay2 = closeTodayYesterDay2.subtract(openTodayYesterDay2).abs();
			BigDecimal entityToday = closeToday.subtract(openToday).abs();
			BigDecimal hatchDown = min(openToday,closeToday).subtract(lowest);//下影线
			BigDecimal hatchUp = highest.subtract(max(openToday,closeToday));//上影线
			
			//前日跌，昨日跳空,今日上升,昨日实体小
			if(closeTodayYesterDay2.compareTo(openTodayYesterDay2) < 0 
					&& max(closeTodayYesterDay,openTodayYesterDay).compareTo(closeTodayYesterDay2) < 0 
					&& openToday.compareTo(min(openTodayYesterDay,closeTodayYesterDay)) > 0 
					&& closeToday.compareTo(max(openTodayYesterDay,closeTodayYesterDay)) > 0
					&& entityYesterDay2.compareTo(entityYesterDay.multiply(new BigDecimal(2))) > 0
					&& entityToday.compareTo(entityYesterDay.multiply(new BigDecimal(2))) > 0) {
				if (getTrend(stock) < 0) {
					Support support = new Support();
					support.setReason("启明星形态");
					support.setUpOrDown("涨");
					support.setCode(stock.getCode());
					support.setName(stock.getName());
					support.setDate(stock.getDate());
					support.setTradeDay(stock.getTradeDay());
					supportMapper.addSupport(support);
					logger.info("添加启明星"+stock.getName()+"("+stock.getCode()+")");
				}
			}
			
			if((closeToday.compareTo(openToday) >= 0 && closeTodayYesterDay.compareTo(openTodayYesterDay) <= 0) 
					&& hatchUp.compareTo(entityToday) < 0 && hatchDown.compareTo(entityToday) < 0) {//昨日跌今日涨
				
				//抱线形态
				if((openToday.compareTo(closeTodayYesterDay) < 0 && closeToday.compareTo(openTodayYesterDay) > 0)) {
					Support support = new Support();
					if (getTrend(stock) < 0) {
						support.setReason("看涨抱线形态");
						support.setUpOrDown("涨");
						support.setCode(stock.getCode());
						support.setName(stock.getName());
						support.setDate(stock.getDate());
						support.setTradeDay(stock.getTradeDay());
						supportMapper.addSupport(support);
						logger.info("添加看涨抱线"+stock.getName()+"("+stock.getCode()+")");
					}
				}
				
				//刺进形态
				BigDecimal MidEntityYesterDay = openTodayYesterDay.add(closeTodayYesterDay).divide(new BigDecimal(2),2);
				if(openToday.compareTo(closeTodayYesterDay) < 0 && closeToday.compareTo(MidEntityYesterDay) > 0) {
					Support support = new Support();
					if (getTrend(stock) < 0) {
						support.setReason("刺进形态");
						support.setUpOrDown("涨");
						support.setCode(stock.getCode());
						support.setName(stock.getName());
						support.setDate(stock.getDate());
						support.setTradeDay(stock.getTradeDay());
						supportMapper.addSupport(support);
						logger.info("添加刺进"+stock.getName()+"("+stock.getCode()+")");
					}
				}
			}
			
			if(entityToday.compareTo(new BigDecimal(0))!=0) {
				if(hatchDown.divide(entityToday,2).compareTo(new BigDecimal(2)) >=0 
						&& hatchUp.divide(entityToday,2).compareTo(new BigDecimal(0.03))<=0 ) {
					Support support = new Support();
					if (getTrend(stock) < 0) {
						support.setReason("锤子线");
						support.setUpOrDown("涨");
						logger.info("添加锤子线"+stock.getName()+"("+stock.getCode()+")");
						support.setCode(stock.getCode());
						support.setName(stock.getName());
						support.setDate(stock.getDate());
						support.setTradeDay(stock.getTradeDay());
						supportMapper.addSupport(support);
					}
				}
				
				if(hatchUp.divide(entityToday,2).compareTo(new BigDecimal(2)) >=0 
						&& hatchDown.divide(entityToday,2).compareTo(new BigDecimal(0.03))<=0 ) {
					Support support = new Support();
					if(getTrend(stock) < 0) {//倒锤子线关注，关注第二天价格是否向上跳空
						support.setReason("倒锤子线");
						support.setUpOrDown("关注涨");
						logger.info("关注倒锤子线"+stock.getName()+"("+stock.getCode()+")");
						support.setCode(stock.getCode());
						support.setName(stock.getName());
						support.setDate(stock.getDate());
						support.setTradeDay(stock.getTradeDay());
						supportMapper.addSupport(support);
					}
				}
			}
			
			//十字星线关注，关注第二天结果
			if(entityToday.compareTo(openToday.multiply(new BigDecimal(0.001)))  <= 0 
					&& hatchUp.subtract(closeToday).abs().compareTo(openToday.multiply(new BigDecimal(0.005))) >=0
					&& hatchDown.compareTo(openToday.multiply(new BigDecimal(0.005))) >=0) {
				Support support = new Support();
				if (getTrend(stock) < 0) {
					support.setReason("十字星线");
					support.setUpOrDown("关注涨");
					logger.info("关注十字星线股票涨"+stock.getName()+"("+stock.getCode()+")");
					support.setCode(stock.getCode());
					support.setName(stock.getName());
					support.setDate(stock.getDate());
					support.setTradeDay(stock.getTradeDay());
					supportMapper.addSupport(support);
				}
			}
			
			//看涨捉腰带线
			if(openToday.compareTo(lowest) == 0 && highest.compareTo(closeToday) > 0 
					&& closeToday.compareTo(openToday) > 0) {
				if(getTrend(stock) < 0) {
					Support support = new Support();
					support.setReason("捉腰带线");
					support.setUpOrDown("涨");
					logger.info("新增捉腰带线看涨"+stock.getName()+"("+stock.getCode()+")");
					support.setCode(stock.getCode());
					support.setName(stock.getName());
					support.setDate(stock.getDate());
					support.setTradeDay(stock.getTradeDay());
					supportMapper.addSupport(support);
				}
			}
			
			//看涨反击线
			if(closeTodayYesterDay.equals(closeToday) && entityYesterDay.divide(openTodayYesterDay,2).abs().compareTo(new BigDecimal(0.03)) >0
					&& entityToday.divide(openToday,2).abs().compareTo(new BigDecimal(0.03)) >0) {
				if(getTrend(stock) < 0) {
					Support support = new Support();
					support.setReason("反击线");
					support.setUpOrDown("涨");
					logger.info("新增反击线看涨"+stock.getName()+"("+stock.getCode()+")");
					support.setCode(stock.getCode());
					support.setName(stock.getName());
					support.setDate(stock.getDate());
					support.setTradeDay(stock.getTradeDay());
					supportMapper.addSupport(support);
				}
			}
			
		}catch(Exception e) {
			logger.info("股票"+stock.getCode()+"出错");
			e.printStackTrace();
		}
		
	}
}
