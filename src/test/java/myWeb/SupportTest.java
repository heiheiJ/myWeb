package myWeb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.service.StockService;
import com.jhyarrow.myWeb.service.SupportService;

public class SupportTest extends JUnitTest {
	@Autowired
	private StockService stockService;
	@Autowired
	private SupportService supportService;
	
	
//	@Test
//	@Transactional
//	@Rollback(false)
	public void noahArk() {
		try {
			System.out.println("开始分析数据");
			long start = System.currentTimeMillis();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
			//String date = df.format(System.currentTimeMillis());
			String date = "2018-03-01";
			ArrayList<StockDaily> lists = stockService.getStockListByDay(date);
			for(StockDaily stock :lists) {
				supportService.getLine(stock);
			}
			System.out.println("处理完成");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void checkData() {
		System.out.println("开始核查数据");
		long start = System.currentTimeMillis();
		int tradeDay = stockService.getMaxTradeDay();
		tradeDay = 39;
		supportService.check(tradeDay-1);
		long end = System.currentTimeMillis();
		System.out.println("核查完成，用时:"+String.valueOf((end-start)/1000)+"秒");
	}
}
