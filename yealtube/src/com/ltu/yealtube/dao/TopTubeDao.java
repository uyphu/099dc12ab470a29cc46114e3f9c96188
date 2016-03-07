package com.ltu.yealtube.dao;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.api.server.spi.response.CollectionResponse;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.cmd.Query;
import com.ltu.yealtube.constants.Constant;
import com.ltu.yealtube.domain.TopTube;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.exception.ErrorCode;
import com.ltu.yealtube.exception.ErrorCodeDetail;

/**
 * The Class TopTubeDao.
 * 
 * @author uyphu
 * 
 */
public class TopTubeDao extends AbstractDao<TopTube> {

	/** The instance. */
	private static TopTubeDao instance = null;

	/**
	 * Instantiates a new topMovie dao.
	 */
	public TopTubeDao() {
		super(TopTube.class);
	}

	/**
	 * Gets the single instance of TopTubeDao.
	 * 
	 * @return single instance of TopTubeDao
	 */
	public static TopTubeDao getInstance() {
		if (instance == null) {
			instance = new TopTubeDao();
		}
		return instance;
	}

	/**
	 * Inits the data.
	 */
	public void initData() {
		TopTube topMovie;

		for (Long i = 1L; i < 10; i++) {
			topMovie = new TopTube(String.valueOf(i), "title" + String.valueOf(i), "description" + String.valueOf(i), 1L, 0, 1,
					null, null);
			persist(topMovie);
		}

	}

	/**
	 * Clean data.
	 */
	public void cleanData() {
		List<TopTube> list = findAll();
		for (TopTube item : list) {
			delete(item);
		}
	}

	/**
	 * Clean data.
	 * 
	 * @param status
	 *            the status
	 * @throws CommonException
	 *             the common exception
	 */
	public void cleanData(Integer status) throws CommonException {
		CollectionResponse<TopTube> list;
		list = searchTopTube("status:" + status.toString(), null, null);
		for (TopTube item : list.getItems()) {
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
	public Query<TopTube> getQuery(String querySearch) throws CommonException {
		try {
			if (querySearch != null) {
				Query<TopTube> query;
				String[] fields = querySearch.split("@@");
				Map<String, Object> map = new HashMap<String, Object>();
				if (fields != null && fields.length > 1) {
					for (String field : fields) {
						if (field.indexOf("status:") != -1) {
							String[] queries = field.split(":");
							map.put("status", Long.parseLong(queries[1]));
						} else if (field.indexOf("categoryId:") != -1) {
							String[] queries = field.split(":");
							map.put("categoryRef", Ref.create(CategoryDao.getInstance().find(Long.parseLong(queries[1]))));
						} 
					}
					query = getQuery(map);
				} else {
					if (querySearch.indexOf("status:") != -1) {
						String[] queries = querySearch.split(":");
						map.put("status", Long.parseLong(queries[1]));
						query = getQuery(map);
					} else if (querySearch.indexOf("categoryId:") != -1) {
						String[] queries = querySearch.split(":");
						map.put("categoryRef", Ref.create(CategoryDao.getInstance().find(Long.parseLong(queries[1]))));
						query = getQuery(map);
					} else {
						query = getQueryByName("title", querySearch);
					}
				}
				return query;
			} else {
				return ofy().load().type(TopTube.class);
			}
		} catch (Exception e) {
			throw new CommonException(ErrorCode.SYSTEM_EXCEPTION.getId(), ErrorCodeDetail.ERROR_PARSE_QUERY
					+ Constant.STRING_EXEPTION_DETAIL + e.getMessage());
		}
	}

	/**
	 * Search topMovie.
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
	public CollectionResponse<TopTube> searchTopTube(String querySearch, String cursorString, Integer count)
			throws CommonException {
		Query<TopTube> query = getQuery(querySearch);
		return executeQuery(query, cursorString, count);
	}

	/**
	 * Gets the top plays.
	 * 
	 * @param cursorString
	 *            the cursor string
	 * @param count
	 *            the count
	 * @return the top plays
	 * @throws CommonException
	 *             the common exception
	 */
	public CollectionResponse<TopTube> getTopPlays(String cursorString, Integer count) throws CommonException {
		Query<TopTube> query = ofy().load().type(TopTube.class).filter("status", Constant.APPROVED_STATUS);
		query = query.order("-viewCount");
		return executeQuery(query, cursorString, count);
	}

	/**
	 * Gets the top musics.
	 * 
	 * @param cursorString
	 *            the cursor string
	 * @param count
	 *            the count
	 * @return the top musics
	 * @throws CommonException
	 *             the common exception
	 */
	public CollectionResponse<TopTube> getTopMusics(String cursorString, Integer count) throws CommonException {
		Query<TopTube> query = ofy().load().type(TopTube.class).filter("status", Constant.APPROVED_STATUS)
				.filter("categoryRef", Ref.create(CategoryDao.getInstance().find(Constant.CATEGORY_MUSIC_ID)));
		query = query.order("-viewCount");
		return executeQuery(query, cursorString, count);
	}

	/**
	 * Gets the top movies.
	 * 
	 * @param cursorString
	 *            the cursor string
	 * @param count
	 *            the count
	 * @return the top movies
	 * @throws CommonException
	 *             the common exception
	 */
	public CollectionResponse<TopTube> getTopMovies(String cursorString, Integer count) throws CommonException {
		Query<TopTube> query = ofy().load().type(TopTube.class).filter(" status = ", Constant.APPROVED_STATUS)
				.filter(" categoryRef = ", Ref.create(CategoryDao.getInstance().find(Constant.CATEGORY_MOVIE_ID)));
		query = query.order("-viewCount");
		return executeQuery(query, cursorString, count);
	}
	
	/**
	 * Gets the deleted tubes.
	 *
	 * @param cursorString the cursor string
	 * @param count the count
	 * @return the deleted tubes
	 * @throws CommonException the common exception
	 */
	public CollectionResponse<TopTube> getDeletedTubes(String cursorString, Integer count) throws CommonException {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -7);
		Query<TopTube> query = ofy().load().type(TopTube.class).filter(" createdAt < ", calendar.getTime());
		return executeQuery(query, cursorString, count);
	}

	/**
	 * Gets the topMovie by title.
	 * 
	 * @param title
	 *            the title
	 * @param cursorString
	 *            the cursor string
	 * @param count
	 *            the count
	 * @return the topMovie by title
	 * @throws CommonException
	 *             the common exception
	 */
	public CollectionResponse<TopTube> getTopTubeByTitle(String title, String cursorString, Integer count)
			throws CommonException {
		if (title != null) {
			Query<TopTube> query = getQueryByName("title", title).order("-createdAt");
			return executeQuery(query, cursorString, count);
		}
		return null;
	}

	@Override
	public CollectionResponse<TopTube> list(String cursorString, Integer count) {
		Query<TopTube> query = getQuery().order("-createdAt");
		return executeQuery(query, cursorString, count);
	}
	
	/**
	 * Gets the query.
	 *
	 * @param field the field
	 * @param value the value
	 * @return the query
	 */
	public Query<TopTube> getQuery(String field, Object value) {
		return ofy().load().type(TopTube.class).filter(field, value);
	}
	
	/**
	 * Search tubes.
	 *
	 * @param field the field
	 * @param value the value
	 * @param cursorString the cursor string
	 * @param count the count
	 * @return the collection response
	 * @throws CommonException the common exception
	 */
	public CollectionResponse<TopTube> searchTubes(String field, Object value, String cursorString, Integer count)
			throws CommonException {
		Query<TopTube> query = getQuery(field, value);
		return executeQuery(query, cursorString, count);
	}

}
