package myWeb;

import java.io.IOException;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
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
import com.jhyarrow.myWeb.mapper.StockMapper;
import com.jhyarrow.myWeb.service.SpiderService;
import com.jhyarrow.myWeb.service.SupportService;

public class SpiderTest extends JUnitTest {
	@Autowired
	private StockMapper stockMapper;
	@Autowired
	private SpiderService spiderService;
	@Autowired
	private SupportService supportService;
	
	@Test
	@Transactional
	@Rollback(false)
	public void spideStockDaily() {
		try {
			spiderService.spideStockDaily("603118", "共进股份", "20180510", "20180510");
			supportService.getMACD("603118",6760);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			stockMapper.insertStockList(stockList);
			
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
	public void spideStockDailyIndex() {
		String dates[] = {"2018-05-11"};
		for(int i=0;i<dates.length;i++) {
			spiderService.spideStockIndexDaily(dates[i]);
		}
	}
	
//	@Test
//	@Transactional
//	@Rollback(false)
	public void addNewStock() throws Exception {
//		spiderService.spideStockDaily("603897", "长城科技", "20180401", "20180430");
//		spiderService.spideStockDaily("603301", "振德医疗", "20180401", "20180430");
//		spiderService.spideStockDaily("300743", "天地数码", "20180401", "20180430");
//		spiderService.spideStockDaily("603876", "鼎胜新材", "20180401", "20180430");
//		spiderService.spideStockDaily("603773", "沃格光电", "20180401", "20180430");
//		spiderService.spideStockDaily("603733", "仙鹤股份", "20180401", "20180430");
//		spiderService.spideStockDaily("603348", "文灿股份", "20180401", "20180430");
//		spiderService.spideStockDaily("603596", "伯特利", "20180401", "20180430");
		
		supportService.getMACD("603897");
		supportService.getMACD("603301");
		supportService.getMACD("300743");
		supportService.getMACD("603876");
		supportService.getMACD("603773");
		supportService.getMACD("603733");
		supportService.getMACD("603348");
		supportService.getMACD("603596");
	}
	
}
