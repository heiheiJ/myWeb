package com.jhyarrow.myWeb.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.jhyarrow.myWeb.service.SpiderService;
import com.jhyarrow.myWeb.service.SupportService;
import com.jhyarrow.myWeb.service.TradeDayService;
import com.jhyarrow.myWeb.util.MailUtil;

public class StockJob{
	private static Logger logger = Logger.getLogger("StockJob");
	@Autowired
	private SupportService supportService;
	@Autowired
	private SpiderService spiderService;
	@Autowired
	private TradeDayService tradeDayService;
	
	public void spide(){
		StringBuffer sb = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		Integer tradeDay = tradeDayService.getTradeDayByDate(date);
		long start ,end;
		if(tradeDay == null) {
			System.out.println(date+"不是交易日！");
			sb.append(date+"不是交易日！\n");
			return;
		}
		try {
			logger.info(date+"，交易日"+tradeDay+"跑批开始");
			sb.append(date+"，交易日"+tradeDay+"跑批开始\n");
			
			//step1 获取当天股票数据
			start = System.currentTimeMillis();
			spiderService.spideStockDaily(date);
			end = System.currentTimeMillis();
			logger.info(date+"股票数据下载完成，用时"+(end-start)/1000+"秒");
			sb.append(date+"股票数据下载完成，用时"+(end-start)/1000+"秒\n");

			
			//step2 获取当天指数数据
			start = System.currentTimeMillis();
			spiderService.spideStockIndexDaily(date);
			end = System.currentTimeMillis();
			logger.info(date+"指数数据处理完成，用时"+(end-start)/1000+"秒");
			sb.append(date+"指数数据处理完成，用时"+(end-start)/1000+"秒\n");
			
			//step3计算macd
			start = System.currentTimeMillis();
			supportService.getMACD(tradeDay);
			end = System.currentTimeMillis();
			logger.info("交易日"+tradeDay+"MACD计算完成，用时"+(end-start)/1000+"秒");
			sb.append("交易日"+tradeDay+"MACD计算完成，用时"+(end-start)/1000+"秒\n");
			
			logger.info(date+"，交易日"+tradeDay+"跑批成功");
			sb.append(date+"，交易日"+tradeDay+"跑批成功\n");
		}catch (Exception e) {
			e.printStackTrace();
			logger.info(date+"，交易日"+tradeDay+"跑批失败");
			sb.append(date+"，交易日"+tradeDay+"跑批失败\n");
		}finally {
			try {
				String to = "632849309@qq.com";
				String subject = date+"结果";
				MailUtil.sendMail(to, subject, sb.toString());
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
