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
import com.jhyarrow.myWeb.domain.support.MacdGoldenCross;
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
	public void testAddMacdGoldenCross() {
		ArrayList<Stock> stockList = (ArrayList<Stock>) stockMapper.getStockListSh();
		for(int i=0;i<stockList.size();i++) {
			Stock sd = stockList.get(i);
			String stockCode = sd.getStockCode();
			supportService.addMacdGoldenCross(stockCode);
		}
		
		stockList = (ArrayList<Stock>) stockMapper.getStockListSz();
		for(int i=0;i<stockList.size();i++) {
			Stock sd = stockList.get(i);
			String stockCode = sd.getStockCode();
			supportService.addMacdGoldenCross(stockCode);
		}
		
		stockList = (ArrayList<Stock>) stockMapper.getStockListCy();
		for(int i=0;i<stockList.size();i++) {
			Stock sd = stockList.get(i);
			String stockCode = sd.getStockCode();
			supportService.addMacdGoldenCross(stockCode);
		}
	}

//	@Test
//	@Transactional
//	@Rollback(false)
	public void testUpdateMacdGoldenCross() {
		ArrayList<Stock> stockList = (ArrayList<Stock>) stockMapper.getStockListSh();
		for(int i=0;i<stockList.size();i++) {
			Stock sd = stockList.get(i);
			String stockCode = sd.getStockCode();
			supportService.updateMacdGoldenCross(stockCode);
		}
		
		stockList = (ArrayList<Stock>) stockMapper.getStockListSz();
		for(int i=0;i<stockList.size();i++) {
			Stock sd = stockList.get(i);
			String stockCode = sd.getStockCode();
			supportService.updateMacdGoldenCross(stockCode);
		}
		
		stockList = (ArrayList<Stock>) stockMapper.getStockListCy();
		for(int i=0;i<stockList.size();i++) {
			Stock sd = stockList.get(i);
			String stockCode = sd.getStockCode();
			supportService.updateMacdGoldenCross(stockCode);
		}
	}
	
//	@Test
//	@Transactional
//	@Rollback(false)	
	public void test() {
		ArrayList<SupportGoldenNeedle> list = supportMapper.getSupportGoldenNeedleList();
		for(int i=0;i<list.size();i++) {
			SupportGoldenNeedle sgn = list.get(i);
			StockDaily sd = stockMapper.getStockDaily(sgn.getStockCode(),sgn.getTradeDay()+1);
			if(sd != null) {
				sgn.setMaxDay1(sd.getHighest());
				sgn.setMinDay1(sd.getLowest());
				sgn.setCloseDay1(sd.getCloseToday());
				sgn.setUpPer1(sd.getUpPer());
			}
			sd = stockMapper.getStockDaily(sgn.getStockCode(),sgn.getTradeDay()+3);
			if(sd != null) {
				sgn.setMaxDay3(sd.getHighest());
				sgn.setMinDay3(sd.getLowest());
				sgn.setCloseDay3(sd.getCloseToday());
				sgn.setUpPer3(sd.getUpPer());
			}
			sd = stockMapper.getStockDaily(sgn.getStockCode(),sgn.getTradeDay()+5);
			if(sd != null) {
				sgn.setMaxDay5(sd.getHighest());
				sgn.setMinDay5(sd.getLowest());
				sgn.setCloseDay5(sd.getCloseToday());
				sgn.setUpPer5(sd.getUpPer());
			}
			supportMapper.updateSupportGoldenNeedle(sgn);
		}
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
	
//	@Test
//	@Transactional
//	@Rollback(false)
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
	@Test
	@Transactional
	@Rollback(false)	
	public void getKDJ() {
		ArrayList<Stock> stockList = (ArrayList<Stock>) stockMapper.getStockListSh();
		for(int i=0;i<stockList.size();i++) {
			Stock sd = stockList.get(i);
			String stockCode = sd.getStockCode();
			supportService.getKDJ(stockCode);
		}
		
		stockList = (ArrayList<Stock>) stockMapper.getStockListSz();
		for(int i=0;i<stockList.size();i++) {
			Stock sd = stockList.get(i);
			String stockCode = sd.getStockCode();
			supportService.getKDJ(stockCode);
		}
		
		stockList = (ArrayList<Stock>) stockMapper.getStockListCy();
		for(int i=0;i<stockList.size();i++) {
			Stock sd = stockList.get(i);
			String stockCode = sd.getStockCode();
			supportService.getKDJ(stockCode);
		}
		
		stockList = (ArrayList<Stock>) stockMapper.getStockListOther();
		for(int i=0;i<stockList.size();i++) {
			Stock sd = stockList.get(i);
			String stockCode = sd.getStockCode();
			supportService.getKDJ(stockCode);
		}
	}
}
