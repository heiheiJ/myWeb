package myWeb;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.util.Args;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.service.StockService;
import com.jhyarrow.myWeb.service.SupportService;
import com.jhyarrow.myWeb.thread.MacdThread;

public class SupportTest extends JUnitTest {
	@Autowired
	private SupportService supportService;
	@Autowired
	private StockService stockService;
	
	
//	@Test
//	@Transactional
//	@Rollback(false)
	public void caculatorAvgDayNew(String stockCode) {
		try {
			long start2 = System.currentTimeMillis();
			supportService.getAvgStatusNew(stockCode);
			long end2 = System.currentTimeMillis();
			System.out.println(stockCode+"共耗时"+ (end2-start2)/1000+"秒");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void caculator() throws Exception {
		long start = System.currentTimeMillis();
		ArrayList<Stock> list = (ArrayList<Stock>) stockService.getStockList();
		for(int i=0;i<list.size();i++) {
			String stockCode = list.get(i).getStockCode();
			long start2 = System.currentTimeMillis();
			supportService.getAvgStatusNew(stockCode);
			long end2 = System.currentTimeMillis();
			System.out.println(stockCode+"共耗时"+ (end2-start2)/1000+"秒");
		}
		long end = System.currentTimeMillis();
		System.out.println("共耗时"+ (end-start)/1000+"秒");
	}
}
