package client;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import domain.User;

/**
 * 用户注册成功后给用户发送邮件
 * @author Acer
 *
 */
public class SendEmail extends Thread {

	private User user;
	
	public void setUser(User user) {
		this.user = user;
	}



	@Override
	public void run() {
		Properties props=new Properties();
		props.setProperty("mail.host", "smtp.sina.com");
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.auth", "true");
		Session session=Session.getInstance(props);
		
		try {
			Transport ts=session.getTransport();
			ts.connect("smtp.sina.com", "edson121", "147258369xy");
			Message message=makeMessage(session,user);
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();
			
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}



	private Message makeMessage(Session session, User user2) throws AddressException, MessagingException {
		
		MimeMessage message=new MimeMessage(session);
		
		message.setFrom(new InternetAddress("edson121@sina.com"));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
		message.setSubject("注册");
		
		MimeBodyPart text=new MimeBodyPart();
		text.setContent("欢迎您:"+user.getUsername()+"<br>注册午后书院，点击链接<a href='#'>激活帐户</a>！","text/html;charset=UTF-8");
		
		MimeMultipart part=new MimeMultipart();
		part.addBodyPart(text);
		
		message.setContent(part);
		
		return message;
	}

	
}
