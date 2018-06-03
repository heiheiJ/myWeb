package com.jhyarrow.myWeb.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jhyarrow.myWeb.domain.SpiderStockDailyError;
import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.domain.StockIndex;
import com.jhyarrow.myWeb.domain.StockIndexDaily;
import com.jhyarrow.myWeb.mapper.StockIndexMapper;
import com.jhyarrow.myWeb.mapper.StockMapper;
import com.jhyarrow.myWeb.mapper.SupportMapper;
import com.jhyarrow.myWeb.mapper.TradeDayMapper;
import com.jhyarrow.myWeb.service.SpiderService;

public class SpiderServiceImpl implements SpiderService{
	@Autowired 
	private StockMapper stockMapper;
	@Autowired
	private TradeDayMapper tradeDayMapper;
	@Autowired
	private StockIndexMapper stockIndexMapper;
	@Autowired
	private SupportMapper supportMapper;
	/*
	 * @param date:yyyy-MM-dd
	 */
	public int spideStockDaily(String date) {
		ArrayList<Stock> list = (ArrayList<Stock>) stockMapper.getStockList();
		int tradeDay =  tradeDayMapper.getTradeDayByDate(date);
		for(int i=0;i<list.size();i++) {
			Stock stock = list.get(i);
			String code = stock.getStockCode();
			String stockName = stock.getStockName();
			Integer lastTradeDay = stock.getLastTradeDay(); 
			spideStockDaily(code,stockName,tradeDay,lastTradeDay,date.replaceAll("-", ""));
		}
		
		return stockMapper.getStockDailyCount(date);
	}
	
	/*
	 * @param date:yyyy-MM-dd
	 */
	public void spideStockIndexDaily(String date) {
		ArrayList<StockIndex> list = (ArrayList<StockIndex>) stockIndexMapper.getStockIndexList();
		int tradeDay =  tradeDayMapper.getTradeDayByDate(date);
		for(int i=0;i<list.size();i++) {
			StockIndex stockIndex = list.get(i);
			String stockCode = stockIndex.getStockCode();
			String stockName = stockIndex.getStockName();
			spideStockIndexDaily(stockCode,stockName,tradeDay,date.replaceAll("-", ""));
		}
		
	}
	public void spideStockDaily(String code,String stockName,int tradeDay,int lastTradeDay,String date) {
		String url = "http://q.stock.sohu.com/hisHq?code=cn_"+code+"&start="+date+"&end="+date+"&stat=1&order=D&period=d"
				+ "&callback=historySearchHandler&rt=json&r=0.8391495715053367&0.9677250558488026";
		
		HttpPost httpPost = new HttpPost(url);
		HttpClient httpCient = HttpClients.createDefault();
		HttpResponse httpResponse;
		String response = "" ;
		try {
			httpResponse = httpCient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			response = EntityUtils.toString(httpEntity,"utf-8");
			if("{}\n".equals(response)) {//停牌返回为空
				return;
			}
			response = response.substring(response.indexOf("[[")+1, response.indexOf("]]")+1);
			String stockList[] = response.split("],");
			for(int i=0;i<stockList.length;i++) {
				String datas[] = stockList[i].replaceAll("\\[", "").replaceAll("\\]","").replaceAll("\"","").split(",");
				StockDaily sd = new StockDaily();
				sd.setStockCode(code);
				sd.setStockName(stockName);
				sd.setDate(datas[0]);
				sd.setOpenToday(datas[1]);
				sd.setCloseToday(datas[2]);
				sd.setUp(datas[3]);
				String upPer = String.valueOf(Double.parseDouble(datas[4].replaceAll("-", "0").replaceAll("%", ""))/100.0);
				if(Double.parseDouble(datas[3]) < 0) {
					upPer = "-"+upPer;
				}
				sd.setUpPer(upPer);
				sd.setLowest(datas[5]);
				sd.setHighest(datas[6]);
				sd.setVolumn(datas[7]);
				sd.setTurnVolume(datas[8]);
				sd.setTurnoverRate(String.valueOf(Double.parseDouble(datas[9].replaceAll("-", "0").replaceAll("%", ""))/100.0));
				sd.setTradeDay(tradeDay);
				sd.setPrevTradeDay(lastTradeDay);
				BigDecimal upPerBd = new BigDecimal(upPer).multiply(new BigDecimal(100)).divide(new BigDecimal(1),0,BigDecimal.ROUND_HALF_UP);
				sd.setUpLevel(upPerBd.toString());
				stockMapper.addStockDaily(sd);
				
				//更新指针
				StockDaily lastStockDaily = stockMapper.getStockDaily(code, lastTradeDay);
				lastStockDaily.setNextTradeDay(tradeDay);
				stockMapper.updateStockDaily(lastStockDaily);
				
				Stock stock = stockMapper.getStockByCode(code);
				stock.setLastTradeDay(tradeDay);
				stockMapper.updateStock(stock);
			}
		} catch (Exception e) {
			System.out.println(code+"处理错误，response："+response);
			e.printStackTrace();
			
			SpiderStockDailyError ssde = new SpiderStockDailyError();
			ssde.setDate(date);
			ssde.setTradeDay(tradeDay);
			ssde.setStockCode(code);
			ssde.setStockName(stockName);
			addSpiderServiceImpl(ssde);
		}
	}
	
	public void spideStockDaily(String code,String stockName,String start,String end) {
		String url = "http://q.stock.sohu.com/hisHq?code=cn_"+code+"&start="+start+"&end="+end+"&stat=1&order=D&period=d"
				+ "&callback=historySearchHandler&rt=json&r=0.8391495715053367&0.9677250558488026";
		
		HttpPost httpPost = new HttpPost(url);
		HttpClient httpCient = HttpClients.createDefault();
		HttpResponse httpResponse;
		String response = "" ;
		try {
			httpResponse = httpCient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			response = EntityUtils.toString(httpEntity,"utf-8");
			if("{}\n".equals(response)) {//停牌返回为空
				return;
			}
			response = response.substring(response.indexOf("[[")+1, response.indexOf("]]")+1);
			String stockList[] = response.split("],");
			for(int i=0;i<stockList.length;i++) {
				String datas[] = stockList[i].replaceAll("\\[", "").replaceAll("\\]","").replaceAll("\"","").split(",");
				StockDaily sd = new StockDaily();
				sd.setStockCode(code);
				sd.setStockName(stockName);
				String date = datas[0];
				sd.setDate(datas[0]);
				sd.setOpenToday(datas[1]);
				sd.setCloseToday(datas[2]);
				sd.setUp(datas[3]);
				String upPer = String.valueOf(Double.parseDouble(datas[4].replaceAll("-", "0").replaceAll("%", ""))/100.0);
				if(Double.parseDouble(datas[3]) < 0) {
					upPer = "-"+upPer;
				}
				sd.setUpPer(upPer);
				sd.setLowest(datas[5]);
				sd.setHighest(datas[6]);
				sd.setVolumn(datas[7]);
				sd.setTurnVolume(datas[8]);
				sd.setTurnoverRate(String.valueOf(Double.parseDouble(datas[9].replaceAll("-", "0").replaceAll("%", ""))/100.0));
				sd.setTradeDay(tradeDayMapper.getTradeDayByDate(date));
				BigDecimal upPerBd = new BigDecimal(upPer).multiply(new BigDecimal(100)).divide(new BigDecimal(1),0,BigDecimal.ROUND_HALF_UP);
				sd.setUpLevel(upPerBd.toString());
				stockMapper.addStockDaily(sd);
			}
		} catch (Exception e) {
			System.out.println(code+"处理错误");
			e.printStackTrace();
		}
	}

	private void spideStockIndexDaily(String code,String stockName,int tradeDay,String date) {
		String url = "http://q.stock.sohu.com/hisHq?code=zs_"+code+"&start="+date+"&end="+date+"&stat=1&order=D&period=d"
				+ "&callback=historySearchHandler&rt=json&r=0.8391495715053367&0.9677250558488026";
		
		HttpPost httpPost = new HttpPost(url);
		HttpClient httpCient = HttpClients.createDefault();
		HttpResponse httpResponse;
		String response = "" ;
		try {
			httpResponse = httpCient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			response = EntityUtils.toString(httpEntity,"utf-8");
			if("{}\n".equals(response)) {//停牌返回为空
				return;
			}
			response = response.substring(response.indexOf("[[")+1, response.indexOf("]]")+1);
			String stockList[] = response.split("],");
			for(int i=0;i<stockList.length;i++) {
				String datas[] = stockList[i].replaceAll("\\[", "").replaceAll("\\]","").replaceAll("\"","").split(",");
				StockIndexDaily sd = new StockIndexDaily();
				sd.setStockCode(code);
				sd.setStockName(stockName);
				sd.setDate(datas[0]);
				sd.setOpenToday(datas[1]);
				sd.setCloseToday(datas[2]);
				sd.setUp(datas[3]);
				String upPer = String.valueOf(Double.parseDouble(datas[4].replaceAll("-", "0").replaceAll("%", ""))/100.0);
				if(Double.parseDouble(datas[3]) < 0) {
					upPer = "-"+upPer;
				}
				sd.setUpPer(upPer);
				sd.setLowest(datas[5]);
				sd.setHighest(datas[6]);
				sd.setVolumn(datas[7]);
				sd.setTurnVolume(datas[8]);
				sd.setTurnoverRate(String.valueOf(Double.parseDouble(datas[9].replaceAll("-", "0").replaceAll("%", ""))/100.0));
				sd.setTradeDay(tradeDay);
				stockIndexMapper.addStockIndexDaily(sd);
			}
		} catch (Exception e) {
			System.out.println(code+"处理错误");
			e.printStackTrace();
		}
	}

	private void addSpiderServiceImpl(SpiderStockDailyError ssde) {
		supportMapper.addSpiderStockDailyError(ssde);
	}
}
