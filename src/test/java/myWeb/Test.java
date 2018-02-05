package myWeb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Test {
	
	public static void main(String[] args) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
		String date = df.format(System.currentTimeMillis());
	}
}
