package com.ltu.yealtube.endpoint;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.ltu.yealtube.constants.Constants;
import com.ltu.yealtube.domain.User;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.service.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserEndpoint.
 */
@Api(name = "userendpoint", namespace = @ApiNamespace(ownerDomain = "ltu.com", ownerName = "ltu.com", packagePath = "yealtube.domain"))
public class UserEndpoint {
	
	/** The user service. */
	private UserService userService = new UserService();

	/**
	 * Return a collection of users.
	 *
	 * @param cursorString the cursor string
	 * @param count The number of users
	 * @return a list of Users
	 */
	@ApiMethod(name = "listUser", scopes = {Constants.EMAIL_SCOPE},
			clientIds = {Constants.WEB_CLIENT_ID, 
		     com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID},
		     audiences = {Constants.ANDROID_AUDIENCE})
	public CollectionResponse<User> listUser(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("count") Integer count) {
		return userService.listUser(cursorString, count);
	}
	
	/**
	 * This inserts a new <code>User</code> object.
	 *
	 * @param user The object to be added.
	 * @return The object to be added.
	 * @throws CommonException the common exception
	 */
	@ApiMethod(name = "insertUser")
	public User insertUser(User user) throws CommonException {
		return userService.insertUser(user);
	}
	
	/**
	 * This updates an existing <code>User</code> object.
	 *
	 * @param user            The object to be added.
	 * @return The object to be updated.
	 * @throws CommonException the common exception
	 */
	@ApiMethod(name = "updateUser")
	public User updateUser(User user) throws CommonException {
		return userService.updateUser(user);
	}

	/**
	 * This deletes an existing <code>User</code> object.
	 *
	 * @param id            The id of the object to be deleted.
	 * @throws CommonException the proconco exception
	 */
	@ApiMethod(name = "removeUser")
	public void removeUser(@Named("id") Long id) throws CommonException {
		userService.removeUser(id);
	}
	
	/**
	 * Gets the user.
	 *
	 * @param id the id
	 * @return the user
	 */
	@ApiMethod(name = "getUser")
	public User getUser(@Named("id") Long id) {
		return userService.findRecord(id);
	}
	
	/**
	 * Inits the data.
	 */
	@ApiMethod(name = "initData", httpMethod = HttpMethod.POST, path = "initData")
	public void initData() {
		userService.initData();
	}
	
	/**
	 * Clean data.
	 */
	@ApiMethod(name = "cleanData", httpMethod = HttpMethod.POST, path = "cleanData")
	public void cleanData() {
		userService.cleanData();
	}
	
	/**
	 * Search user.
	 *
	 * @param querySearch the query search
	 * @param cursorString the cursor string
	 * @param count the count
	 * @return the collection response
	 * @throws CommonException the common exception
	 */
	@ApiMethod(name = "searchUser", httpMethod=HttpMethod.GET, path="search_user")
	public CollectionResponse<User> searchUser(
			@Nullable @Named("querySearch") String querySearch,
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("count") Integer count) throws CommonException {
		return userService.searchUser(querySearch, cursorString, count);
	}
	
	/**
	 * Login.
	 *
	 * @param login the login
	 * @param password the password
	 * @return the user
	 * @throws CommonException the common exception
	 */
	@ApiMethod(name = "login", httpMethod = HttpMethod.GET, path = "login")
	public User login(@Nullable @Named("login") String login, @Nullable @Named("password") String password)
			throws CommonException {
		return userService.login(login, password);
	}
	
	/**
	 * Activate account.
	 *
	 * @param activateKey the activate key
	 * @return the user
	 * @throws CommonException the common exception
	 */
	@ApiMethod(name = "activateAccount", httpMethod=HttpMethod.GET, path="activateAccount")
	public User activateAccount(@Nullable @Named(value = "activateKey") String activateKey) throws CommonException {
		return userService.activateAccount(activateKey);
    }
	
	/**
	 * Gets the token.
	 *
	 * @param token the token
	 * @return the token
	 * @throws CommonException the common exception
	 */
	@ApiMethod(name = "getToken", httpMethod=HttpMethod.GET, path="getToken")
	public User getToken(@Nullable @Named(value = "token") String token) throws CommonException {
		return userService.getToken(token);
	}
	
	/**
	 * Test insert.
	 *
	 * @param value the value
	 * @return the user
	 * @throws CommonException the common exception
	 */
	@ApiMethod(name = "testInsert", httpMethod=HttpMethod.GET, path="testInsert")
	public User testInsert(@Nullable @Named(value = "value") String value) throws CommonException {
		User user = new User("login" + value, "password", "uyphu@yahoo.com");
		return insertUser(user);
    }
	
	@ApiMethod(name = "addAuthority", httpMethod = HttpMethod.POST, path = "addAuthority")
	public void addAuthority(@Named("userId") Long userId, @Named("role") String role) throws CommonException {
		userService.addAuthority(userId, role);
	}
	
}
