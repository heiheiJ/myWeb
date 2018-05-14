package com.jhyarrow.myWeb.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailUtil {
    private static String emailForm;
    private static String personal;
	private static JavaMailSenderImpl mailSender = createMailSender();
	
	private static JavaMailSenderImpl createMailSender() {
		Properties props = new Properties();
		try {  
	        props=PropertiesLoaderUtils.loadAllProperties("mail.properties");   
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(props.getProperty("mailHost"));
		mailSender.setPort(25);
		mailSender.setUsername(props.getProperty("mailUsername"));
		mailSender.setPassword(props.getProperty("mailPassword"));
		Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", props.getProperty("mailTimeout"));
        p.setProperty("mail.smtp.auth", "false");
        mailSender.setJavaMailProperties(p);
        emailForm = props.getProperty("mailFrom");
        personal = props.getProperty("personal");
        
        return mailSender;
	}
	
	public static void sendMail(String to, String subject, String html) throws MessagingException,UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 设置utf-8或GBK编码，否则邮件会有乱码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(emailForm, personal);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(html, true);
        mailSender.send(mimeMessage);
	}
}
