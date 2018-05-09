package myWeb;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.mapper.StockMapper;
import com.jhyarrow.myWeb.mapper.SupportMapper;
import com.jhyarrow.myWeb.service.SupportService;
import com.jhyarrow.myWeb.view.StockDailyView;

public class SupportTest extends JUnitTest {
	@Autowired
	private SupportMapper supportMapper;
	@Autowired
	private StockMapper stockMapper;
	@Autowired
	private SupportService supportService;
	
	
//	@Test
//	@Transactional
//	@Rollback(false)
	public void caculator() throws Exception {
		long start = System.currentTimeMillis();
		ArrayList<Stock> list = (ArrayList<Stock>) stockMapper.getStockList();
		for(int i=0;i<list.size();i++) {
			String stockCode = list.get(i).getStockCode();
			try {
				for(int j=6744;j<=6753;j++) {
					supportService.getMACDDay(stockCode,j);
				}
			}catch (Exception e) {
				System.out.println(stockCode+"出错");
				e.printStackTrace();
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("共耗时"+ (end-start)/1000+"秒");
	}
	
	@Test
	@Transactional
	@Rollback(false)	
	public void test() {
		String date = "2016-08-25";
		ArrayList<StockDailyView> sdList = supportService.getGoldenNeedle(date);
		Gson gson = new Gson();
		String jsonString = gson.toJson(sdList);
		System.out.println(jsonString);

	}
	
//	@Test
//	@Transactional
//	@Rollback(false)	
	public void testHeihei() {
		ArrayList<Stock> stockList = (ArrayList<Stock>) stockMapper.getStockList();
		for(int i=0;i<stockList.size();i++) {
			Stock s = stockList.get(i);
			String code = s.getStockCode();
			ArrayList<StockDaily> list = (ArrayList<StockDaily>)stockMapper.getStockDailyList(code);
			for(int j=0;j<list.size();j++) {
				StockDaily sd = list.get(j);
				BigDecimal upPer = new BigDecimal(sd.getUpPer());
				upPer = upPer.divide(new BigDecimal(1),0,BigDecimal.ROUND_HALF_UP);
				sd.setUpLevel(upPer.toString());
			}
		}
	}
}
