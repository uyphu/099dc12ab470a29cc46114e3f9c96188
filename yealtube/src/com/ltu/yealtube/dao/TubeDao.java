package com.ltu.yealtube.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.api.server.spi.response.CollectionResponse;
import com.googlecode.objectify.cmd.Query;
import com.ltu.yealtube.constants.Constants;
import com.ltu.yealtube.domain.Tube;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.exception.ErrorCode;
import com.ltu.yealtube.exception.ErrorCodeDetail;

public class TubeDao extends AbstractDao<Tube> {
	
	private static TubeDao instance = null;

	/**
	 * Instantiates a new tube dao.
	 */
	public TubeDao() {
		super(Tube.class);
	}
	
	public static TubeDao getInstance() {
		if (instance == null) {
			instance = new TubeDao();
		}
		return instance;
	}

	/**
	 * Inits the data.
	 */
	public void initData() {
		Tube tube;

		for (Long i = 1L; i < 10; i++) {
			tube = new Tube(i.toString(), 1L, "name" + i, "description", 1);
			persist(tube);
		}

	}

	/**
	 * Clean data.
	 */
	public void cleanData() {
		List<Tube> list = findAll();
		for (Tube item : list) {
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
	public Query<Tube> getQuery(String querySearch) throws CommonException {
		try {
			if (querySearch != null) {
				Query<Tube> query;
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
				return ofy().load().type(Tube.class);
			}
		} catch (Exception e) {
			throw new CommonException(ErrorCode.SYSTEM_EXCEPTION.getId(), ErrorCodeDetail.ERROR_PARSE_QUERY
					+ Constants.STRING_EXEPTION_DETAIL + e.getMessage());
		}
	}

	/**
	 * Search tube.
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
	public CollectionResponse<Tube> searchTube(String querySearch, String cursorString, Integer count)
			throws CommonException {
		Query<Tube> query = getQuery(querySearch);
		return executeQuery(query, cursorString, count);
	}

	/**
	 * Gets the tube by name.
	 * 
	 * @param name
	 *            the name
	 * @return the tube by name
	 */
	public CollectionResponse<Tube> getTubeByName(String name, String cursorString, Integer count) throws CommonException {
		if (name != null) {
			Query<Tube> query = getQueryByName("name", name);
			return executeQuery(query, cursorString, count);
		}
		return null;
	}
	
}
