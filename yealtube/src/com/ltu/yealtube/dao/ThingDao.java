package com.ltu.yealtube.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.api.server.spi.response.CollectionResponse;
import com.googlecode.objectify.cmd.Query;
import com.ltu.yealtube.constants.AuthorityConstants;
import com.ltu.yealtube.constants.Constant;
import com.ltu.yealtube.domain.Thing;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.exception.ErrorCode;
import com.ltu.yealtube.exception.ErrorCodeDetail;

/**
 * The Class ThingDao.
 * @author uyphu
 */
public class ThingDao extends RemoteAbstractDao<Thing>{
	
	/** The instance. */
	private static ThingDao instance = null;
	
	/**
	 * Gets the single instance of ThingDao.
	 *
	 * @return single instance of ThingDao
	 */
	public static ThingDao getInstance() {
		if (instance == null) {
			instance = new ThingDao();
		}
		return instance;
	}

	/**
	 * Instantiates a new thing dao.
	 */
	public ThingDao() {
		super(Thing.class);
	}
	
	/**
	 * Gets the thing by name.
	 *
	 * @param name the name
	 * @return the thing by name
	 */
	public Thing getThingByName(String name) {
		Map<String, Object> columns = new HashMap<String, Object>();
		columns.put("name", name);
		Query<Thing> query = getQuery(columns);
		List<Thing> list = executeQuery(query, 1);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} 
		return null;
	}
	
	/**
	 * Search thing.
	 *
	 * @param querySearch the query search
	 * @param cursorString the cursor string
	 * @param count the count
	 * @return the collection response
	 * @throws CommonException the common exception
	 */
	public CollectionResponse<Thing> searchThing(String querySearch, String cursorString, Integer count)
			throws CommonException {
		Query<Thing> query = getQuery(querySearch);
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
	public Query<Thing> getQuery(String querySearch) throws CommonException {
		try {
			if (querySearch != null) {
				Query<Thing> query;
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
				return ofy().load().type(Thing.class);
			}
		} catch (Exception e) {
			throw new CommonException(ErrorCode.SYSTEM_EXCEPTION.getId(), ErrorCodeDetail.ERROR_PARSE_QUERY
					+ Constant.STRING_EXEPTION_DETAIL + e.getMessage());
		}
	}

	@Override
	public void initData() {
		Thing thing;
		
		thing = new Thing(1L, AuthorityConstants.ROLE_ADMIN);
		persist(thing);
		thing = new Thing(2L, AuthorityConstants.ROLE_USER);
		persist(thing);
		thing = new Thing(3L, AuthorityConstants.ROLE_ANONYMOUS);
		persist(thing);
		thing = new Thing(4L, AuthorityConstants.ROLE_READONLY);
		persist(thing);
		thing = new Thing(5L, AuthorityConstants.ROLE_EDIT);
		persist(thing);
		thing = new Thing(6L, AuthorityConstants.ROLE_APPROVAL);
		persist(thing);
		thing = new Thing(7L, AuthorityConstants.ROLE_MANAGER);
		persist(thing);
		thing = new Thing(8L, AuthorityConstants.ROLE_LEADER);
		persist(thing);
	}

	@Override
	public void cleanData() {
		List<Thing> authorities = findAll();
		for (Thing thing : authorities) {
			delete(thing);
		}
	}

}
