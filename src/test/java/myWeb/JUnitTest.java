package myWeb;

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
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("heihei", "heihei");
		String value = map.get("heihei");
		value += "a";
		map.put("heihei",value);
		System.out.println(map.get("heihei"));
	}
	
}
