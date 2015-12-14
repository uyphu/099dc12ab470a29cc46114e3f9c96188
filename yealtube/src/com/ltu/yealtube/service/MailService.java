package com.ltu.yealtube.service;


import org.apache.log4j.Logger;

import com.ltu.yealtube.constants.Constants;
import com.ltu.yealtube.domain.User;
import com.ltu.yealtube.utils.MailUtil;


public class MailService {
	
	/** The Constant logger. */
	private static final Logger log = Logger.getLogger(MailService.class);
	
	public MailService() {
		
	}

    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
    	log.debug("Send e-mail[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}");
        try {
            MailUtil.sendEmail(to, subject, content);
            log.debug("Sent e-mail to User " + to);
        } catch (Exception e) {
            log.warn("E-mail could not be sent to user " + to, e.getCause());
        }
    }

    public void sendActivationEmail(User user, String baseUrl) {
        log.debug("Sending activation e-mail to " + user.getEmail());
        String content = buildMsgBody(user, baseUrl);
        String subject = "yealtube account activation";
        //FIXME added it for developement
//        sendEmail(user.getEmail(), subject, content, false, true);
        sendEmail(Constants.ADMIN_EMAIL, subject, content, false, true);
    }

//    public void sendPasswordResetMail(User user, String baseUrl) {
//        log.debug("Sending password reset e-mail to '{}'", user.getEmail());
//        String content = buildMsgBody(user, baseUrl);
//        String subject = messageSource.getMessage("email.reset.title", null, locale);
//        sendEmail(user.getEmail(), subject, content, false, true);
//    }
    
    public String buildMsgBody(User user, String baseUrl) {
    	StringBuffer  body = new StringBuffer();
    	body.append("<!DOCTYPE html> ");
    	body.append(" ");
    	body.append("<html> ");
    	body.append("    <head> ");
    	body.append("        <title>yealtube account activation</title> ");
    	body.append("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /> ");
    	body.append("    </head> ");
    	body.append("    <body> ");
    	body.append("        <p>Dear ");
    	body.append(user.getLogin());
    	body.append("</p> ");
    	body.append("        <p>Your yealtube account has been created, please click on the URL below to activate");
    	body.append(" it:</p> ");
    	body.append("        <p> ");
    	body.append("            <a href=\""
    			+ baseUrl
    			+ "/#/activate?key="
    			+ user.getActivationKey()
    			+ "\""
    			+ ">"
    			+ user.getLogin()
    			+ "</a> ");
    	body.append("        </p> ");
    	body.append("        <p> ");
    	body.append("            <span>Regards,</span> ");
    	body.append("            <br /> ");
    	body.append("            <em>yealtube Team.</em> ");
    	body.append("        </p> ");
    	body.append("    </body> ");
    	body.append("</html>");
    	
    	return body.toString();
    }
}
