package com.ltu.yealtube.auth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Authenticator;
import com.google.common.net.HttpHeaders;
import com.ltu.yealtube.service.UserService;

public class AppAuthenticator implements Authenticator {

	private final Logger log = Logger.getLogger(UserService.class);

	@Override
	public User authenticate(HttpServletRequest request) {
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);

		// Check if the HTTP Authorization header is present and formatted
		// correctly
		if (token != null && token.startsWith("Bearer ")) {

			// Extract the token from the HTTP Authorization header
			token = token.substring("Bearer".length()).trim();

			String user = authenticateGoogleToken(token);
			if (user != null) {
				return new User(user);
			}
		} else {
			//Get facebook token logged in
			token = request.getHeader("access_token");
		}
		
		return null;
	}

	/**
	 * Authenticate google token.
	 * 
	 * @param token
	 *            the token
	 * @return the string
	 */
	private String authenticateGoogleToken(String token) {
		try {
			String address = "https://www.googleapis.com/oauth2/v2/tokeninfo?access_token=" + token;

			URL url = new URL(address);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

			String email = null;
			String str;

			String value = String.valueOf("\"email\": \"");
			while ((str = in.readLine()) != null) {
				str = str.trim();
				if (str.startsWith(value)) {
					email = str.substring(value.length(), str.length() - 2);
				}
			}
			in.close();
			
			return email;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}

	}

}
