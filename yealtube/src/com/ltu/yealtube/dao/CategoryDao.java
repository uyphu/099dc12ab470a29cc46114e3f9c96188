package com.ltu.yealtube.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.api.server.spi.response.CollectionResponse;
import com.googlecode.objectify.cmd.Query;
import com.ltu.yealtube.constants.Constant;
import com.ltu.yealtube.domain.Category;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.exception.ErrorCode;
import com.ltu.yealtube.exception.ErrorCodeDetail;

public class CategoryDao extends AbstractDao<Category> {
	
	/** The instance. */
	private static CategoryDao instance = null;
	/**
	 * Instantiates a new category dao.
	 */
	public CategoryDao() {
		super(Category.class);
	}
	
	public static CategoryDao getInstance() {
		if (instance == null) {
			instance = new CategoryDao();
		}
		return instance;
	}

	/**
	 * Inits the data.
	 */
	public void initData() {
		Category category;

		category = new Category(1L, Constant.CATEGORY_FUN, 0L, "Fun", "Fun", "Fun", "Fun");
		persist(category);
		category = new Category(2L, Constant.CATEGORY_SPORTS, 0L, "Sports", "Sports", "Sports", "Sports");
		persist(category);
		category = new Category(3L, Constant.CATEGORY_MUSIC, 0L, "Music", "Music", "Music", "Music");
		persist(category);
		category = new Category(4L, Constant.CATEGORY_18PLUS, 0L, "18+", "18+", "18+", "18+");
		persist(category);
		category = new Category(5L, Constant.CATEGORY_MOVIE, 0L, "Movie", "Movie", "Movie", "Movie");
		persist(category);
	}

	/**
	 * Clean data.
	 */
	public void cleanData() {
		List<Category> list = findAll();
		for (Category item : list) {
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
	public Query<Category> getQuery(String querySearch) throws CommonException {
		try {
			if (querySearch != null) {
				Query<Category> query;
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
				return ofy().load().type(Category.class);
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
	public CollectionResponse<Category> searchCategory(String querySearch, String cursorString, Integer count)
			throws CommonException {
		Query<Category> query = getQuery(querySearch);
		return executeQuery(query, cursorString, count);
	}

}
