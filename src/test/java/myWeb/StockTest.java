package myWeb;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.domain.StockDaily;
import com.jhyarrow.myWeb.mapper.StockMapper;

public class StockTest extends JUnitTest{
	@Autowired
	private StockMapper stockMapper;
	
	
	@Test
	public void heihei() {
		String src = "<heihei></heihei><aaaa/><bbb/><cc/><d/>";
		while(src.indexOf("/>")!= -1) {
			String tmp1 = src.substring(0,src.indexOf("/>"));
			String tmp2 = src.substring(src.indexOf("/>")+2, src.length());
			String tmp3 = tmp1.substring(0,tmp1.lastIndexOf("<"));
			String tmp4 = tmp1.substring(tmp1.lastIndexOf("<")+1, tmp1.length());
			src = tmp3 + "<"+tmp4+"></"+tmp4+">" + tmp2;
		}
		System.out.println(src);
	}
	
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
