package myWeb;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.mapper.StockMapper;
import com.jhyarrow.myWeb.service.StockService;
import com.jhyarrow.myWeb.service.SupportService;

public class SupportTest extends JUnitTest {
	@Autowired
	private SupportService supportService;
	@Autowired
	private StockService stockService;
	@Autowired
	private StockMapper stockMapper;
	
	
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
	
//	@Test
//	@Transactional
//	@Rollback(false)
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
	
	@Test
	@Transactional
	@Rollback(false)	
	public void test() {
		ArrayList<Stock> stockList = (ArrayList<Stock>) stockMapper.getStockList();
		for(int i=0;i<stockList.size();i++) {
			Stock s = stockList.get(i);
			String code = s.getStockCode();
			ArrayList<StockDaily> list = (ArrayList<StockDaily>)stockMapper.getStockDailyList(code);
			for(int j=0;j<list.size();j++) {
				StockDaily sd = list.get(j);
				BigDecimal up = new BigDecimal(sd.getUp());
				BigDecimal upPer = new BigDecimal(sd.getUpPer());
				if(up.compareTo(new BigDecimal(0))< 0) {
					upPer = upPer.multiply(new BigDecimal(-1));
				}
				sd.setUpPer(upPer.toString());
				upPer = upPer.multiply(new BigDecimal(100)).divide(new BigDecimal(1),0,BigDecimal.ROUND_HALF_UP);
				sd.setUpLevel(upPer.toString());
				stockMapper.updateStockDaily(sd);
			}
			System.out.println(code+"操作完成");
		}
	}
}
