package com.jhyarrow.myWeb.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.jhyarrow.myWeb.service.JobService;

public class JobServiceImpl implements JobService{

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

}
