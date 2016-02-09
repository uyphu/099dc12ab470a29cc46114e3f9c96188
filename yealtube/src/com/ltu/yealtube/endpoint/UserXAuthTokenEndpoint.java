package com.ltu.yealtube.endpoint;

import javax.annotation.Nonnull;
import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.ltu.yealtube.auth.Token;
import com.ltu.yealtube.auth.TokenProvider;
import com.ltu.yealtube.constants.Constant;
import com.ltu.yealtube.domain.User;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.service.FacebookService;
import com.ltu.yealtube.service.GoogleService;
import com.ltu.yealtube.service.UserService;


/**
 * The Class UserXAuthTokenEndpoint.
 * @author uyphu
 */
@Api(name = "userxauthtokenendpoint", namespace = @ApiNamespace(ownerDomain = "ltu.com", ownerName = "ltu.com", packagePath = "yealtube.domain"))
public class UserXAuthTokenEndpoint {

	/**
	 * Authorize.
	 *
	 * @param login the login
	 * @param password the password
	 * @return the token
	 * @throws CommonException the common exception
	 */
	@ApiMethod(name = "authorize", httpMethod = HttpMethod.POST, path = "authorize")
	public Token authorize(@Nonnull @Named("login") String login, @Nonnull @Named("password") String password)
			throws CommonException {
		UserService userService = UserService.getInstance();
		User user = userService.login(login, password);
		TokenProvider tokenProvider = new TokenProvider(Constant.SECRET_KEY, Constant.TOKEN_VALIDITY_IN_SECONDS);
		return tokenProvider.createToken(user);
	}
	
	/**
	 * Authorize google.
	 *
	 * @param token the token
	 * @return the token
	 * @throws CommonException the common exception
	 */
	@ApiMethod(name = "authorizeGoogle", httpMethod = HttpMethod.POST, path = "authorizeGoogle")
	public Token authorizeGoogle(@Nonnull @Named("token") String token)
			throws CommonException {
		GoogleService googleService = new GoogleService(token);
		User user = googleService.authenticate();
		
		UserService userService = new UserService();
		user = userService.updateUser(user);
		return new Token(token, 0, Constant.GOOGLE_USER);
	}
	
	/**
	 * Authorize facebook.
	 *
	 * @param token the token
	 * @return the token
	 * @throws CommonException the common exception
	 */
	@ApiMethod(name = "authorizeFacebook", httpMethod = HttpMethod.POST, path = "authorizeFacebook")
	public Token authorizeFacebook(@Nonnull @Named("token") String token)
			throws CommonException {
		FacebookService facebookService = new FacebookService(token);
		User user = facebookService.authenticate();
		UserService userService = new UserService();
		user = userService.updateUser(user);
		return new Token(token, 0, Constant.FACEBOOK_USER);
	}

}
