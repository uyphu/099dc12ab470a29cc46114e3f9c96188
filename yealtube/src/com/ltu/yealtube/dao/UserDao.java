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
			user = new User(i, 1L, "username" + i, "password", "salt", "firstname" + i, "lastname" + i, "email" + i
					+ "@yahoo.com", "image", "code", "ip", 1, Calendar.getInstance().getTime());
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
	 * @param name
	 *            the name
	 * @return the user by name
	 */
	public User getUserByName(String name) {
		if (name != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("grpName", name);
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
}
