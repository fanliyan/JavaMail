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
		mailInfo.setSubject("����һ������ʼ�");
		mailInfo.setContent("��ã�����һ������ʼ�");
		 String[] fileName = {"������.docx"};
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
