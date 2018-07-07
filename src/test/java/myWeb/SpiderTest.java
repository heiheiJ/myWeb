package myWeb;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.jhyarrow.myWeb.domain.SpiderStockDailyAllError;
import com.jhyarrow.myWeb.domain.SpiderStockDailyError;
import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.mapper.StockMapper;
import com.jhyarrow.myWeb.service.SpiderService;
import com.jhyarrow.myWeb.service.StockService;
import com.jhyarrow.myWeb.service.SupportService;
import com.jhyarrow.myWeb.service.TradeDayService;

public class SpiderTest extends JUnitTest {
	@Autowired
	private StockMapper stockMapper;
	@Autowired
	private SpiderService spiderService;
	@Autowired
	private SupportService supportService;
	@Autowired
	private StockService stockService;
	@Autowired
	private TradeDayService tradeDayService;
	
//	@Test
//	@Transactional
//	@Rollback(false)
	public void spideStockDaily() {
		try {
			spiderService.spideStockDaily("600031", "三一重工", "20180518", "20180518");
			supportService.getMACD("600031",6766);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@Test
//	@Transactional
//	@Rollback(false)
	public void spideStock() {
		try {
			spiderService.spideStock();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		

//	@Test
//	@Transactional
//	@Rollback(false)
	public void spideStockDailyIndex() {
		stockService.truncateStockIndexDaily();
		spiderService.spideStockIndexDaily("1990-01-01","2018-06-05");
	}
	
//	@Test
//	@Transactional
//	@Rollback(false)
	public void updateTradeDay() {
		tradeDayService.truncateTradeDay();
		tradeDayService.addTradeDayList();
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void addNewStock() throws Exception {
		supportService.get9();
		supportService.getKDJ();
	}
}
	
