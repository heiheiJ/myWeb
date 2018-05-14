package myWeb;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.mail.MessagingException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.domain.support.SupportGoldenNeedle;
import com.jhyarrow.myWeb.mapper.StockMapper;
import com.jhyarrow.myWeb.mapper.SupportMapper;
import com.jhyarrow.myWeb.service.SupportService;
import com.jhyarrow.myWeb.service.TradeDayService;
import com.jhyarrow.myWeb.util.MailUtil;

public class SupportTest extends JUnitTest {
	@Autowired
	private SupportMapper supportMapper;
	@Autowired
	private StockMapper stockMapper;
	@Autowired
	private SupportService supportService;
	@Autowired
	private TradeDayService tradeDayService;
	


//	@Test
//	@Transactional
//	@Rollback(false)
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
	
//	@Test
//	@Transactional
//	@Rollback(false)
	public void getTradeDay() {
		String date = "2018-05-02";
		String to = "632849309@qq.com";
		String subject = date+"";
		try {
			MailUtil.sendMail(to, subject,"lalala");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void testMacd() {
		ArrayList<Stock> list = (ArrayList<Stock>) stockMapper.getStockList();
		for(int i=0;i<list.size();i++) {
			Stock s = list.get(i);
			String stockCode = s.getStockCode();
			try {
				supportService.getMACD(stockCode);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(stockCode+"操作完成");
		}
	}
}
