package myWeb;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
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

import com.jhyarrow.myWeb.domain.HgtStockDaily;
import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.mapper.StockMapper;
import com.jhyarrow.myWeb.mapper.SupportMapper;
import com.jhyarrow.myWeb.mapper.TradeDayMapper;
import com.jhyarrow.myWeb.service.StockService;

public class HgtTest extends JUnitTest{
	@Autowired
	private TradeDayMapper tradeDayMapper;
	@Autowired
	private SupportMapper supportMapper;
	@Autowired
	private StockMapper stockMapper;
	@Autowired
	private StockService stockService;
	
//	@Test
//	@Transactional
//	@Rollback(false)
	public void test(){
		try {
			for(int i=5980;i<=6813;i++) {
				String date = tradeDayMapper.getTradeDayByTradeDay(i);
				String url = "http://data.eastmoney.com/hsgt/top10/"+date+".html";
				HttpClient httpCient = HttpClients.createDefault();
				HttpResponse httpResponse;
				HttpGet httpGet = new HttpGet(url);
				httpResponse = httpCient.execute(httpGet);
				HttpEntity httpEntity = httpResponse.getEntity();
				String response = EntityUtils.toString(httpEntity,"utf-8");
				Document doc = Jsoup.parse(response);
				Element table = doc.select("table[class=tab1]").get(0);
				Elements rows = table.select("tbody").select("tr");
				for(int j=0;j<rows.size();j++) {
					Elements tds = rows.get(j).select("td");
					if(tds.size()<4) {
						continue;
					}
					HgtStockDaily hgtStockDaily = new HgtStockDaily();
					hgtStockDaily.setTradeDay(i);
					hgtStockDaily.setStockCode(tds.get(1).select("a").text().trim());
					hgtStockDaily.setStockName(tds.get(2).select("a").text().trim());
					hgtStockDaily.setCloseToday(tds.get(4).select("span").text().trim());
					String jingAmt = tds.get(6).select("span").text().trim();
					if(jingAmt.indexOf("亿")!=-1) {
						jingAmt = jingAmt.replace("亿", "");
						BigDecimal bd = new BigDecimal(jingAmt).multiply(new BigDecimal(10000));
						hgtStockDaily.setJingAmt(bd.floatValue());
					}else if (jingAmt.indexOf("万")!=-1) {
						jingAmt = jingAmt.replace("万", "");
						BigDecimal bd = new BigDecimal(jingAmt);
						hgtStockDaily.setJingAmt(bd.floatValue());
					}
					
					String buyAmt = tds.get(7).select("span").text().trim();
					if(buyAmt.indexOf("亿")!=-1) {
						buyAmt = buyAmt.replace("亿", "");
						BigDecimal bd = new BigDecimal(buyAmt).multiply(new BigDecimal(10000));
						hgtStockDaily.setBuyAmt(bd.floatValue());
					}else if (buyAmt.indexOf("万")!=-1) {
						buyAmt = buyAmt.replace("万", "");
						BigDecimal bd = new BigDecimal(buyAmt);
						hgtStockDaily.setBuyAmt(bd.floatValue());
					}
					
					String sellAmt = tds.get(8).select("span").text().trim();
					if(sellAmt.indexOf("亿")!=-1) {
						sellAmt = sellAmt.replace("亿", "");
						BigDecimal bd = new BigDecimal(sellAmt).multiply(new BigDecimal(10000));
						hgtStockDaily.setSellAmt(bd.floatValue());
					}else if (sellAmt.indexOf("万")!=-1) {
						sellAmt = sellAmt.replace("万", "");
						BigDecimal bd = new BigDecimal(sellAmt);
						hgtStockDaily.setSellAmt(bd.floatValue());
					}
					
					String totalAmt = tds.get(9).select("span").text().trim();
					if(totalAmt.indexOf("亿")!=-1) {
						totalAmt = totalAmt.replace("亿", "");
						BigDecimal bd = new BigDecimal(totalAmt).multiply(new BigDecimal(10000));
						hgtStockDaily.setTotalAmt(bd.floatValue());
					}else if (totalAmt.indexOf("万")!=-1) {
						totalAmt = totalAmt.replace("万", "");
						BigDecimal bd = new BigDecimal(totalAmt);
						hgtStockDaily.setTotalAmt(bd.floatValue());
					}
					supportMapper.addHgtStockDaily(hgtStockDaily);
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Transactional
	@Rollback(false)
	public void check() {
		try {
			String url = "http://localhost:8080/myWeb/getBlogList.action?page=1";
			HttpClient httpCient = HttpClients.createDefault();
			HttpResponse httpResponse;
			HttpGet httpGet = new HttpGet(url);
			httpResponse = httpCient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			String response = EntityUtils.toString(httpEntity,"utf-8");
			System.out.println(response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
}
