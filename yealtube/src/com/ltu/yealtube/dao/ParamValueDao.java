package com.ltu.yealtube.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.api.server.spi.response.CollectionResponse;
import com.googlecode.objectify.cmd.Query;
import com.ltu.yealtube.constants.Constant;
import com.ltu.yealtube.domain.ParamValue;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.exception.ErrorCode;
import com.ltu.yealtube.exception.ErrorCodeDetail;

public class ParamValueDao extends AbstractDao<ParamValue> {
	
	/** The instance. */
	private static ParamValueDao instance = null;
	
	/**
	 * Gets the single instance of AuthorityDao.
	 *
	 * @return single instance of AuthorityDao
	 */
	public static ParamValueDao getInstance() {
		if (instance == null) {
			instance = new ParamValueDao();
		}
		return instance;
	}

	private ParamValueDao() {
		super(ParamValue.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initData() throws CommonException {
		ParamValue paramValue;
		
		paramValue = new ParamValue("18plus", "sexy, naked, nude, hot, sweet");
		persist(paramValue);
		
	}

	@Override
	public void cleanData() throws CommonException {
		List<ParamValue> list = findAll();
		for (ParamValue item : list) {
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
	public Query<ParamValue> getQuery(String querySearch) throws CommonException {
		try {
			if (querySearch != null) {
				Query<ParamValue> query;
				Map<String, Object> map = new HashMap<String, Object>();
				if (querySearch.indexOf("value:") != -1) {
					String[] queries = querySearch.split(":");
					map.put("value", Long.parseLong(queries[1]));
					query = getQuery(map);
				} else {
					query = getQueryByName("value", querySearch);
				}
				return query;
			} else {
				return ofy().load().type(ParamValue.class);
			}
		} catch (Exception e) {
			throw new CommonException(ErrorCode.SYSTEM_EXCEPTION.getId(), ErrorCodeDetail.ERROR_PARSE_QUERY
					+ Constant.STRING_EXEPTION_DETAIL + e.getMessage());
		}
	}

	/**
	 * Search category.
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
	public CollectionResponse<ParamValue> searchParamValue(String querySearch, String cursorString, Integer count)
			throws CommonException {
		Query<ParamValue> query = getQuery(querySearch);
		return executeQuery(query, cursorString, count);
	}

}
