package com.jhyarrow.myWeb.thread;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Callable;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jhyarrow.myWeb.domain.StockIndex;
import com.jhyarrow.myWeb.domain.StockIndexDaily;

public class StockIndexSpideThread implements Callable<StockIndexDaily>{
		private StockIndex stockIndex;
		private int tradeDay;
		
		public StockIndexSpideThread(StockIndex stockIndex,int tradeDay) {
			this.stockIndex = stockIndex;
			this.tradeDay = tradeDay;
		}

		public StockIndexDaily call() throws Exception {
			return spiderStockIndex(stockIndex,tradeDay);
		}
		
		public StockIndexDaily spiderStockIndex(StockIndex stockIndex,int tradeDay) {
			String url = stockIndex.getUrl();
			String stockCode = stockIndex.getStockCode();
			String stockName = stockIndex.getStockName();
			HttpPost httpPost = new HttpPost(url);
			HttpClient httpCient = HttpClients.createDefault();
			StockIndexDaily stockIndexDaily = new StockIndexDaily();
			try {
				HttpResponse httpResponse = httpCient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				String response = EntityUtils.toString(httpEntity,"utf-8");
				Document doc = Jsoup.parse(response);
				stockIndexDaily.setCode(stockCode);
				stockIndexDaily.setName(stockName);
				Calendar c = Calendar.getInstance();
				int w = c.get(Calendar.DAY_OF_WEEK) -1;
				if(w < 0) {
					w = 0;
				}
				stockIndexDaily.setWeekDay(w);
				stockIndexDaily.setTradeDay(tradeDay);
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
				stockIndexDaily.setDate(df.format(new Date()));
				
				//开头数据
				Elements rows = doc.select("div[class=price s-up ]");
				for(int i=0;i<rows.size();i++) {
					Element row = rows.get(i);
					String data = row.select("strong").text().replaceAll(",", "").trim();
					stockIndexDaily.setCloseToday(data);
					data = row.select("span").get(0).text().replaceAll(",", "").trim();
					stockIndexDaily.setUp(data);
					data = row.select("span").get(1).text().replaceAll(",", "").trim();
					if(data.contains("%")) {
						BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("%")));
						bd = bd.divide(new BigDecimal(100));
						stockIndexDaily.setUpPer(bd.toString());
					}
				}
				
				rows = doc.select("div[class=price s-down ]");
				for(int i=0;i<rows.size();i++) {
					Element row = rows.get(i);
					String data = row.select("strong").text().replaceAll(",", "").trim();
					stockIndexDaily.setCloseToday(data);
					data = row.select("span").get(0).text().replaceAll(",", "").trim();
					stockIndexDaily.setUp(data);
					data = row.select("span").get(1).text().replaceAll(",", "").trim();
					if(data.contains("%")) {
						BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("%")));
						bd = bd.divide(new BigDecimal(100));
						stockIndexDaily.setUpPer(bd.toString());
					}
				}
				
				//第一行数据
				rows = doc.select("div[class=bets-col-9]").get(0).select("dl");
				for(Element row : rows) {
					String title = row.select("dt").text().trim();
					String data = row.select("dd").text().replaceAll(",", "").replaceAll("--", "0").trim();
					if (title.contains("成交量")) {
						if(data.contains("万手")) {
							BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("万手")));
							stockIndexDaily.setVolumn(bd.toString());
						}else if (data.contains("亿手")) {
							BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("亿手")));
							bd = bd.multiply(new BigDecimal(10000));
							stockIndexDaily.setVolumn(bd.toString());
						}else if(data.contains("手")){
							BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("手")));
							bd = bd.divide(new BigDecimal(10000));
							stockIndexDaily.setVolumn(bd.toString());
						}else {
							BigDecimal bd = new BigDecimal(data);
							bd = bd.divide(new BigDecimal(10000));
							stockIndexDaily.setVolumn(bd.toString());
						}
					}else if (title.contains("最高")) {
						stockIndexDaily.setHighest(data);
					}else if (title.contains("成交额")) {
						if(data.contains("万")) {
							BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("万")));
							stockIndexDaily.setTurnVolume(bd.toString());
						}else if (data.contains("亿")) {
							BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("亿")));
							bd = bd.multiply(new BigDecimal(10000));
							stockIndexDaily.setTurnVolume(bd.toString());
						}else {
							BigDecimal bd = new BigDecimal(data);
							bd = bd.divide(new BigDecimal(10000));
							stockIndexDaily.setTurnVolume(bd.toString());
						}
					}else if (title.contains("最低")) {
						stockIndexDaily.setLowest(data);
					}else if (title.contains("今开")) {
						stockIndexDaily.setOpenToday(data);
					}else if (title.contains("涨家数")) {
						stockIndexDaily.setHigher(data);
					}else if (title.contains("跌家数")) {
						stockIndexDaily.setLower(data);
					}else if (title.contains("平家数")) {
						stockIndexDaily.setEqual(data);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(stockIndex.getStockCode()+"处理出错");
			}
			return stockIndexDaily;
	}

}
