package myWeb;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.jhyarrow.myWeb.domain.Line;
import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.domain.support.SupportKdj;
import com.jhyarrow.myWeb.domain.support.SupportMacd;
import com.jhyarrow.myWeb.mapper.StockMapper;
import com.jhyarrow.myWeb.mapper.SupportMapper;

public class SupportTest extends JUnitTest {
	@Autowired
	private SupportMapper supportMapper;
	@Autowired
	private StockMapper stockMapper;
	
	@Test
	@Transactional
	@Rollback(false)
	public void testSupportMacd() {
		ArrayList<Stock> stockList = stockMapper.getStockList();
		for(int i=0;i<stockList.size();i++) {
			Stock s = stockList.get(i);
			String stockCode = s.getStockCode();
			ArrayList<StockDaily> stockDailyList = stockMapper.getStockDailyList(stockCode);
			try {
				boolean flag = false;//true上涨，false下跌
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
				System.out.println(stockCode+"失败");
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
				System.out.println(stockCode+"失败");
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
				boolean flag = true;//true上涨false下跌
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
				System.out.println(stockCode+"失败");
			}
		}
	}
}
