package myWeb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.service.StockService;
import com.jhyarrow.myWeb.service.SupportService;

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
		ArrayList<Stock> list = stockService.getStockList();
		for(int i=0;i<list.size();i++) {
			Stock stock = list.get(i);
			try {
				stockService.spiderStock(stock,33);
			}catch (Exception e) {
				e.printStackTrace();
			}
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
	
	@Test
	@Transactional
	@Rollback(false)
	public void errorStockHandle() {
		Stock stock = new Stock();
		stock.setStockCode("600258");
		stock.setStockName("首旅酒店");
		stock.setUrl("https://gupiao.baidu.com/stock/sh600258.html");
		stockService.spiderStock(stock,34);
	}
}
