package com.fly.mail.test;

import com.fly.mail.info.MailSenderInfo;
import com.fly.mail.sendMail.Email;

public class JavaMailTest {
   
	public static void main(String[] args) {
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.163.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("qfanliyan@163.com");
		mailInfo.setPassword("520fly");
		mailInfo.setFromAddress("qfanliyan@163.com");
		mailInfo.setToAddress("ifanliyan@qq.com");
		mailInfo.setSubject("这是一封测试邮件");
		mailInfo.setContent("你好！这是一封测试邮件");
		
		try {
			Email.sendTextMail(mailInfo);
			//Email.sendHtmlMail(mailInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
