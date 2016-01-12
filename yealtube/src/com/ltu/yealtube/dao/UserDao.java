package com.ltu.yealtube.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.api.server.spi.response.CollectionResponse;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;
import com.ltu.yealtube.constants.Constants;
import com.ltu.yealtube.domain.Authority;
import com.ltu.yealtube.domain.User;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.exception.ErrorCode;
import com.ltu.yealtube.exception.ErrorCodeDetail;
import com.ltu.yealtube.utils.AppUtils;

/**
 * The Class UserDao.
 */
public class UserDao extends AbstractDao<User> {
	
	private static UserDao instance = null;
	
	/**
	 * Instantiates a new user dao.
	 */
	public UserDao() {
		super(User.class);
	}
	
	public static UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
		}
		return instance;
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
	 * Find one by login.
	 *
	 * @param login the login
	 * @param userType the user type
	 * @return the user
	 */
	public User findOneByLogin(String login, String userType) {
		if (login != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("login", login);
			map.put("type", userType);
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
	public User findOneByEmail(String email, String type) {
		if (email != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("email", email);
			map.put("type", type);
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
	
	/**
	 * Adds the role.
	 * 
	 * @param login
	 *            the login
	 * @param role
	 *            the role
	 */
	public void addRole(String login, String role) throws CommonException {
		User user = findOneByLogin(login);
		AuthorityDao authorityDao = new AuthorityDao();
		Authority authority = authorityDao.getAuthorityByName(role);
		if (user != null && authority != null) {
			Key<Authority> key = Key.create(Authority.class, authority.getId());
			List<Key<Authority>> list = user.getAuthorityKeys();
			if (list == null) {
				list = new ArrayList<Key<Authority>>();
			}
			if (!list.contains(key)) {
				list.add(key);
				user.setAuthorityKeys(list);
				persist(user);
			} else {
				throw new CommonException(ErrorCode.CONFLICT_EXCEPTION.getId(),
						ErrorCodeDetail.ERROR_DUPLICATED_ROLE.getMsg());
			}
		} else {
			throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION.getId(),
					ErrorCodeDetail.ERROR_NOT_FOUND_ROLE.getMsg());
		}
	}
	
	public void addRole(Long userId, String role) throws CommonException {
		User user = find(userId);
		AuthorityDao authorityDao = new AuthorityDao();
		Authority authority = authorityDao.getAuthorityByName(role);
		if (user != null && authority != null) {
			Key<Authority> key = Key.create(Authority.class, authority.getId());
			List<Key<Authority>> list = user.getAuthorityKeys();
			if (list == null) {
				list = new ArrayList<Key<Authority>>();
			}
			if (!list.contains(key)) {
				list.add(key);
				user.setAuthorityKeys(list);
				persist(user);
			} else {
				throw new CommonException(ErrorCode.CONFLICT_EXCEPTION.getId(),
						ErrorCodeDetail.ERROR_DUPLICATED_ROLE.getMsg());
			}
		} else {
			throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION.getId(),
					ErrorCodeDetail.ERROR_NOT_FOUND_ROLE.getMsg());
		}
	}
	
	/**
	 * Removes the role.
	 *
	 * @param login the login
	 * @param role the role
	 * @throws CommonException the proconco exception
	 */
	public void removeRole(String login, String role) throws CommonException {
		User user = findOneByLogin(login);
		AuthorityDao authorityDao = new AuthorityDao();
		Authority authority = authorityDao.getAuthorityByName(role);
		if (user != null && authority != null) {
			Key<Authority> key = Key.create(Authority.class, authority.getId());
			List<Key<Authority>> list = user.getAuthorityKeys();
			if (list == null) {
				list = new ArrayList<Key<Authority>>();
			}
			if (list.contains(key)) {
				list.remove(key);
				user.setAuthorityKeys(list);
				persist(user);
			} else {
				throw new CommonException(ErrorCode.CONFLICT_EXCEPTION.getId(),
						ErrorCodeDetail.ERROR_DUPLICATED_ROLE.getMsg());
			}
		} else {
			throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION.getId(),
					ErrorCodeDetail.ERROR_NOT_FOUND_ROLE.getMsg());
		}
	}
	
	/**
	 * Removes the authority.
	 *
	 * @param userId the user id
	 * @param role the role
	 * @throws CommonException the proconco exception
	 */
	public void removeAuthority(Long userId, String role) throws CommonException {
		User user = find(userId);
		AuthorityDao authorityDao = new AuthorityDao();
		Authority authority = authorityDao.getAuthorityByName(role);
		if (user != null && authority != null) {
			Key<Authority> key = Key.create(Authority.class, authority.getId());
			List<Key<Authority>> list = user.getAuthorityKeys();
			if (list == null) {
				list = new ArrayList<Key<Authority>>();
			}
			if (list.contains(key)) {
				list.remove(key);
				user.setAuthorityKeys(list);
				persist(user);
			} else {
				throw new CommonException(ErrorCode.CONFLICT_EXCEPTION.getId(),
						ErrorCodeDetail.ERROR_DUPLICATED_ROLE.getMsg());
			}
		} else {
			throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION.getId(),
					ErrorCodeDetail.ERROR_NOT_FOUND_ROLE.getMsg());
		}
	}
	
	@Override
	public User update(User user) {
		User oldUser = find(user.getId());
		if (user.getFirstName() != null) {
			oldUser.setFirstName(user.getFirstName());
		}
		if (user.getLastName() != null) {
			oldUser.setLastName(user.getLastName());
		}
		if (user.getLangKey() != null) {
			oldUser.setLangKey(user.getLangKey());
		}
		if (user.getEmail() != null) {
			oldUser.setEmail(user.getEmail());
		}
		if (user.getUserGroupId() != null) {
			oldUser.setUserGroupId(user.getUserGroupId());
		}
		
		if (user.isActivated() != oldUser.isActivated()) {
			oldUser.setActivated(user.isActivated());
		}
		if (!user.getPassword().equals(oldUser.getPassword())) {
			oldUser.setPassword(AppUtils.cryptWithMD5(user.getPassword()));
		}
		return super.update(oldUser);
	}
	
}
