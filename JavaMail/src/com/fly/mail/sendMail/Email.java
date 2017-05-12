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
 * ��װ�����ʼ�����
 * @author ������
 * @ʱ�� 2017-05-09
 *
 */
public class Email {
    
	/**
	 * ���ı���ʽ�����ʼ�
	 * @param mailInfo    �������ʼ���Ϣ
	 * @throws Exception
	 */
	public static void sendTextMail(MailSenderInfo mailInfo) throws Exception{
		// �ж��Ƿ���Ҫ�����֤
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		
		if(mailInfo.isValidate()){
			 // �����Ҫ�����֤���򴴽�һ��������֤��
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}
		// �����ʼ��Ự���Ժ�������֤������һ�������ʼ���session
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		// ����session����һ���ʼ���Ϣ
		Message mailMessage = new MimeMessage(sendMailSession);
		// �����ʼ������ߵ�ַ
		Address from = new InternetAddress(mailInfo.getFromAddress());
		// �����ʼ���Ϣ�ķ�����
		mailMessage.setFrom(from);
		// �����ʼ��Ľ����ߵ�ַ�������õ��ʼ���Ϣ��
		Address to = new InternetAddress(mailInfo.getToAddress());
		mailMessage.setRecipient(Message.RecipientType.TO, to);
		// �����ʼ���Ϣ������
		mailMessage.setSubject(mailInfo.getSubject());
		// �����ʼ���Ϣ���͵�ʱ��
		mailMessage.setSentDate(new Date());
		// �����ʼ���Ϣ����Ҫ����
		String mailContent = mailInfo.getContent();
		mailMessage.setText(mailContent);
		// �����ʼ�
		Transport.send(mailMessage);
	}
	
	/**
	 * ��HTML��ʽ�����ʼ�
	 * @param mailInfo   �������ʼ���Ϣ
	 * @throws Exception
	 */
	public static void sendHtmlMail(MailSenderInfo mailInfo) throws Exception{
		// �ж��Ƿ���Ҫ�����֤
				MyAuthenticator authenticator = null;
				Properties pro = mailInfo.getProperties();
				
				if(mailInfo.isValidate()){
					 // �����Ҫ�����֤���򴴽�һ��������֤��
					authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
				}
				// �����ʼ��Ự���Ժ�������֤������һ�������ʼ���session
				Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
				// ����session����һ���ʼ���Ϣ
				Message mailMessage = new MimeMessage(sendMailSession);
				// �����ʼ������ߵ�ַ
				Address from = new InternetAddress(mailInfo.getFromAddress());
				// �����ʼ���Ϣ�ķ�����
				mailMessage.setFrom(from);
				// �����ʼ��Ľ����ߵ�ַ�������õ��ʼ���Ϣ��
				Address to = new InternetAddress(mailInfo.getToAddress());
				// Message.RecipientType.TO���Ա�ʾ�����ߵ�����ΪTO
				mailMessage.setRecipient(Message.RecipientType.TO, to);
				// �����ʼ���Ϣ������
				mailMessage.setSubject(mailInfo.getSubject());
				// �����ʼ���Ϣ���͵�ʱ��
				mailMessage.setSentDate(new Date());
				Multipart mainPart = new MimeMultipart();
				// ����һ������HTML���ݵ�MimeBodyPart
				BodyPart html = new MimeBodyPart();
				html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
				mainPart.addBodyPart(html);
				// ��MiniMultipart��������Ϊ�ʼ�����
				mailMessage.setContent(mainPart);
				// �����ʼ�
				Transport.send(mailMessage);
	}
	
	/**
	 * ����������һ������ı���ͼƬ�������ĸ����ʼ�
	 * @throws Exception 
	 */
	public static void sendComplexMail(MailSenderInfo mailInfo) throws Exception{
		// �ж��Ƿ���Ҫ�����֤
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		
		if(mailInfo.isValidate()){
			 // �����Ҫ�����֤���򴴽�һ��������֤��
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}
		// �����ʼ��Ự���Ժ�������֤������һ�������ʼ���session
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		// ����session����һ���ʼ���Ϣ
		Message mailMessage = new MimeMessage(sendMailSession);
		// �����ʼ������ߵ�ַ
		Address from = new InternetAddress(mailInfo.getFromAddress());
		// �����ʼ���Ϣ�ķ�����
		mailMessage.setFrom(from);
		// �����ʼ��Ľ����ߵ�ַ�������õ��ʼ���Ϣ��
		Address to = new InternetAddress(mailInfo.getToAddress());
		// Message.RecipientType.TO���Ա�ʾ�����ߵ�����ΪTO
		mailMessage.setRecipient(Message.RecipientType.TO, to);
		// �����ʼ���Ϣ������
		mailMessage.setSubject(mailInfo.getSubject());
		// �����ʼ���Ϣ���͵�ʱ��
		mailMessage.setSentDate(new Date());
		
		
		MimeBodyPart image = new MimeBodyPart();
		//DataHandlerΪͼƬ  FileDataSourceΪͼƬ����Դ ���봦�����Ƚ����ƣ���������������ͨ������ MimeMultipart �������е������ͣ�����˵�� setSubType()����֪ MimeMultipart ������ز�����������ͼ��� Content-ID ����Ϊͼ��� src �� img �����ʹ�ã�
		DataHandler dh = new DataHandler(new FileDataSource("stock-photo-189028703.jpg"));
		image.setDataHandler(dh);
		//��һ�������� cid URL ����ͼ������ cid ��ͼ�񸽼� Content-ID ͷ������ setContentʱ�õ����cid
		image.setContentID("tupian");
		
		BodyPart text = new MimeBodyPart();
		text.setContent("������ͼƬ<br/><img src='cid:" + image.getContentID() + "'/>", "text/html;charset=UTF-8");
			
		MimeMultipart textImageRelated = new MimeMultipart();
		textImageRelated.addBodyPart(text);
		textImageRelated.addBodyPart(image);
		textImageRelated.setSubType("related");
		
		BodyPart textImage = new MimeBodyPart();
		textImage.setContent(textImageRelated);
		
		BodyPart attachment = new MimeBodyPart();
		//DataHandlerΪ����  FileDataSourceΪ��������Դ     ����� URL �ж�ʱ������������Դ�� URLDataSource
		DataHandler dh2 = new DataHandler(new FileDataSource(mailInfo.getAttachFileNames()[0]));
		attachment.setDataHandler(dh2);
		attachment.setFileName(MimeUtility.encodeText(dh2.getName()));
		
		MimeMultipart mainPart = new MimeMultipart();
		mainPart.addBodyPart(textImage);
		mainPart.addBodyPart(attachment);
		mainPart.setSubType("mixed");
		
		mailMessage.setContent(mainPart);
		// �����ʼ��������������ʼ����ر�����
		Transport.send(mailMessage);
	}
}
