package myWeb;

import java.io.IOException;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.service.StockService;

public class SpiderTest extends JUnitTest {
	@Autowired
	private StockService stockService;
	
//	@Test
//	@Transactional
//	@Rollback(false)
	public void spideStockDaily(String code) {
		Stock stock = stockService.getStockByCode(code);
		String stockName = stock.getStockName();
		String url = "http://q.stock.sohu.com/hisHq?code=cn_"+code+"&start=19700101&end=20180405&stat=1&order=D&period=d"
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
				sd.setStockCode(code);
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
			System.out.println(code+"处理完成");
		} catch (ClientProtocolException e) {
			System.out.println(code+"处理错误");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(code+"处理错误");
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println(code+"处理错误");
			e.printStackTrace();
		}
	}
	
//	@Test
//	@Transactional
//	@Rollback(false)
	public void spide() {
		ArrayList<Stock> list = (ArrayList<Stock>) stockService.getStockList();
		for(int i=0;i<list.size();i++) {
			Stock stock = list.get(i);
			String code = stock.getStockCode();
			spideStockDaily(code);
		}
	}
	
	
//	@Test
//	@Transactional
//	@Rollback(false)
	public void spideStock() {
		String url = "http://www.yz21.org/stock/info/";
		HttpClient httpCient = HttpClients.createDefault();
		HttpResponse httpResponse;
		ArrayList<Stock> stockList = new ArrayList<Stock>();
		try {
			for(int k=2;k<=182;k++) {
				HttpGet httpGet = new HttpGet(url + "stocklist_"+k+".html");
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
				System.out.println("第"+k+"页完成");
			}
			stockService.insertStockList(stockList);
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@Test
//	@Transactional
//	@Rollback(false)
	public void spideSingle() {
		spideStockDaily("000787");
	}
}
