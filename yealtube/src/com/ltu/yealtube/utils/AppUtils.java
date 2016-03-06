package com.ltu.yealtube.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.ltu.yealtube.constants.Constant;

// TODO: Auto-generated Javadoc
/**
 * The Class AppUtils.
 */
public class AppUtils {
	
	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(AppUtils.class.getName());
	
	/**
	 * Crypt with m d5.
	 *
	 * @param password the password
	 * @return the string
	 */
	public static String cryptWithMD5(String password){
		try {
			if (password != null) {
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] passBytes = password.getBytes();
				md.reset();
				byte[] digested = md.digest(passBytes);
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < digested.length; i++) {
					sb.append(Integer.toHexString(0xff & digested[i]));
				}
				return sb.toString();
			}
		} catch (NoSuchAlgorithmException ex) {
			logger.error(ex.getMessage());
		}
		return null;
	}
	
	/**
	 * Generate token.
	 *
	 * @param login the login
	 * @return the string
	 */
	public static String generateToken(String login){
		//String token = Base64.encodeString(login) + RandomUtil.generateActivationKey();
		String token = RandomUtil.generateActivationKey();
		return token;
	}
	
	/**
	 * Gets the current time.
	 *
	 * @return the current time
	 */
	public static String getCurrentTime() {
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS Z");
		return DATE_FORMAT.format(Calendar.getInstance().getTime());
	}
	
	/**
	 * Gets the current date.
	 *
	 * @return the current date
	 */
	public static Date getCurrentDate() {
		return Calendar.getInstance().getTime();
	}
	
	/**
	 * To date.
	 *
	 * @param date the date
	 * @return the date
	 */
	public static Date toDate(String date) {
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(Constant.LONG_DATE_FORMAT);
		try {
			return DATE_FORMAT.parse(date);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e.getCause());
			return null;
		}
	}
	
	/**
	 * To string.
	 *
	 * @param date the date
	 * @return the string
	 */
	public static String toString(Date date) {
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(Constant.LONG_DATE_FORMAT);
		return DATE_FORMAT.format(date);
	}
	
	/**
	 * To short date string.
	 *
	 * @param date the date
	 * @return the string
	 */
	public static String toShortDateString(Date date) {
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(Constant.SHORT_DATE_FORMAT);
		return DATE_FORMAT.format(date);
	}
	
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("letruonguyphu@gmail.com","0913630243");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("letruonguyphu@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("uyphu@yahoo.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler," +
					"\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
}
