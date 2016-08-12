package com.lib.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 * @author Liou CZ
 */
public class SendEmail {

	public static final String HOST = "smtp.qq.com";
	public static final String PROTOCOL = "smtp";
	public static final int PORT = 587;
	public static final String FROM = "416530244@qq.com";// 发件人的email
	public static final String PWD = "razendhwhgtibghc";// 发件人密码
	//student_yyf@163.com yyfyyf1994
	//587 416530244@qq.com razendhwhgtibghc
	//13574472762@163.com a13574472762
	public static void main(String[] args) {
		send("13574472507@163.com", "test");
	}

	/**
	 * 获取Session
	 * 
	 * @return
	 */
	private static Session getSession() {
		Properties props = new Properties();
		props.put("mail.smtp.host", HOST);// 设置服务器地址
		props.put("mail.store.protocol", PROTOCOL);// 设置协议
		props.put("mail.smtp.port", PORT);// 设置端口
		props.put("mail.smtp.auth", true);

		Authenticator authenticator = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(FROM, PWD);
			}

		};
		Session session = Session.getDefaultInstance(props, authenticator);

		return session;
	}

	public static void send(String toEmail, String content) {
		Session session = getSession();
		try {
			// System.out.println("--send--"+content);
			// Instantiate a message
			Message msg = new MimeMessage(session);

			// Set message attributes
			msg.setFrom(new InternetAddress(FROM));
			InternetAddress[] address = { new InternetAddress(toEmail) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject("SOKLIB知识库管理系统账号激活邮件");
			msg.setSentDate(new Date());
			msg.setContent(content, "text/html;charset=utf-8");

			// Send the message
			Transport.send(msg);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

}
