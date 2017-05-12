package com.fly.mail.sendMail;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.fly.mail.authenticator.MyAuthenticator;
import com.fly.mail.info.MailSenderInfo;

/**
 * 封装发送邮件代码
 * @author 范立炎
 * @时间 2017-05-09
 *
 */
public class Email {
    
	/**
	 * 以文本格式发送邮件
	 * @param mailInfo    待发送邮件信息
	 * @throws Exception
	 */
	public static void sendTextMail(MailSenderInfo mailInfo) throws Exception{
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		
		if(mailInfo.isValidate()){
			 // 如果需要身份认证，则创建一个密码验证器
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		// 根据session创建一个邮件消息
		Message mailMessage = new MimeMessage(sendMailSession);
		// 创建邮件发送者地址
		Address from = new InternetAddress(mailInfo.getFromAddress());
		// 设置邮件消息的发送者
		mailMessage.setFrom(from);
		// 创建邮件的接收者地址，并设置到邮件消息中
		Address to = new InternetAddress(mailInfo.getToAddress());
		mailMessage.setRecipient(Message.RecipientType.TO, to);
		// 设置邮件消息的主题
		mailMessage.setSubject(mailInfo.getSubject());
		// 设置邮件消息发送的时间
		mailMessage.setSentDate(new Date());
		// 设置邮件消息的主要内容
		String mailContent = mailInfo.getContent();
		mailMessage.setText(mailContent);
		// 发送邮件
		Transport.send(mailMessage);
	}
	
	/**
	 * 以HTML格式发送邮件
	 * @param mailInfo   待发送邮件信息
	 * @throws Exception
	 */
	public static void sendHtmlMail(MailSenderInfo mailInfo) throws Exception{
		// 判断是否需要身份认证
				MyAuthenticator authenticator = null;
				Properties pro = mailInfo.getProperties();
				
				if(mailInfo.isValidate()){
					 // 如果需要身份认证，则创建一个密码验证器
					authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
				}
				// 根据邮件会话属性和密码验证器构造一个发送邮件的session
				Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
				// 根据session创建一个邮件消息
				Message mailMessage = new MimeMessage(sendMailSession);
				// 创建邮件发送者地址
				Address from = new InternetAddress(mailInfo.getFromAddress());
				// 设置邮件消息的发送者
				mailMessage.setFrom(from);
				// 创建邮件的接收者地址，并设置到邮件消息中
				Address to = new InternetAddress(mailInfo.getToAddress());
				// Message.RecipientType.TO属性表示接收者的类型为TO
				mailMessage.setRecipient(Message.RecipientType.TO, to);
				// 设置邮件消息的主题
				mailMessage.setSubject(mailInfo.getSubject());
				// 设置邮件消息发送的时间
				mailMessage.setSentDate(new Date());
				Multipart mainPart = new MimeMultipart();
				// 创建一个包含HTML内容的MimeBodyPart
				BodyPart html = new MimeBodyPart();
				html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
				mainPart.addBodyPart(html);
				// 将MiniMultipart对象设置为邮件内容
				mailMessage.setContent(mainPart);
				// 发送邮件
				Transport.send(mailMessage);
	}
	
	/**
	 * 创建并发送一封包含文本、图片、附件的复杂邮件
	 * @throws Exception 
	 */
	public static void sendComplexMail(MailSenderInfo mailInfo) throws Exception{
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		
		if(mailInfo.isValidate()){
			 // 如果需要身份认证，则创建一个密码验证器
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		// 根据session创建一个邮件消息
		Message mailMessage = new MimeMessage(sendMailSession);
		// 创建邮件发送者地址
		Address from = new InternetAddress(mailInfo.getFromAddress());
		// 设置邮件消息的发送者
		mailMessage.setFrom(from);
		// 创建邮件的接收者地址，并设置到邮件消息中
		Address to = new InternetAddress(mailInfo.getToAddress());
		// Message.RecipientType.TO属性表示接收者的类型为TO
		mailMessage.setRecipient(Message.RecipientType.TO, to);
		// 设置邮件消息的主题
		mailMessage.setSubject(mailInfo.getSubject());
		// 设置邮件消息发送的时间
		mailMessage.setSentDate(new Date());
		
		
		MimeBodyPart image = new MimeBodyPart();
		//DataHandler为图片  FileDataSource为图片数据源 （与处理附件比较类似：区别在于您必需通过设置 MimeMultipart 构造器中的子类型（或者说用 setSubType()）告知 MimeMultipart 各个相关部件，并设置图像的 Content-ID ，作为图像的 src 在 img 标记中使用）
		DataHandler dh = new DataHandler(new FileDataSource("stock-photo-189028703.jpg"));
		image.setDataHandler(dh);
		//用一个给定的 cid URL 引用图像，其中 cid 是图像附件 Content-ID 头的引用 setContent时用到这个cid
		image.setContentID("tupian");
		
		BodyPart text = new MimeBodyPart();
		text.setContent("美丽的图片<br/><img src='cid:" + image.getContentID() + "'/>", "text/html;charset=UTF-8");
			
		MimeMultipart textImageRelated = new MimeMultipart();
		textImageRelated.addBodyPart(text);
		textImageRelated.addBodyPart(image);
		textImageRelated.setSubType("related");
		
		BodyPart textImage = new MimeBodyPart();
		textImage.setContent(textImageRelated);
		
		BodyPart attachment = new MimeBodyPart();
		//DataHandler为附件  FileDataSource为附件数据源     如果从 URL 中读时，附件的数据源是 URLDataSource
		DataHandler dh2 = new DataHandler(new FileDataSource(mailInfo.getAttachFileNames()[0]));
		attachment.setDataHandler(dh2);
		attachment.setFileName(MimeUtility.encodeText(dh2.getName()));
		
		MimeMultipart mainPart = new MimeMultipart();
		mainPart.addBodyPart(textImage);
		mainPart.addBodyPart(attachment);
		mainPart.setSubType("mixed");
		
		mailMessage.setContent(mainPart);
		// 连接邮件服务器、发送邮件、关闭连接
		Transport.send(mailMessage);
	}
}
