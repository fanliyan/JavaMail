package com.fly.mail.authenticator;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * �Զ�����֤
 * @author ������
 * @ʱ�� 2017-05-09
 *
 */
public class MyAuthenticator extends Authenticator{

	private String userName;
	private String password;
	
	public MyAuthenticator() {
	}
	
	
	public MyAuthenticator(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}



	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}
  
}
