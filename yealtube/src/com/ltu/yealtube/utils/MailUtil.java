package com.ltu.yealtube.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailUtil {
	
	public static void sendEmail(String toEmail, String subject, String body) {
		// ...
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		try {
		    Message msg = new MimeMessage(session);
		    msg.setFrom(new InternetAddress("letruonguyphu@gmail.com", "Example.com Admin"));
		    msg.addRecipient(Message.RecipientType.TO,
		     new InternetAddress(toEmail, "Mr. User"));
		    msg.setSubject(subject);
		    msg.setText(body);
		    msg.setContent(body, "text/html; charset=utf-8");
		    Transport.send(msg);

		} catch (AddressException e) {
		    e.printStackTrace();
		} catch (MessagingException e) {
		    e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
