package myWeb;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.domain.StockIndexDaily;
import com.jhyarrow.myWeb.mapper.StockMapper;
import com.jhyarrow.myWeb.service.StockService;
import com.jhyarrow.myWeb.service.TradeDayService;

public class StockTest extends JUnitTest{
	@Autowired
	private StockMapper stockMapper;
	@Autowired
	private StockService stockService;
	@Autowired
	private TradeDayService tradeDayService;
	
	
//	@Test
//	@Transactional
//	@Rollback(false)
	public  void test() {
		ArrayList<Stock> stockList = (ArrayList<Stock>) stockMapper.getStockListSh();
		for(int i=0;i<stockList.size();i++) {
			Stock stock = stockList.get(i);
			String stockCode = stock.getStockCode();
			ArrayList<StockDaily> stockDailyList = stockMapper.getStockDailyList(stockCode);
			
			for(int j=1;j<stockDailyList.size();j++) {
				StockDaily stockDaily = stockDailyList.get(j);
				stockDaily.setPrevTradeDay(stockDailyList.get(j-1).getTradeDay());
				stockMapper.updateStockDaily(stockDaily);
			}
			System.out.println(stockCode + "处理完成");
		}
		
		stockList = (ArrayList<Stock>) stockMapper.getStockListSz();
		for(int i=0;i<stockList.size();i++) {
			Stock stock = stockList.get(i);
			String stockCode = stock.getStockCode();
			ArrayList<StockDaily> stockDailyList = stockMapper.getStockDailyList(stockCode);
			
			for(int j=1;j<stockDailyList.size();j++) {
				StockDaily stockDaily = stockDailyList.get(j);
				stockDaily.setPrevTradeDay(stockDailyList.get(j-1).getTradeDay());
				stockMapper.updateStockDaily(stockDaily);
			}
			System.out.println(stockCode + "处理完成");
		}
		
		stockList = (ArrayList<Stock>) stockMapper.getStockListCy();
		for(int i=0;i<stockList.size();i++) {
			Stock stock = stockList.get(i);
			String stockCode = stock.getStockCode();
			ArrayList<StockDaily> stockDailyList = stockMapper.getStockDailyList(stockCode);
			
			for(int j=1;j<stockDailyList.size();j++) {
				StockDaily stockDaily = stockDailyList.get(j);
				stockDaily.setPrevTradeDay(stockDailyList.get(j-1).getTradeDay());
				stockMapper.updateStockDaily(stockDaily);
			}
			System.out.println(stockCode + "处理完成");
		}
		
		stockList = (ArrayList<Stock>) stockMapper.getStockListOther();
		for(int i=0;i<stockList.size();i++) {
			Stock stock = stockList.get(i);
			String stockCode = stock.getStockCode();
			ArrayList<StockDaily> stockDailyList = stockMapper.getStockDailyList(stockCode);
			
			for(int j=1;j<stockDailyList.size();j++) {
				StockDaily stockDaily = stockDailyList.get(j);
				stockDaily.setPrevTradeDay(stockDailyList.get(j-1).getTradeDay());
				stockMapper.updateStockDaily(stockDaily);
			}
			System.out.println(stockCode + "处理完成");
		}
	}
	
}
