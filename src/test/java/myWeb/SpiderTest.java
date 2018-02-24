package myWeb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.domain.StockIndex;
import com.jhyarrow.myWeb.domain.StockIndexDaily;
import com.jhyarrow.myWeb.service.StockService;
import com.jhyarrow.myWeb.service.SupportService;
import com.jhyarrow.myWeb.thread.StockIndexSpideThread;
import com.jhyarrow.myWeb.thread.StockSpideThread;

public class SpiderTest extends JUnitTest {
	@Autowired
	private StockService stockService;
	@Autowired
	private SupportService supportService;
	
//	@Test
//	@Transactional
//	@Rollback(false)
	public void test() {
		long start = System.currentTimeMillis();
		ArrayList<Stock> list = (ArrayList<Stock>) stockService.getStockList();
		ExecutorService exec = Executors.newFixedThreadPool(20);
		ArrayList<Future<StockDaily>> results = new ArrayList<Future<StockDaily>>();
		int tradeDay = stockService.getMaxTradeDay();
		for(int i=0;i<list.size();i++) {
			Stock stock = list.get(i);
			try {
				results.add(exec.submit(new StockSpideThread(stock,tradeDay+1)));  
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			for(Future<StockDaily> fs :results) {
				stockService.addStockDaily(fs.get());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
	
//	@Test
//	@Transactional
//	@Rollback(false)
	public void noahArk() {
		try {
//			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
//			String date = df.format(System.currentTimeMillis());
			String date = "2018-02-21";
			ArrayList<StockDaily> lists = stockService.getStockListByDay(date);
			for(StockDaily stock :lists) {
				supportService.goldNeedle(stock);
			}
			System.out.println("处理完成");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test
//	@Transactional
//	@Rollback(false)
	public void errorStockHandle() {
		Stock stock = new Stock();
		stock.setStockCode("600258");
		stock.setStockName("首旅酒店");
		stock.setUrl("https://gupiao.baidu.com/stock/sh600258.html");
		stockService.spiderStock(stock,34);
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void spideIndex() {
		long end = System.currentTimeMillis();
		ArrayList<StockIndex> indexList = (ArrayList<StockIndex>) stockService.getStockIndexList();
		ExecutorService exec = Executors.newFixedThreadPool(20);
		ArrayList<Future<StockIndexDaily>> indexResults = new ArrayList<Future<StockIndexDaily>>();
		int tradeDay = stockService.getMaxTradeDay();
		for(int i=0;i<indexList.size();i++) {
			StockIndex stockIndex = indexList.get(i);
			try {
				indexResults.add(exec.submit(new StockIndexSpideThread(stockIndex,tradeDay+1)));  
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			for(Future<StockIndexDaily> fs :indexResults) {
				stockService.addStockIndexDaily(fs.get());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		long end2 = System.currentTimeMillis();
		System.out.println("处理完成，共处理"+indexResults.size()+"条数据，用时:"+String.valueOf((end2-end)/1000)+"秒");
	}
}
