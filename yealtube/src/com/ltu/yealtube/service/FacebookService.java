package com.ltu.yealtube.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.ltu.yealtube.constants.Constant;
import com.ltu.yealtube.domain.User;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.exception.ErrorCode;
import com.ltu.yealtube.exception.ErrorCodeDetail;
import com.ltu.yealtube.helper.UserHelper;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;

/**
 * The Class FBGraph.
 */
public class FacebookService {
	
	/** The log. */
	private final Logger log = Logger.getLogger(FacebookService.class);

	/** The access token. */
	private String accessToken;

	/**
	 * Instantiates a new FB graph.
	 *
	 * @param accessToken the access token
	 */
	public FacebookService(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * Authenticate.
	 *
	 * @return the user
	 */
	public User authenticate() throws CommonException {
		User result = new User();
		try {
			FacebookClient facebookClient = new DefaultFacebookClient(accessToken, Version.VERSION_2_5);
			com.restfb.types.User user = facebookClient.fetchObject("me", com.restfb.types.User.class);
			result = UserHelper.convertToUser(user);
		} catch (Exception e) {
			log.error(e.getMessage(), e.getCause());
			throw new CommonException(ErrorCode.SYSTEM_EXCEPTION.getId(), ErrorCode.UNAUTHORIZED_EXCEPTION
					+ Constant.STRING_EXEPTION_DETAIL + e.getMessage());
		}
		if (result.getLogin() == null) {
			throw new CommonException(ErrorCode.UNAUTHORIZED_EXCEPTION,
					ErrorCodeDetail.ERROR_USER_NOT_AUTHENTICATED);
		} else {
			return result;
		}
	}

	/**
	 * Gets the graph data.
	 *
	 * @param fbGraph the fb graph
	 * @return the graph data
	 */
	public Map<String, String> getGraphData(String fbGraph) {
		Map<String, String> fbProfile = new HashMap<String, String>();
		try {
			JSONObject json = new JSONObject(fbGraph);
			fbProfile.put("id", json.getString("id"));
			fbProfile.put("first_name", json.getString("first_name"));
			if (json.has("email"))
				fbProfile.put("email", json.getString("email"));
			if (json.has("gender"))
				fbProfile.put("gender", json.getString("gender"));
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in parsing FB graph data. " + e);
		}
		return fbProfile;
	}
	
}
