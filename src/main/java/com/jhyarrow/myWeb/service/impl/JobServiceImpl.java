package com.jhyarrow.myWeb.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.jhyarrow.myWeb.domain.Stock;
import com.jhyarrow.myWeb.mapper.StockMapper;
import com.jhyarrow.myWeb.service.JobService;

public class JobServiceImpl implements JobService{
	@Autowired
	private StockMapper stockMapper;
	
	private static Logger logger = Logger.getLogger("JobServiceImpl");
	  
	public void doSpider(String bat){
		  try{
			  final Process process = Runtime.getRuntime().exec(bat);
			  new Thread() {
				  public void run() {
					  InputStream inputStream =  process.getInputStream();
					  BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(inputStream));
					  String msg;
					  try{
						  while((msg=bufferedReader.readLine())!=null) {
							logger.info(msg);  
						  }
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  };
			  	}.start();
			  	
			  new Thread() {
					public void run() {
						  InputStream inputStream =  process.getErrorStream();
						  BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(inputStream));
						  String msg;
						  try{
							  while((msg=bufferedReader.readLine())!=null) {
								logger.info(msg);  
							  }
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					  };
				  	}.start();
			process.waitFor();
	    }catch (Exception e){
	      e.printStackTrace();
	    }
	  }
	  
	private Stock handleDataAfterJson(Stock stock){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
			stock.setDate(df.format(new Date(System.currentTimeMillis())));
			
			//涨跌
			String upPer = stock.getUp_per();
			if(upPer.contains("%")) {
				BigDecimal bd = new BigDecimal(upPer.substring(0, upPer.indexOf("%")));
				bd = bd.divide(new BigDecimal(100));
				stock.setUp_per(bd.toString());
			}
			
			//
			String lmr = stock.getLmr();
			lmr = lmr.replaceAll("--", "0");
			stock.setLmr(lmr);
			
			//市盈率
			String pe_ratio = stock.getPe_ratio();
			pe_ratio = pe_ratio.replaceAll("--", "0");
			stock.setPe_ratio(pe_ratio);
			
			
			//成交量（万手）
			String volumn = stock.getVolumn();
			if(volumn.contains("万手")) {
				BigDecimal bd = new BigDecimal(volumn.substring(0, volumn.indexOf("万手")));
				stock.setVolumn(bd.toString());
			}else if (volumn.contains("亿手")) {
				BigDecimal bd = new BigDecimal(volumn.substring(0, volumn.indexOf("亿手")));
				bd = bd.multiply(new BigDecimal(10000));
				stock.setVolumn(bd.toString());
			}else if(volumn.contains("手")){
				BigDecimal bd = new BigDecimal(volumn.substring(0, volumn.indexOf("手")));
				bd = bd.divide(new BigDecimal(10000));
				stock.setVolumn(bd.toString());
			}else {
				BigDecimal bd = new BigDecimal(volumn);
				bd = bd.divide(new BigDecimal(10000));
				stock.setVolumn(bd.toString());
			}
			
			//内盘(万手)
			String invol = stock.getInvol();
			invol = invol.replaceAll("--", "0");
			if(invol.contains("万手")) {
				BigDecimal bd = new BigDecimal(invol.substring(0, invol.indexOf("万手")));
				stock.setInvol(bd.toString());
			}else if (invol.contains("亿手")) {
				BigDecimal bd = new BigDecimal(invol.substring(0, invol.indexOf("亿手")));
				bd = bd.multiply(new BigDecimal(10000));
				stock.setInvol(bd.toString());
			}else if(invol.contains("手")){
				BigDecimal bd = new BigDecimal(invol.substring(0, invol.indexOf("手")));
				bd = bd.divide(new BigDecimal(10000));
				stock.setInvol(bd.toString());
			}else {
				BigDecimal bd = new BigDecimal(invol);
				bd = bd.divide(new BigDecimal(10000));
				stock.setInvol(bd.toString());
			}
			
			//成交额（万元）
			String turnVolume = stock.getTurn_volume();
			if(turnVolume.contains("万")) {
				BigDecimal bd = new BigDecimal(turnVolume.substring(0, turnVolume.indexOf("万")));
				stock.setTurn_volume(bd.toString());
			}else if (turnVolume.contains("亿")) {
				BigDecimal bd = new BigDecimal(turnVolume.substring(0, turnVolume.indexOf("亿")));
				bd = bd.multiply(new BigDecimal(10000));
				stock.setTurn_volume(bd.toString());
			}else {
				BigDecimal bd = new BigDecimal(turnVolume);
				bd = bd.divide(new BigDecimal(10000));
				stock.setTurn_volume(bd.toString());
			}
			
			//委比
			String theCommittee = stock.getThe_committee();
			if(theCommittee.contains("%")) {
				BigDecimal bd = new BigDecimal(theCommittee.substring(0, theCommittee.indexOf("%")));
				bd = bd.divide(new BigDecimal(100));
				stock.setThe_committee(bd.toString());
			}else {
				theCommittee = theCommittee.replaceAll("--", "0");
				stock.setThe_committee(theCommittee);
			}
			
			//流通市值(万元)
			String famc = stock.getFamc();
			if(famc.contains("万")) {
				BigDecimal bd = new BigDecimal(famc.substring(0, famc.indexOf("万")));
				stock.setFamc(bd.toString());
			}else if (famc.contains("亿")) {
				BigDecimal bd = new BigDecimal(famc.substring(0, famc.indexOf("亿")));
				bd = bd.multiply(new BigDecimal(10000));
				stock.setFamc(bd.toString());
			}else {
				BigDecimal bd = new BigDecimal(famc);
				bd = bd.divide(new BigDecimal(10000));
				stock.setFamc(bd.toString());
			}
			
			//总股本(万元)
			String generalCapital = stock.getGeneral_capital();
			if(generalCapital.contains("万")) {
				BigDecimal bd = new BigDecimal(generalCapital.substring(0, generalCapital.indexOf("万")));
				stock.setGeneral_capital(bd.toString());
			}else if (generalCapital.contains("亿")) {
				BigDecimal bd = new BigDecimal(generalCapital.substring(0, generalCapital.indexOf("亿")));
				bd = bd.multiply(new BigDecimal(10000));
				stock.setGeneral_capital(bd.toString());
			}else {
				BigDecimal bd = new BigDecimal(generalCapital);
				bd = bd.divide(new BigDecimal(10000));
				stock.setGeneral_capital(bd.toString());
			}
			
			//换手率
			String turnoverRate = stock.getTurnover_rate();
			if(turnoverRate.contains("%")) {
				BigDecimal bd = new BigDecimal(turnoverRate.substring(0, turnoverRate.indexOf("%")));
				bd = bd.divide(new BigDecimal(100));
				stock.setTurnover_rate(bd.toString());
			}
			
			//外盘(万手)
			String outerDisc = stock.getOuter_disc();
			outerDisc = outerDisc.replaceAll("--", "0");
			if(outerDisc.contains("万手")) {
				BigDecimal bd = new BigDecimal(outerDisc.substring(0, outerDisc.indexOf("万手")));
				stock.setOuter_disc(bd.toString());
			}else if (outerDisc.contains("亿手")) {
				BigDecimal bd = new BigDecimal(outerDisc.substring(0, outerDisc.indexOf("亿手")));
				bd = bd.multiply(new BigDecimal(10000));
				stock.setOuter_disc(bd.toString());
			}else if(outerDisc.contains("手")){
				BigDecimal bd = new BigDecimal(outerDisc.substring(0, outerDisc.indexOf("手")));
				bd = bd.divide(new BigDecimal(10000));
				stock.setOuter_disc(bd.toString());
			}else {
				BigDecimal bd = new BigDecimal(outerDisc);
				bd = bd.divide(new BigDecimal(10000));
				stock.setOuter_disc(bd.toString());
			}
			
			//振幅
			String swing = stock.getSwing();
			if(swing.contains("%")) {
				BigDecimal bd = new BigDecimal(swing.substring(0, swing.indexOf("%")));
				bd = bd.divide(new BigDecimal(100));
				stock.setSwing(bd.toString());
			}else {
				swing = swing.replaceAll("--", "0");
				stock.setSwing(swing);
			}
			
			//总市值(万元)
			String marketCap = stock.getMarket_cap();
			if(marketCap.contains("万")) {
				BigDecimal bd = new BigDecimal(marketCap.substring(0, marketCap.indexOf("万")));
				stock.setMarket_cap(bd.toString());
			}else if (marketCap.contains("亿")) {
				BigDecimal bd = new BigDecimal(marketCap.substring(0, marketCap.indexOf("亿")));
				bd = bd.multiply(new BigDecimal(10000));
				stock.setMarket_cap(bd.toString());
			}else {
				BigDecimal bd = new BigDecimal(marketCap);
				bd = bd.divide(new BigDecimal(10000));
				stock.setMarket_cap(bd.toString());
			}
			
			//流通股本（万元）
			String flowOfEquity = stock.getFlow_of_equity();
			if(flowOfEquity.contains("万")) {
				BigDecimal bd = new BigDecimal(flowOfEquity.substring(0, flowOfEquity.indexOf("万")));
				stock.setFlow_of_equity(bd.toString());
			}else if (flowOfEquity.contains("亿")) {
				BigDecimal bd = new BigDecimal(flowOfEquity.substring(0, flowOfEquity.indexOf("亿")));
				bd = bd.multiply(new BigDecimal(10000));
				stock.setFlow_of_equity(bd.toString());
			}else {
				BigDecimal bd = new BigDecimal(flowOfEquity);
				bd = bd.divide(new BigDecimal(10000));
				stock.setFlow_of_equity(bd.toString());
			}
			
			return stock;
		}
	  
	public void handleData(String jsonPath) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
			File file = new File(jsonPath+df.format(new Date(System.currentTimeMillis()))+"stock.json");
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
				String json = null;
				while ((json = reader.readLine()) != null) {
					json = json.replaceAll("},", "}").replaceAll("\n", "").replaceAll(" ", "");
					Gson gson = new Gson();
					Stock stock = gson.fromJson(json, Stock.class);
					stock = this.handleDataAfterJson(stock);
					try {
						stockMapper.addStock(stock);
					}catch(Exception e) {
						logger.info(stock.toString() + "出错"+e.getMessage());
					}
	            }
				reader.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
			}
			finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e1) {
	                }
	            }
	        }
		}

}
