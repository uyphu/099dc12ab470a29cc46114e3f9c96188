package com.ltu.yealtube.endpoint;

import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.ltu.yealtube.domain.User;
import com.ltu.yealtube.service.MailService;
import com.ltu.yealtube.utils.MailUtil;

@Api(name = "mailendpoint", namespace = @ApiNamespace(ownerDomain = "ltu.com", ownerName = "ltu.com", packagePath = "yealtube.domain"))
public class MailEndpoint {
	
	@ApiMethod(name = "sendMail", httpMethod = HttpMethod.POST, path = "sendMail")
	public void sendMail(@Named("toEmail") String toEmail, @Named("subject") String subject, @Named("body") String body) {
		MailUtil.sendEmail(toEmail, subject, body);
	}
	
	@ApiMethod(name = "sendActiveMail", httpMethod = HttpMethod.POST, path = "sendActiveMail")
	public void sendActiveMail(@Named("toEmail") String toEmail, @Named("subject") String subject) {
		 String baseUrl = "https://yealtubetest.appspot.com";
         MailService mailService = new MailService();
         User user = new User();
         user.setLogin("test");
         String body = mailService.buildMsgBody(user, baseUrl);
		MailUtil.sendEmail(toEmail, subject, body);
	}

}
