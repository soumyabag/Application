package com.codeplanet;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;


public class SendMail extends HttpServlet{
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void send( String to, String subject, String body) {
		
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host","smtp.gmail.com");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.auth", "true");
		String from = "s24bag19@gmail.com";
		Session session = Session.getInstance(prop, new SimpleAuthenticator("s24bag19@gmail.com",
				"s25bag1996"));
		session.setDebug(true);
		try {
			MimeMessage message = new MimeMessage(session);
			message.setSubject(subject);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setText(body);
			Transport.send(message);
			
		}catch (Exception e) {
			
		}
      }
}

class SimpleAuthenticator extends javax.mail.Authenticator{
    String username=null;
    String password=null;
	public SimpleAuthenticator(String username, String password) {
		this.username=username;
		this.password=password;
	}
	public javax.mail.PasswordAuthentication getPasswordAuthentication() {
		return new javax.mail.PasswordAuthentication(username,password);
	}
	
}
