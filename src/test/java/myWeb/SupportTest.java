package myWeb;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.util.Args;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.service.StockService;
import com.jhyarrow.myWeb.service.SupportService;
import com.jhyarrow.myWeb.thread.MacdThread;

public class SupportTest extends JUnitTest {
	@Autowired
	private SupportService supportService;
	
	
//	@Test
//	@Transactional
//	@Rollback(false)
	public void caculatorAvgDay(String stockCode) {
		try {
			long start2 = System.currentTimeMillis();
			supportService.getAvgStatus(stockCode);
			long end2 = System.currentTimeMillis();
			System.out.println("stockCode共耗时"+ (end2-start2)/1000+"秒");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void caculator() {
		long start = System.currentTimeMillis();
		MacdThread.supportService = supportService;
		ExecutorService pool = Executors.newFixedThreadPool(20);
		String args[] = {"000601","000602","000603","000605",
				"000606","000607","000608","000609","000610","000611","000612","000613","000615","000616",
				"000617","000619","000620","000622","000623","000625","000626","000627","000628","000629",
				"000630","000631","000632","000633","000635","000636","000637","000638","000639","000650",
				"000651","000652","000655","000656","000657","000659","000661","000662","000663","000665",
				"000666","000667","000668","000669","000670","000671","000672","000673","000676","000677",
				"000678","000679","000680","000681","000682","000683","000685","000686","000687","000688",
				"000690","000691","000692","000693","000695","000697","000698","000700","000701","000702",
				"000703","000705","000707","000708","000709","000710","000711","000712","000713","000715",
				"000716","000717","000718","000719","000720","000721","000722","000723","000725","000726",
				"000727","000728","000729","000731","000732","000733","000735","000736","000737","000738",
				"000739","000748","000750","000751","000752","000753","000755","000756","000757","000758",
				"000759","000760","000761","000762","000766","000767","000768","000776","000777","000778",
				"000779","000780","000782","000783","000785","000786","000787","000788","000789","000790",
				"000791","000792","000793","000795","000796","000797","000798","000799","000800","000801",
				"000802","000803","000805","000806","000807","000809","000810","000811","000812","000813",
				"000815","000816","000818","000819","000820","000821","000822","000823","000825","000826",
				"000828","000829","000830","000831","000833","000835","000836","000837","000838","000839",
				"000848","000850","000851","000852","000856","000858","000859","000860","000861","000862",
				"000863","000868","000869","000875","000876","000877","000878","000880","000881","000882",
				"000883","000885","000886","000887","000888","000889","000890","000892","000893","000895",
				"000897","000898","000899","000900","000901","000902","000903","000905","000906","000908",
				"000909","000910","000911","000912","000913","000915","000916","000917","000918","000919",
				"000920","000921","000922","000923","000925","000926","000927","000928","000929","000930",
				"000931","000932","000933","000935","000936","000937","000938","000939","000948","000949",
				"000950","000951","000952","000953","000955","000957","000958","000959","000960","000961",
				"000962","000963","000965","000966","000967","000968","000969","000970","000971","000972",
				"000973","000975","000976","000977","000978","000979","000980","000981","000982","000983",
				"000985","000987","000988","000989","000990","000993","000995","000996","000997","000998",
				"000999"};
		CountDownLatch countDownLatch = new CountDownLatch(args.length);  
		for(int i=0;i<args.length;i++) {
			String stockCode = args[i];
			pool.execute(new MacdThread(stockCode,countDownLatch));
		}
		try {
			countDownLatch.await();
		}catch (InterruptedException e){  
            e.printStackTrace();  
        }  
		
		long end = System.currentTimeMillis();
		System.out.println("共耗时"+ (end-start)/1000+"秒");
	}
}
