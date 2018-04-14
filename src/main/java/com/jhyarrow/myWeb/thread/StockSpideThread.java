package com.jhyarrow.myWeb.thread;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.service.StockService;

public class StockSpideThread implements Runnable{
	public static StockService stockService;
	private String stockCode;
	
	public StockSpideThread(String stockCode) {
		this.stockCode = stockCode;
	}
	
	public void run() {
		spideStockDaily(stockCode);
	}
	
	public void spideStockDaily(String stockCode) {
		Stock stock = stockService.getStockByCode(stockCode);
		String stockName = stock.getStockName();
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String date = df.format(c.getTime());
		String url = "http://q.stock.sohu.com/hisHq?code=cn_"+stockCode+"&start="+date+"&end="+date+"&stat=1&order=D&period=d"
				+ "&callback=historySearchHandler&rt=json&r=0.8391495715053367&0.9677250558488026";
		HttpPost httpPost = new HttpPost(url);
		HttpClient httpCient = HttpClients.createDefault();
		HttpResponse httpResponse;
		try {
			httpResponse = httpCient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			String response = EntityUtils.toString(httpEntity,"utf-8");
			response = response.substring(response.indexOf("[[")+1, response.indexOf("]]")+1);
			String stockList[] = response.split("],");
			for(int i=0;i<stockList.length;i++) {
				String datas[] = stockList[i].replaceAll("\\[", "").replaceAll("\\]","").replaceAll("\"","").split(",");
				StockDaily sd = new StockDaily();
				sd.setStockCode(stockCode);
				sd.setStockName(stockName);
				sd.setDate(datas[0]);
				sd.setOpenToday(datas[1]);
				sd.setCloseToday(datas[2]);
				sd.setUp(datas[3]);
				sd.setUpPer(String.valueOf(Double.parseDouble(datas[4].replaceAll("-", "0").replaceAll("%", ""))/100.0));
				sd.setLowest(datas[5]);
				sd.setHighest(datas[6]);
				sd.setVolumn(datas[7]);
				sd.setTurnVolume(datas[8]);
				sd.setTurnoverRate(String.valueOf(Double.parseDouble(datas[9].replaceAll("-", "0").replaceAll("%", ""))/100.0));
				stockService.addStockDaily(sd);
			}
			System.out.println(stockCode+"处理完成");
		}catch (Exception e) {
			System.out.println(stockCode+"处理错误");
			e.printStackTrace();
		}
	}
		
}
