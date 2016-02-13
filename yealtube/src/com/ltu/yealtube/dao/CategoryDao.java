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

/**
 * The Class CategoryDao.
 */
public class CategoryDao extends AbstractDao<Category> {
	
	/** The instance. */
	private static CategoryDao instance = null;
	/**
	 * Instantiates a new category dao.
	 */
	public CategoryDao() {
		super(Category.class);
	}
	
	/**
	 * Gets the single instance of CategoryDao.
	 *
	 * @return single instance of CategoryDao
	 */
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

		category = new Category(1L, Constant.CATEGORY_FUN, 0L, "Fun", "Fun", "Fun", "fun, comedy, vui, hai, funny, cuoi");
		persist(category);
		category = new Category(2L, Constant.CATEGORY_SPORTS, 0L, "Sports", "Sports", "Sports", "sports, football, volleyball, tennis, athletic");
		persist(category);
		category = new Category(3L, Constant.CATEGORY_MUSIC, 0L, "Music", "Music", "Music", "music, shows");
		persist(category);
		category = new Category(4L, Constant.CATEGORY_18PLUS, 0L, "18+", "18+", "18+", "18+, sexy, naked, nude, hot girl, sweet, adult, naughty, tinh duc, bikini, nguoi lon, tinh cam");
		persist(category);
		category = new Category(5L, Constant.CATEGORY_MOVIE, 0L, "Movie", "Movie", "Movie", "movies, movie, film");
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
					map.put("keyword", Long.parseLong(queries[1]));
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
	
	/**
	 * Find one by name.
	 *
	 * @param name the name
	 * @return the category
	 */
	public Category findOneByName(String name) {
		if (name != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", name);
			Query<Category> query = getQuery(map);
			List<Category> list = executeQuery(query, 1);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}
	
}
