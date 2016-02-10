package com.ltu.yealtube.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.api.server.spi.response.CollectionResponse;
import com.googlecode.objectify.cmd.Query;
import com.ltu.yealtube.constants.AuthorityConstants;
import com.ltu.yealtube.constants.Constant;
import com.ltu.yealtube.domain.Authority;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.exception.ErrorCode;
import com.ltu.yealtube.exception.ErrorCodeDetail;

/**
 * The Class AuthorityDao.
 * @author uyphu
 */
public class AuthorityDao extends AbstractDao<Authority>{
	
	/** The instance. */
	private static AuthorityDao instance = null;
	
	/**
	 * Gets the single instance of AuthorityDao.
	 *
	 * @return single instance of AuthorityDao
	 */
	public static AuthorityDao getInstance() {
		if (instance == null) {
			instance = new AuthorityDao();
		}
		return instance;
	}

	/**
	 * Instantiates a new authority dao.
	 */
	public AuthorityDao() {
		super(Authority.class);
	}
	
	/**
	 * Gets the authority by name.
	 *
	 * @param name the name
	 * @return the authority by name
	 */
	public Authority getAuthorityByName(String name) {
		Map<String, Object> columns = new HashMap<String, Object>();
		columns.put("name", name);
		Query<Authority> query = getQuery(columns);
		List<Authority> list = executeQuery(query, 1);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} 
		return null;
	}
	
	/**
	 * Search authority.
	 *
	 * @param querySearch the query search
	 * @param cursorString the cursor string
	 * @param count the count
	 * @return the collection response
	 * @throws CommonException the common exception
	 */
	public CollectionResponse<Authority> searchAuthority(String querySearch, String cursorString, Integer count)
			throws CommonException {
		Query<Authority> query = getQuery(querySearch);
		return executeQuery(query, cursorString, count);
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
	public Query<Authority> getQuery(String querySearch) throws CommonException {
		try {
			if (querySearch != null) {
				Query<Authority> query;
				Map<String, Object> map = new HashMap<String, Object>();
				if (querySearch.indexOf("keyword:") != -1) {
					String[] queries = querySearch.split(":");
					map.put("metaKeyword", Long.parseLong(queries[1]));
					query = getQuery(map);
				} else {
					query = getQueryByName("name", querySearch);
				}
				return query;
			} else {
				return ofy().load().type(Authority.class);
			}
		} catch (Exception e) {
			throw new CommonException(ErrorCode.SYSTEM_EXCEPTION.getId(), ErrorCodeDetail.ERROR_PARSE_QUERY
					+ Constant.STRING_EXEPTION_DETAIL + e.getMessage());
		}
	}

	@Override
	public void initData() {
		Authority authority;
		
		authority = new Authority(1L, AuthorityConstants.ROLE_ADMIN);
		persist(authority);
		authority = new Authority(2L, AuthorityConstants.ROLE_USER);
		persist(authority);
		authority = new Authority(3L, AuthorityConstants.ROLE_ANONYMOUS);
		persist(authority);
		authority = new Authority(4L, AuthorityConstants.ROLE_READONLY);
		persist(authority);
		authority = new Authority(5L, AuthorityConstants.ROLE_EDIT);
		persist(authority);
		authority = new Authority(6L, AuthorityConstants.ROLE_APPROVAL);
		persist(authority);
		authority = new Authority(7L, AuthorityConstants.ROLE_MANAGER);
		persist(authority);
		authority = new Authority(8L, AuthorityConstants.ROLE_LEADER);
		persist(authority);
	}

	@Override
	public void cleanData() {
		List<Authority> authorities = findAll();
		for (Authority authority : authorities) {
			delete(authority);
		}
	}

}
