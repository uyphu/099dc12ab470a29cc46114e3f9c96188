package com.ltu.yealtube.helper;

import com.ltu.yealtube.constants.Constant;
import com.ltu.yealtube.domain.User;

public class UserHelper {

	public static User convertToUser(com.restfb.types.User user) {
		User obj = new User();
		obj.setLogin(user.getId());
		obj.setType(Constant.FACEBOOK_USER);
		obj.setEmail(user.getEmail());
		obj.setLangKey(user.getLocale());
		return obj;
	}
}
