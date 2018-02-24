package myWeb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.service.StockService;


public class StockTest extends JUnitTest{
	@Autowired
	private StockService stockService;
	
	@Test
	@Transactional
	@Rollback(false)
	public void test() {
		ArrayList<Stock> list = new ArrayList<Stock>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(
					new File("C:\\Users\\champagne\\Desktop\\a股.txt")),"GBK"));
			while(br.read()!=-1) {
				String line = br.readLine();
				String stockCode ;
				if(line.indexOf("sh") != -1) {
					stockCode= line.substring(line.indexOf("sh")+2, line.indexOf(".html")).trim();
				}else {
					stockCode = line.substring(line.indexOf("sz")+2, line.indexOf(".html")).trim();
				}
				String stockName = line.substring(line.indexOf("#")+1,line.length()).trim();
				String url = line.substring(line.indexOf("'")+1,line.indexOf("',")).trim();
				Stock stock = new Stock();
				stock.setStockCode(stockCode);
				stock.setStockName(stockName);
				stock.setUrl(url);
				list.add(stock);
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int cnt = stockService.insertStockList(list);
		System.out.println("处理完成，共插入"+cnt+"条数据");
	}
	
//	@Test
//	@Transactional
//	@Rollback(false)
	public void getStockList() {
		ArrayList<Stock> stocklist = (ArrayList<Stock>) stockService.getStockList();
		Stock stock = stocklist.get(0);
		for(StockDaily stockDaily: stock.getStockDailyList()) {
			System.out.println(stockDaily.getOpenToday());
		}
			
	}
}
