package com.ltu.yealtube.helper;

import com.ltu.yealtube.constants.Constants;
import com.ltu.yealtube.domain.User;

public class UserHelper {

	public static User convertToUser(com.restfb.types.User user) {
		User obj = new User();
		obj.setLogin(user.getId());
		obj.setType(Constants.FACEBOOK_USER);
		obj.setEmail(user.getEmail());
		obj.setLangKey(user.getLocale());
		return obj;
	}
}
