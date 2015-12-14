package com.ltu.yealtube.auth;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Authenticator;
import com.google.common.net.HttpHeaders;
import com.ltu.yealtube.constants.Constants;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.service.FacebookService;
import com.ltu.yealtube.service.GoogleService;
import com.ltu.yealtube.service.UserService;
import com.ltu.yealtube.utils.StringUtils;

/**
 * The Class AppAuthenticator.
 */
public class AppAuthenticator implements Authenticator {


	/** The log. */
	private final Logger log = Logger.getLogger(AppAuthenticator.class);
	
	@Override
	public User authenticate(HttpServletRequest request) {
		
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		// Check if the HTTP Authorization header is present and formatted
		// correctly
		if (token != null && token.startsWith("Bearer ")) {
			// Extract the token from the HTTP Authorization header
			String type = String.valueOf(token.charAt(token.length()-1));
			token = token.substring("Bearer".length(), token.length()-1).trim();
			
			if (Constants.SYSTEM_USER.equals(type)) {
				TokenProvider tokenProvider = TokenProvider.getInstance(Constants.SECRET_KEY, Constants.TOKEN_VALIDITY_IN_SECONDS);
				UserService userService = UserService.getInstance();
				if (StringUtils.hasText(token)) {
	                String login = tokenProvider.getUserNameFromToken(token);
	                com.ltu.yealtube.domain.User user = userService.findOneByLogin(login, Constants.SYSTEM_USER);
	                if (tokenProvider.validateToken(token, user)) {
	                    return new User(user.getEmail());
	                }
	            } else {
	            	return null;
	            }
			}
			
			if (Constants.GOOGLE_USER.equals(type)) {
				GoogleService googleService = new GoogleService(token);
				try {
					com.ltu.yealtube.domain.User user = googleService.authenticate();
					return new User(user.getEmail());
				} catch (CommonException e) {
					log.error(e.getMessage(), e.getCause());
					return null;
				}
			}
			
			if (Constants.FACEBOOK_USER.equals(type)) {
				FacebookService googleService = new FacebookService(token);
				try {
					com.ltu.yealtube.domain.User user = googleService.authenticate();
					if (user.getLogin() != null) {
						return new User(user.getLogin());
					}
				} catch (CommonException e) {
					log.error(e.getMessage(), e.getCause());
					return null;
				}
			}
		}				
		return null;
	}
	
}
