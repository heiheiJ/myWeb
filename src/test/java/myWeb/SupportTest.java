package myWeb;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.jhyarrow.myWeb.domain.Line;
import com.jhyarrow.myWeb.domain.Line5;
import com.jhyarrow.myWeb.domain.Line5Conclusion;
import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.domain.support.SupportKdj;
import com.jhyarrow.myWeb.domain.support.SupportMacd;
import com.jhyarrow.myWeb.mapper.StockMapper;
import com.jhyarrow.myWeb.mapper.SupportMapper;
import com.jhyarrow.myWeb.service.StockService;
import com.jhyarrow.myWeb.service.SupportService;

public class SupportTest extends JUnitTest {
	@Autowired
	private SupportMapper supportMapper;
	@Autowired
	private StockMapper stockMapper;
	@Autowired
	private SupportService supportService;
	@Autowired
	private StockService stockService;
	
//	@Test
//	@Transactional
//	@Rollback(false)
	public void testSupportMacd() {
		ArrayList<Stock> stockList = stockMapper.getStockList();
		for(int i=0;i<stockList.size();i++) {
			Stock s = stockList.get(i);
			String stockCode = s.getStockCode();
			ArrayList<StockDaily> stockDailyList = stockMapper.getStockDailyList(stockCode);
			try {
				boolean flag = false;//true涓婃定锛宖alse涓嬭穼
				SupportMacd sm = new SupportMacd();
				for(int j=1;j<stockDailyList.size();j++) {
					StockDaily sd = stockDailyList.get(j);
					StockDaily sdYesterday = stockDailyList.get(j-1);
					BigDecimal bar = new BigDecimal(sd.getBar());
					BigDecimal barYesterday = new BigDecimal(sdYesterday.getBar());
					if(bar.compareTo(barYesterday) >0 && !flag) {
						if(!flag) {
							sm.setStockCode(sd.getStockCode());
							sm.setStockName(sd.getStockName());
							sm.setStartDay(sd.getTradeDay());
							sm.setStartPrice(new BigDecimal(sd.getCloseToday()).floatValue());
							sm.setStartBar(new BigDecimal(sd.getBar()).floatValue());
						}
						flag = true;
					}else if(flag) {
						BigDecimal upPerToday = new BigDecimal(sd.getUpPer());
						if(upPerToday.compareTo(new BigDecimal((0))) < 0){
							sm.setEndDay(sd.getTradeDay());
							sm.setEndPrice(new BigDecimal(sd.getCloseToday()).floatValue());
							BigDecimal upPer = (new BigDecimal(sm.getEndPrice()).subtract(new BigDecimal(sm.getStartPrice())))
									.divide(new BigDecimal(sm.getStartPrice()),4,BigDecimal.ROUND_HALF_UP);
							sm.setUpPer(upPer.floatValue());
							supportMapper.addSupportMacd(sm);
							flag = false;
						}
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println(stockCode+"澶辫触");
			}
			
		}
	}
	
//	@Test
//	@Transactional
//	@Rollback(false)
	public void testSupportKdj() {
		ArrayList<Stock> stockList = stockMapper.getStockList();
		for(int i=0;i<stockList.size();i++) {
			Stock s = stockList.get(i);
			String stockCode = s.getStockCode();
			ArrayList<StockDaily> stockDailyList = stockMapper.getStockDailyList(stockCode);
			try {
				boolean flag = false;
				SupportKdj sk = new SupportKdj();
				for(int j=1;j<stockDailyList.size();j++) {
					StockDaily sd = stockDailyList.get(j);
					StockDaily sdYesterday = stockDailyList.get(j-1);
					BigDecimal J = new BigDecimal(sd.getJ());
					BigDecimal JYesterday = new BigDecimal(sdYesterday.getJ());
					if(!flag && JYesterday.compareTo(new BigDecimal(0)) <=0 && J.compareTo(new BigDecimal(0))>0) {
						sk.setStockCode(sd.getStockCode());
						sk.setStockName(sd.getStockName());
						sk.setStartDay(sd.getTradeDay());
						sk.setStartPrice(new BigDecimal(sd.getCloseToday()).floatValue());
						sk.setStartBar(new BigDecimal(sd.getBar()).floatValue());
						flag = true;
					}
					if(flag && JYesterday.compareTo(J)>0) {
						if(J.compareTo(new BigDecimal(100)) > 0) {
							continue;
						}else {
							sk.setEndDay(sd.getTradeDay());
							sk.setEndPrice(new BigDecimal(sd.getCloseToday()).floatValue());
							BigDecimal upPer = (new BigDecimal(sk.getEndPrice()).subtract(new BigDecimal(sk.getStartPrice())))
									.divide(new BigDecimal(sk.getStartPrice()),4,BigDecimal.ROUND_HALF_UP);
							sk.setUpPer(upPer.floatValue());
							supportMapper.addSupportKdj(sk);
							flag = false;
						}
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println(stockCode+"澶辫触");
			}
		}
	}
	
//	@Test
//	@Transactional
//	@Rollback(false)
	public void testLine() {
		ArrayList<Stock> stockList = stockMapper.getStockList();
		for(int i=0;i<stockList.size();i++) {
			Stock s = stockList.get(i);
			String stockCode = s.getStockCode();
			ArrayList<StockDaily> stockDailyList = stockMapper.getStockDailyList(stockCode);
			try {
				boolean flag = true;//true涓婃定false涓嬭穼
				Line line = new Line();
				line.setStockCode(stockCode);
				line.setStartDay(stockDailyList.get(0).getTradeDay());
				line.setStartPrice(new BigDecimal(stockDailyList.get(0).getCloseToday()).floatValue());
				for(int j=0;j<stockDailyList.size()-1;j++) {
					String closeTodayYesterDay = stockDailyList.get(j).getCloseToday();
					String closeToday = stockDailyList.get(j+1).getCloseToday();
					if(new BigDecimal(closeTodayYesterDay).compareTo(new BigDecimal(closeToday)) > 0 && flag) {
						line.setEndDay(stockDailyList.get(j).getTradeDay());
						BigDecimal endPrice = new BigDecimal(closeTodayYesterDay);
						BigDecimal startPrice = new BigDecimal(line.getStartPrice());
						line.setEndPrice(endPrice.floatValue());
						line.setUpPer(endPrice.subtract(startPrice).divide(startPrice,4,BigDecimal.ROUND_HALF_UP).floatValue());
						supportMapper.addLine(line);
						line.setStartDay(stockDailyList.get(j).getTradeDay());
						line.setStartPrice(new BigDecimal(stockDailyList.get(j+1).getCloseToday()).floatValue());
						flag = false;
					}
					if(new BigDecimal(closeTodayYesterDay).compareTo(new BigDecimal(closeToday)) < 0 && !flag) {
						line.setEndDay(stockDailyList.get(j).getTradeDay());
						BigDecimal endPrice = new BigDecimal(closeTodayYesterDay);
						BigDecimal startPrice = new BigDecimal(line.getStartPrice());
						line.setEndPrice(endPrice.floatValue());
						line.setUpPer(endPrice.subtract(startPrice).divide(startPrice,4,BigDecimal.ROUND_HALF_UP).floatValue());
						supportMapper.addLine(line);
						line.setStartDay(stockDailyList.get(j+1).getTradeDay());
						line.setStartPrice(new BigDecimal(stockDailyList.get(j+1).getCloseToday()).floatValue());
						flag = true;
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println(stockCode+"澶辫触");
			}
		}
	}
	
//	@Test
//	@Transactional
//	@Rollback(false)
	public void addLine5() {
		ArrayList<Stock> stockList = stockMapper.getStockList();
		for(int i=0;i<stockList.size();i++) {
			Stock s = stockList.get(i);
			ArrayList<StockDaily> stockDailyList = stockMapper.getStockDailyList(s.getStockCode());
			if(stockDailyList.size() <6) {
				continue;
			}
			for(int j=0;j<stockDailyList.size()-6;j++) {
				StockDaily sd1 = stockDailyList.get(j);
				StockDaily sd2 = stockDailyList.get(j+1);
				StockDaily sd3 = stockDailyList.get(j+2);
				StockDaily sd4 = stockDailyList.get(j+3);
				StockDaily sd5 = stockDailyList.get(j+4);
				StockDaily sd6 = stockDailyList.get(j+5);
				Line5 line5 = new Line5();
				line5.setDay1(new BigDecimal(sd1.getUpPer()).multiply(new BigDecimal(100))
						.divide(new BigDecimal(1),BigDecimal.ROUND_HALF_UP).intValue());
				line5.setDay2(new BigDecimal(sd2.getUpPer()).multiply(new BigDecimal(100))
						.divide(new BigDecimal(1),BigDecimal.ROUND_HALF_UP).intValue());
				line5.setDay3(new BigDecimal(sd3.getUpPer()).multiply(new BigDecimal(100))
						.divide(new BigDecimal(1),BigDecimal.ROUND_HALF_UP).intValue());
				line5.setDay4(new BigDecimal(sd4.getUpPer()).multiply(new BigDecimal(100))
						.divide(new BigDecimal(1),BigDecimal.ROUND_HALF_UP).intValue());
				line5.setDay5(new BigDecimal(sd5.getUpPer()).multiply(new BigDecimal(100))
						.divide(new BigDecimal(1),BigDecimal.ROUND_HALF_UP).intValue());
				line5.setAns(new BigDecimal(sd6.getUpPer()).multiply(new BigDecimal(100)).floatValue());
				line5.setStockCode(sd1.getStockCode());
				line5.setDate(sd1.getDate());
				supportMapper.addLine5(line5);
			}
		}
	}
	
//	@Test
//	@Transactional
//	@Rollback(false)
	public void handleLine5() {
		ArrayList<Line5Conclusion> list = supportMapper.getLine5ConclusionList();
		for(int i=0;i<list.size();i++) {
			Line5Conclusion line5Conclusion = list.get(i);
			Integer day1 = line5Conclusion.getDay1();
			Integer day2 = line5Conclusion.getDay2();
			Integer day3 = line5Conclusion.getDay3();
			Integer day4 = line5Conclusion.getDay4();
			Integer day5 = line5Conclusion.getDay5();
			ArrayList<Line5> line5List = supportMapper.getLine5(day1, day2, day3, day4, day5);
			line5Conclusion.setCnt(line5List.size());
			BigDecimal max = new BigDecimal(line5List.get(0).getAns());
			BigDecimal min = new BigDecimal(line5List.get(0).getAns());
			BigDecimal avg = new BigDecimal(0);
			int up = 0;
			int down = 0;
			for(int j=0;j<line5List.size();j++) {
				BigDecimal ans = new BigDecimal(line5List.get(j).getAns());
				if(max.compareTo(ans) < 0) {
					max = ans;
				}
				if(min.compareTo(ans) > 0) {
					min = ans;
				}
				if(ans.compareTo(new BigDecimal(0)) > 0) {
					up ++;
				}else {
					down++;
				}
				avg = avg.add(ans);
			}
			avg = avg.divide(new BigDecimal(line5List.size()),4,BigDecimal.ROUND_HALF_UP);
			line5Conclusion.setMax(max.floatValue());
			line5Conclusion.setMin(min.floatValue());
			line5Conclusion.setAvg(avg.floatValue());
			line5Conclusion.setUp(up);
			line5Conclusion.setDown(down);
			supportMapper.updateLine5Conclusion(line5Conclusion);
		}
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void testDualThrust() {
		ArrayList<Stock> stockList = stockService.getStockList();
		for(int i=0;i<stockList.size();i++) {
			Stock s = stockList.get(i);
			supportService.getDualThrust(s);
			System.out.println(s.getStockCode()+"完成");
		}
	}
}
