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
import com.jhyarrow.myWeb.domain.support.SupportGoldenNeedle;
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
	public void testGoldenNeedle() {
		ArrayList<Stock> stockList = (ArrayList<Stock>) stockMapper.getStockListSh();
		for(int i=0;i<stockList.size();i++) {
			Stock sd = stockList.get(i);
			String stockCode = sd.getStockCode();
			supportService.updateGoldenNeedle(stockCode);
		}
		
		stockList = (ArrayList<Stock>) stockMapper.getStockListSz();
		for(int i=0;i<stockList.size();i++) {
			Stock sd = stockList.get(i);
			String stockCode = sd.getStockCode();
			supportService.updateGoldenNeedle(stockCode);
		}
		
		stockList = (ArrayList<Stock>) stockMapper.getStockListCy();
		for(int i=0;i<stockList.size();i++) {
			Stock sd = stockList.get(i);
			String stockCode = sd.getStockCode();
			supportService.updateGoldenNeedle(stockCode);
		}
	}
	
//	@Test
//	@Transactional
//	@Rollback(false)	
	public void test() {
		ArrayList<SupportGoldenNeedle> list = supportMapper.getSupportGoldenNeedleList();
		BigDecimal ansA = new BigDecimal(0);
		BigDecimal ansB = new BigDecimal(0);
		int cnta = 0;
		int cntb = 0;
		for(int i=0;i<list.size();i++) {
			SupportGoldenNeedle sgn = list.get(i);
			StockDaily sd = new StockDaily();
			sd.setStockCode(sgn.getStockCode());
			sd.setTradeDay(sgn.getTradeDay());
			sd = stockMapper.getStockDaily(sd);
			BigDecimal upPer = new BigDecimal(sd.getUpPer());
			
			BigDecimal upPer1 = new BigDecimal(sgn.getUpPer1());
			if(upPer1.compareTo(new BigDecimal(0)) > 0) {
				ansA = ansA.add(upPer.abs());
				cnta ++;
			}else if(upPer1.compareTo(new BigDecimal(0)) < 0){
				ansB = ansB.add(upPer.abs());
				cntb ++;
			}
		}
		System.out.print(ansA.divide(new BigDecimal(cnta),4,BigDecimal.ROUND_HALF_UP));
		System.out.print(ansB.divide(new BigDecimal(cntb),4,BigDecimal.ROUND_HALF_UP));
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
