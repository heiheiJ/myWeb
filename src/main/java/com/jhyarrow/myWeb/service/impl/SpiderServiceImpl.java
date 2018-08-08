package com.jhyarrow.myWeb.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.jhyarrow.myWeb.domain.SpiderStockDailyAllError;
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
	
	//获取所有股票
	public void spideStock() throws Exception{
		//清库
		stockMapper.deleteStock();
		
		//获取数据
		String url = "http://www.yz21.org/stock/info/";
		HttpClient httpCient = HttpClients.createDefault();
		HttpResponse httpResponse;
		ArrayList<Stock> stockList = new ArrayList<Stock>();
		HttpGet httpGet = new HttpGet(url);
		httpResponse = httpCient.execute(httpGet);
		HttpEntity httpEntity = httpResponse.getEntity();
		String response = EntityUtils.toString(httpEntity,"utf-8");
		Document doc = Jsoup.parse(response);
		Element table = doc.select("table[class=stockBlock]").get(0);
		Elements rows = table.select("tr");
		for(int i=1;i<rows.size();i++) {
			Elements tds = rows.get(i).select("td");
			Stock stock = new Stock();
			stock.setStockCode(tds.get(1).select("a").text().trim());
			stock.setStockName(tds.get(2).select("a").text().trim());
			stock.setComName(tds.get(3).select("a").text().trim());
			stockList.add(stock);
		}
			
		for(int k=2;k<=184;k++) {
			httpGet = new HttpGet(url + "stocklist_"+k+".html");
			httpResponse = httpCient.execute(httpGet);
			httpEntity = httpResponse.getEntity();
			response = EntityUtils.toString(httpEntity,"utf-8");
			doc = Jsoup.parse(response);
			table = doc.select("table[class=stockBlock]").get(0);
			rows = table.select("tr");
			for(int i=1;i<rows.size();i++) {
				Elements tds = rows.get(i).select("td");
				Stock stock = new Stock();
				stock.setStockCode(tds.get(1).select("a").text().trim());
				stock.setStockName(tds.get(2).select("a").text().trim());
				stock.setComName(tds.get(3).select("a").text().trim());
				if(stock.getStockCode().startsWith("900") || stock.getStockCode().startsWith("200")) {
					continue;
				}
				stockList.add(stock);
			}
		}
		stockMapper.insertStockList(stockList);
	}
	
	//获取股票数据
	public void spideStockDaily(String code,String stockName,String start,String end) {
		start = start.replaceAll("-", "");
		end = end.replaceAll("-", "");
		String url = "http://q.stock.sohu.com/hisHq?code=cn_"+code+"&start="+start+"&end="+end+"&stat=1&order=D&period=d"
				+ "&callback=historySearchHandler&rt=json&r=0.8391495715053367&0.9677250558488026";
		
		HttpPost httpPost = new HttpPost(url);
		HttpClient httpCient = HttpClients.createDefault();
		HttpResponse httpResponse;
		String response = "" ;
		String date = "";
		try {
			httpResponse = httpCient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			response = EntityUtils.toString(httpEntity,"utf-8");
			if("{}\n".equals(response)) {//停牌返回为空
				return;
			}
			String status = "";
			if(response.contains("status")) {
				status = response.substring(response.indexOf("status")+8,response.indexOf(","));
			}
			if(!"0".equals(status)) {
				return;
			}
			response = response.substring(response.indexOf("[[")+1, response.indexOf("]]")+1);
			String stockList[] = response.split("],");
			for(int i=0;i<stockList.length;i++) {
				String datas[] = stockList[i].replaceAll("\\[", "").replaceAll("\\]","").replaceAll("\"","").split(",");
				StockDaily sd = new StockDaily();
				sd.setStockCode(code);
				sd.setStockName(stockName);
				date = datas[0];
				sd.setDate(date);
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
				BigDecimal upPerBd = new BigDecimal(upPer).multiply(new BigDecimal(100)).divide(new BigDecimal(1),0,BigDecimal.ROUND_HALF_UP);
				sd.setUpLevel(upPerBd.toString());
				stockMapper.addStockDaily(sd);
			}
		} catch (Exception e) {
			SpiderStockDailyAllError ssde = new SpiderStockDailyAllError();
			ssde.setStockCode(code);
			ssde.setStockName(stockName);
			if(response.length() > 4000)
				response = response.substring(0,4000);
			ssde.setResponse(response);
			addSpiderStockDailyAllError(ssde);
			System.out.println(code+"处理错误");
			e.printStackTrace();
		}
	}	

	//获取股票指数数据
	public void spideStockIndexDaily(String start,String end) {
		start = start.replaceAll("-", "");
		end = end.replaceAll("-", "");
		ArrayList<StockIndex> list = (ArrayList<StockIndex>) stockIndexMapper.getStockIndexList();
		for(int i=0;i<list.size();i++) {
			StockIndex stockIndex = list.get(i);
			String stockCode = stockIndex.getStockCode();
			String stockName = stockIndex.getStockName();
			String url = "http://q.stock.sohu.com/hisHq?code=zs_"+stockCode+"&start="+start+"&end="+end+"&stat=1&order=D&period=d"
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
				for(int j=0;j<stockList.length;j++) {
					String datas[] = stockList[j].replaceAll("\\[", "").replaceAll("\\]","").replaceAll("\"","").split(",");
					StockIndexDaily sd = new StockIndexDaily();
					sd.setStockCode(stockCode);
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
					stockIndexMapper.addStockIndexDaily(sd);
				}
			} catch (Exception e) {
				System.out.println(stockName+"("+stockCode+")处理错误");
				e.printStackTrace();
			}
		}
		
	}
	

	private void addSpiderStockDailyError(SpiderStockDailyError ssde) {
		supportMapper.addSpiderStockDailyError(ssde);
	}
	
	private void addSpiderStockDailyAllError(SpiderStockDailyAllError ssdae) {
		supportMapper.addSpiderStockDailyAllError(ssdae);
	}

}
