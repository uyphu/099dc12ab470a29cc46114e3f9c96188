package com.ltu.yealtube.dao;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.api.server.spi.response.CollectionResponse;
import com.googlecode.objectify.cmd.Query;
import com.ltu.yealtube.constants.Constants;
import com.ltu.yealtube.domain.User;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.exception.ErrorCode;
import com.ltu.yealtube.exception.ErrorCodeDetail;
import com.ltu.yealtube.utils.AppUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDao.
 */
public class UserDao extends AbstractDao<User> {

	/**
	 * Instantiates a new user dao.
	 */
	public UserDao() {
		super(User.class);
	}

	/**
	 * Inits the data.
	 */
	public void initData() {
		User user;

		for (Long i = 1L; i < 10; i++) {
			user = new User(i, 1L, "login" + i, "password", "salt", "firstName" + i, "lastName" + i, "email" + i
					+ "@yahoo.com", false, "en", "activationKey", "resetKey", Calendar.getInstance().getTime(),
					Calendar.getInstance().getTime(), "image", "code", "ip");
			persist(user);
		}

	}

	/**
	 * Clean data.
	 */
	public void cleanData() {
		List<User> list = findAll();
		for (User item : list) {
			delete(item);
		}
	}

	/**
	 * Gets the query.
	 * 
	 * @param querySearch
	 *            the query search
	 * @return the query
	 * @throws CommonException
	 *             the proconco exception
	 */
	public Query<User> getQuery(String querySearch) throws CommonException {
		try {
			if (querySearch != null) {
				Query<User> query;
				Map<String, Object> map = new HashMap<String, Object>();
				if (querySearch.indexOf("delFlag:") != -1) {
					String[] queries = querySearch.split(":");
					map.put("delFlag", Long.parseLong(queries[1]));
					query = getQuery(map);
				} else {
					query = getQueryByName("grpName", querySearch);
				}
				return query;
			} else {
				return ofy().load().type(User.class);
			}
		} catch (Exception e) {
			throw new CommonException(ErrorCode.SYSTEM_EXCEPTION.getId(), ErrorCodeDetail.ERROR_PARSE_QUERY
					+ Constants.STRING_EXEPTION_DETAIL + e.getMessage());
		}
	}

	/**
	 * Search user.
	 * 
	 * @param querySearch
	 *            the query search
	 * @param cursorString
	 *            the cursor string
	 * @param count
	 *            the count
	 * @return the collection response
	 * @throws CommonException
	 *             the proconco exception
	 */
	public CollectionResponse<User> searchUser(String querySearch, String cursorString, Integer count)
			throws CommonException {
		Query<User> query = getQuery(querySearch);
		return executeQuery(query, cursorString, count);
	}

	/**
	 * Gets the user by name.
	 *
	 * @param login the login
	 * @return the user by name
	 */
	public User getUserByName(String login) {
		if (login != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("login", login);
			Query<User> query = getQuery(map);
			List<User> list = executeQuery(query, 1);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}

	/**
	 * Gets the user by manager.
	 * 
	 * @param manager
	 *            the manager
	 * @return the user by manager
	 */
	public User getUserByManager(String manager) {
		if (manager != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("manager", manager);
			Query<User> query = getQuery(map);
			List<User> list = executeQuery(query, 1);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}
	
	/**
	 * Find one by login.
	 *
	 * @param login the login
	 * @return the user
	 */
	public User findOneByLogin(String login) {
		if (login != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("login", login);
			Query<User> query = getQuery(map);
			List<User> list = executeQuery(query, 1);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}
	
	/**
	 * Find one by email.
	 *
	 * @param email the email
	 * @return the user
	 */
	public User findOneByEmail(String email) {
		if (email != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("email", email);
			Query<User> query = getQuery(map);
			List<User> list = executeQuery(query, 1);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}
	
	/**
	 * Find one by activation key.
	 *
	 * @param activationKey the activation key
	 * @return the user
	 */
	public User findOneByActivationKey(String activationKey) {
		if (activationKey != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("activationKey", activationKey);
			Query<User> query = getQuery(map);
			List<User> list = executeQuery(query, 1);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}
	
	/**
	 * Login.
	 *
	 * @param login the login
	 * @param password the password
	 * @return the user
	 */
	public User login(String login, String password) {
		if (login != null && password != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("login", login);
			map.put("password", AppUtils.cryptWithMD5(password));
			Query<User> query = getQuery(map);
			List<User> list = executeQuery(query, 1);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}
	
	/**
	 * Find one by token.
	 *
	 * @param token the token
	 * @return the user
	 */
	public User findOneByToken(String token) {
		if (token != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("token", token);
			Query<User> query = getQuery(map);
			List<User> list = executeQuery(query, 1);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}
	
}
