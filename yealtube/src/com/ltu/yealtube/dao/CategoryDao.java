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
	/**
	 * Instantiates a new category dao.
	 */
	public CategoryDao() {
		super(Category.class);
	}

	/**
	 * Inits the data.
	 */
	public void initData() {
		Category category;

		for (Long i = 1L; i < 10; i++) {
			category = new Category(i, "name" + i, 0L, "description", "metaTitle", "metaDescription", "metaKeyword");
			persist(category);
		}

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

	/**
	 * Gets the category by name.
	 * 
	 * @param name
	 *            the name
	 * @return the category by name
	 */
	public Category getCategoryByName(String name) {
		if (name != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("grpName", name);
			Query<Category> query = getQuery(map);
			List<Category> list = executeQuery(query, 1);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}

	/**
	 * Gets the category by manager.
	 * 
	 * @param manager
	 *            the manager
	 * @return the category by manager
	 */
	public Category getCategoryByManager(String manager) {
		if (manager != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("manager", manager);
			Query<Category> query = getQuery(map);
			List<Category> list = executeQuery(query, 1);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}
}
