package myWeb;

import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:springmvc.xml"
		,"classpath:applicationContext-service.xml","classpath:applicationContext-dao.xml"
		,"classpath:applicationContext-transaction.xml"})
public class JUnitTest {
	public static void main(String[] args) {
		BigDecimal bd = new BigDecimal(1.45);
		bd = bd.divide(new BigDecimal(1),0,BigDecimal.ROUND_HALF_UP);
		System.out.println(bd);
	}
	
}
