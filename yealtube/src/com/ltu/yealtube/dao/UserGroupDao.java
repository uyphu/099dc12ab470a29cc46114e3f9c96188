package com.ltu.yealtube.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.api.server.spi.response.CollectionResponse;
import com.googlecode.objectify.cmd.Query;
import com.ltu.yealtube.constants.AuthorityConstants;
import com.ltu.yealtube.constants.Constant;
import com.ltu.yealtube.domain.UserGroup;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.exception.ErrorCode;
import com.ltu.yealtube.exception.ErrorCodeDetail;

/**
 * The Class UserGroupDao.
 * @author uyphu
 */
public class UserGroupDao extends AbstractDao<UserGroup> {
	
	/** The instance. */
	private static UserGroupDao instance = null;
	
	/**
	 * Gets the single instance of UserGroupDao.
	 *
	 * @return single instance of UserGroupDao
	 */
	public static UserGroupDao getInstance() {
		if (instance == null) {
			instance = new UserGroupDao();
		}
		return instance;
	}

	/**
	 * Instantiates a new userGroup dao.
	 */
	public UserGroupDao() {
		super(UserGroup.class);
	}

	/**
	 * Inits the data.
	 */
	public void initData() {
		UserGroup userGroup;

		userGroup = new UserGroup(1L, "Admin", "", AuthorityConstants.ROLE_ADMIN);
		persist(userGroup);
		
		userGroup = new UserGroup(2L, "User", "", AuthorityConstants.ROLE_USER);
		persist(userGroup);
		
		userGroup = new UserGroup(3L, "Edit", "", AuthorityConstants.ROLE_EDIT);
		persist(userGroup);
		
		userGroup = new UserGroup(4L, "Anonymous", "", AuthorityConstants.ROLE_ANONYMOUS);
		persist(userGroup);
		
		userGroup = new UserGroup(5L, "Read Only", "", AuthorityConstants.ROLE_READONLY);
		persist(userGroup);

	}

	/**
	 * Clean data.
	 */
	public void cleanData() {
		List<UserGroup> list = findAll();
		for (UserGroup item : list) {
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
	public Query<UserGroup> getQuery(String querySearch) throws CommonException {
		try {
			if (querySearch != null) {
				Query<UserGroup> query;
				Map<String, Object> map = new HashMap<String, Object>();
				if (querySearch.indexOf("delFlag:") != -1) {
					String[] queries = querySearch.split(":");
					map.put("delFlag", Long.parseLong(queries[1]));
					query = getQuery(map);
				} else {
					query = getQueryByName("name", querySearch);
				}
				return query;
			} else {
				return ofy().load().type(UserGroup.class);
			}
		} catch (Exception e) {
			throw new CommonException(ErrorCode.SYSTEM_EXCEPTION.getId(), ErrorCodeDetail.ERROR_PARSE_QUERY
					+ Constant.STRING_EXEPTION_DETAIL + e.getMessage());
		}
	}

	/**
	 * Search userGroup.
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
	public CollectionResponse<UserGroup> searchUserGroup(String querySearch, String cursorString, Integer count)
			throws CommonException {
		Query<UserGroup> query = getQuery(querySearch);
		return executeQuery(query, cursorString, count);
	}

	/**
	 * Gets the userGroup by name.
	 * 
	 * @param name
	 *            the name
	 * @return the userGroup by name
	 */
	public UserGroup getUserGroupByName(String name) {
		if (name != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", name);
			Query<UserGroup> query = getQuery(map);
			List<UserGroup> list = executeQuery(query, 1);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}

}
