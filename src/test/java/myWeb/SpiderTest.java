package myWeb;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.jhyarrow.myWeb.callable.StockSpideCallable;
import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.mapper.StockMapper;
import com.jhyarrow.myWeb.service.SpiderService;
import com.jhyarrow.myWeb.service.StockService;
import com.jhyarrow.myWeb.service.SupportService;
import com.jhyarrow.myWeb.thread.StockSpideThread;

public class SpiderTest extends JUnitTest {
	@Autowired
	private StockMapper stockMapper;
	@Autowired
	private SpiderService spiderService;
	@Autowired
	private SupportService supportService;
	@Autowired
	private StockService stockService;
	
	
	@Test
	@Transactional
	@Rollback(false)
	public void addNewStock() throws Exception {
		long start = System.currentTimeMillis();
		ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		ArrayList<Stock> stockList = stockService.getStockList();
		CountDownLatch countDownLatch  =  new  CountDownLatch (stockList.size());
		for(int i=0;i<stockList.size();i++) {
			Stock stock = stockList.get(i);
			StockSpideThread sst = new StockSpideThread(spiderService, stock,"2018-07-30",countDownLatch);
			executor.execute(sst);
		}
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println((end-start)/1000 + "秒");
	}
	
//	@Test
//	@Transactional
//	@Rollback(false)
	public void spiderStockDailyFuture() {
		long start = System.currentTimeMillis();
		ArrayList<Stock> stockList = stockService.getStockList();
		ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		ArrayList<FutureTask<Boolean>> futureList = new ArrayList<FutureTask<Boolean>>();
		for(int i=0;i<stockList.size();i++) {
			Stock stock = stockList.get(i);
			FutureTask<Boolean> future = new FutureTask<Boolean>(new StockSpideCallable(spiderService, stock, "2018-07-30"));
			futureList.add(future);
			executor.submit(future);
		}
		
		try {
			for(int i=0;i<futureList.size();i++) {
				FutureTask<Boolean> future = futureList.get(i);
				if(!future.get()) {
					System.out.println("操作失败");
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println((end-start)/1000 + "秒");
	}
}
	
