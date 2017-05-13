package com.fly.mail.test;

import com.fly.mail.info.MailSenderInfo;
import com.fly.mail.sendMail.Email;

public class JavaMailTest {
   
	public static void main(String[] args) {
		//设置邮件相关信息
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.163.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("qfanliyan@163.com");
		mailInfo.setPassword("520fly");     // 您的邮箱密码,若你的邮箱开启了客户端授权密码，则此处是您的客户端授权密码
		mailInfo.setFromAddress("qfanliyan@163.com");
		mailInfo.setToAddress("ifanliyan@qq.com");
		mailInfo.setSubject("这是一封测试邮件");
		mailInfo.setContent("你好！这是一封测试邮件");
		 String[] fileName = {"手艺人.docx"};
		 mailInfo.setAttachFileNames(fileName);
		try {
			//Email.sendTextMail(mailInfo);
			//Email.sendHtmlMail(mailInfo);
			Email.sendComplexMail(mailInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
