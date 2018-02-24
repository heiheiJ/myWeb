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

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockDaily;

public class StockSpideThread implements Callable<StockDaily>{
	private Stock stock;
	private int tradeDay;
	
	public StockSpideThread(Stock stock,int tradeDay) {
		this.stock = stock;
		this.tradeDay = tradeDay;
	}

	public StockDaily call() throws Exception {
		return spiderStock(stock,tradeDay);
	}
	
	public StockDaily spiderStock(Stock stock,int tradeDay) {
		String url = stock.getUrl();
		String stockCode = stock.getStockCode();
		String stockName = stock.getStockName();
		HttpPost httpPost = new HttpPost(url);
		HttpClient httpCient = HttpClients.createDefault();
		StockDaily stockDaily = new StockDaily();
		try {
			HttpResponse httpResponse = httpCient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			String response = EntityUtils.toString(httpEntity,"utf-8");
			Document doc = Jsoup.parse(response);
			stockDaily.setCode(stockCode);
			stockDaily.setName(stockName);
			Calendar c = Calendar.getInstance();
			int w = c.get(Calendar.DAY_OF_WEEK) -1;
			if(w < 0) {
				w = 0;
			}
			stockDaily.setWeekDay(w);
			stockDaily.setTradeDay(tradeDay);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
			stockDaily.setDate(df.format(new Date()));
			
			//开头数据
			Elements rows = doc.select("div[class=price s-up ]");
			for(int i=0;i<rows.size();i++) {
				Element row = rows.get(i);
				String data = row.select("strong").text().replaceAll(",", "").trim();
				stockDaily.setCloseToday(data);
				data = row.select("span").get(0).text().replaceAll(",", "").trim();
				stockDaily.setUp(data);
				data = row.select("span").get(1).text().replaceAll(",", "").trim();
				if(data.contains("%")) {
					BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("%")));
					bd = bd.divide(new BigDecimal(100));
					stockDaily.setUpPer(bd.toString());
				}
			}
			
			rows = doc.select("div[class=price s-down ]");
			for(int i=0;i<rows.size();i++) {
				Element row = rows.get(i);
				String data = row.select("strong").text().replaceAll(",", "").trim();
				stockDaily.setCloseToday(data);
				data = row.select("span").get(0).text().replaceAll(",", "").trim();
				stockDaily.setUp(data);
				data = row.select("span").get(1).text().replaceAll(",", "").trim();
				if(data.contains("%")) {
					BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("%")));
					bd = bd.divide(new BigDecimal(100));
					stockDaily.setUpPer(bd.toString());
				}
			}
			
			//第一行数据
			rows = doc.select("div[class=line1]").get(0).select("dl");
			for(Element row : rows) {
				String title = row.select("dt").text().trim();
				String data = row.select("dd").text().replaceAll(",", "").replaceAll("--", "0").trim();
				if(title.contains("今开")) {
					stockDaily.setOpenToday(data);
				}else if (title.contains("成交量")) {
					if(data.contains("万手")) {
						BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("万手")));
						stockDaily.setVolumn(bd.toString());
					}else if (data.contains("亿手")) {
						BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("亿手")));
						bd = bd.multiply(new BigDecimal(10000));
						stockDaily.setVolumn(bd.toString());
					}else if(data.contains("手")){
						BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("手")));
						bd = bd.divide(new BigDecimal(10000));
						stockDaily.setVolumn(bd.toString());
					}else {
						BigDecimal bd = new BigDecimal(data);
						bd = bd.divide(new BigDecimal(10000));
						stockDaily.setVolumn(bd.toString());
					}
				}else if (title.contains("最高")) {
					stockDaily.setHighest(data);
				}else if (title.contains("涨停")) {
					stockDaily.setHarden(data);
				}else if (title.contains("内盘")) {
					if(data.contains("万手")) {
						BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("万手")));
						stockDaily.setInvol(bd.toString());
					}else if (data.contains("亿手")) {
						BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("亿手")));
						bd = bd.multiply(new BigDecimal(10000));
						stockDaily.setInvol(bd.toString());
					}else if(data.contains("手")){
						BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("手")));
						bd = bd.divide(new BigDecimal(10000));
						stockDaily.setInvol(bd.toString());
					}else {
						BigDecimal bd = new BigDecimal(data);
						bd = bd.divide(new BigDecimal(10000));
						stockDaily.setInvol(bd.toString());
					}
				}else if (title.contains("成交额")) {
					if(data.contains("万")) {
						BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("万")));
						stockDaily.setTurnVolume(bd.toString());
					}else if (data.contains("亿")) {
						BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("亿")));
						bd = bd.multiply(new BigDecimal(10000));
						stockDaily.setTurnVolume(bd.toString());
					}else {
						BigDecimal bd = new BigDecimal(data);
						bd = bd.divide(new BigDecimal(10000));
						stockDaily.setTurnVolume(bd.toString());
					}
				}else if (title.contains("委比")) {
					if(data.contains("%")) {
						BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("%")));
						bd = bd.divide(new BigDecimal(100));
						stockDaily.setTheCommittee(bd.toString());
					}else {
						stockDaily.setTheCommittee(data);
					}
				}else if (title.contains("流通市值")) {
					if(data.contains("万")) {
						BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("万")));
						stockDaily.setFamc(bd.toString());
					}else if (data.contains("亿")) {
						BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("亿")));
						bd = bd.multiply(new BigDecimal(10000));
						stockDaily.setFamc(bd.toString());
					}else {
						BigDecimal bd = new BigDecimal(data);
						bd = bd.divide(new BigDecimal(10000));
						stockDaily.setFamc(bd.toString());
					}
				}else if (title.contains("市盈率")) {
					stockDaily.setPeRatio(data);
				}else if (title.contains("每股收益")) {
					stockDaily.setEarningPerShare(data);
				}else if (title.contains("总股本")) {
					if(data.contains("万")) {
						BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("万")));
						stockDaily.setGeneralCapital(bd.toString());
					}else if (data.contains("亿")) {
						BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("亿")));
						bd = bd.multiply(new BigDecimal(10000));
						stockDaily.setGeneralCapital(bd.toString());
					}else {
						BigDecimal bd = new BigDecimal(data);
						bd = bd.divide(new BigDecimal(10000));
						stockDaily.setGeneralCapital(bd.toString());
					}
				}
			}
			
			//第二行数据
			rows = doc.select("div[class=line2]").get(0).select("dl");
			for(Element row : rows) {
				String title = row.select("dt").text().trim();
				String data = row.select("dd").text().replaceAll(",", "").replaceAll("--", "0").trim();
				if(title.contains("昨收")) {
					stockDaily.setCloseLastday(data);
				}else if (title.contains("换手率")) {
					if(data.contains("%")) {
						BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("%")));
						bd = bd.divide(new BigDecimal(100));
						stockDaily.setTurnoverRate(bd.toString());
					}
				}else if (title.contains("最低")) {
					stockDaily.setLowest(data);
				}else if (title.contains("跌停")) {
					stockDaily.setLowLimit(data);
				}else if (title.contains("外盘")) {
					if(data.contains("万手")) {
						BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("万手")));
						stockDaily.setOuterDisc(bd.toString());
					}else if (data.contains("亿手")) {
						BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("亿手")));
						bd = bd.multiply(new BigDecimal(10000));
						stockDaily.setOuterDisc(bd.toString());
					}else if(data.contains("手")){
						BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("手")));
						bd = bd.divide(new BigDecimal(10000));
						stockDaily.setOuterDisc(bd.toString());
					}else {
						BigDecimal bd = new BigDecimal(data);
						bd = bd.divide(new BigDecimal(10000));
						stockDaily.setOuterDisc(bd.toString());
					}
				}else if (title.contains("振幅")) {
					if(data.contains("%")) {
						BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("%")));
						bd = bd.divide(new BigDecimal(100));
						stockDaily.setSwing(bd.toString());
					}else {
						stockDaily.setSwing(data);
					}
				}else if (title.contains("量比")) {
					stockDaily.setLmr(data);
				}else if (title.contains("总市值")) {
					if(data.contains("万")) {
						BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("万")));
						stockDaily.setMarketCap(bd.toString());
					}else if (data.contains("亿")) {
						BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("亿")));
						bd = bd.multiply(new BigDecimal(10000));
						stockDaily.setMarketCap(bd.toString());
					}else {
						BigDecimal bd = new BigDecimal(data);
						bd = bd.divide(new BigDecimal(10000));
						stockDaily.setMarketCap(bd.toString());
					}
					
				}else if (title.contains("市净率")) {
					stockDaily.setPbRatio(data);
				}else if (title.contains("每股净资产")) {
					stockDaily.setAssetPerStock(data);
				}else if (title.contains("流通股本")) {
					if(data.contains("万")) {
						BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("万")));
						stockDaily.setFlowOfEquity(bd.toString());
					}else if (data.contains("亿")) {
						BigDecimal bd = new BigDecimal(data.substring(0, data.indexOf("亿")));
						bd = bd.multiply(new BigDecimal(10000));
						stockDaily.setFlowOfEquity(bd.toString());
					}else {
						BigDecimal bd = new BigDecimal(data);
						bd = bd.divide(new BigDecimal(10000));
						stockDaily.setFlowOfEquity(bd.toString());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(stock.getStockCode()+"处理出错");
		}
		return stockDaily;
	}
}
