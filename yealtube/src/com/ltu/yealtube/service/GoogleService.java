package com.ltu.yealtube.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.log4j.Logger;

import com.ltu.yealtube.constants.Constant;
import com.ltu.yealtube.domain.User;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.exception.ErrorCode;
import com.ltu.yealtube.exception.ErrorCodeDetail;

/**
 * The Class GoogleService.
 */
public class GoogleService {
	
	/** The log. */
	private final Logger log = Logger.getLogger(GoogleService.class);
	
	/** The access token. */
	private String accessToken;

	/**
	 * Instantiates a new FB graph.
	 *
	 * @param accessToken the access token
	 */
	public GoogleService(String accessToken) {
		this.accessToken = accessToken;
	}
	
	/**
	 * Authenticate.
	 *
	 * @return the string
	 */
	public User authenticate() throws CommonException{
		User user = new User();
		try {
			String address = "https://www.googleapis.com/oauth2/v2/tokeninfo?access_token=" + accessToken;

			URL url = new URL(address);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

			
			String strEmail = String.valueOf("\"email\": \"");
			String strUserId = String.valueOf("\"user_id\": \"");
			
			String str;
			while ((str = in.readLine()) != null) {
				str = str.trim();
				if (str.startsWith(strEmail)) {
					user.setEmail(str.substring(strEmail.length(), str.length() - 2));
				}
				if (str.startsWith(strUserId)) {
					user.setLogin(str.substring(strUserId.length(), str.length() - 2));
				}
			}
			in.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e.getCause());
			throw new CommonException(ErrorCode.SYSTEM_EXCEPTION.getId(), ErrorCode.UNAUTHORIZED_EXCEPTION
					+ Constant.STRING_EXEPTION_DETAIL + e.getMessage());
		}
		
		if (user.getLogin() == null) {
			throw new CommonException(ErrorCode.UNAUTHORIZED_EXCEPTION,
					ErrorCodeDetail.ERROR_USER_NOT_AUTHENTICATED);
		} else {
			user.setType(Constant.GOOGLE_USER);
			return user;
		}
	}

}
