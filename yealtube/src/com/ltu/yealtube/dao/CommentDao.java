package com.ltu.yealtube.dao;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.api.server.spi.response.CollectionResponse;
import com.googlecode.objectify.cmd.Query;
import com.ltu.yealtube.constants.Constant;
import com.ltu.yealtube.domain.Comment;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.exception.ErrorCode;
import com.ltu.yealtube.exception.ErrorCodeDetail;

/**
 * The Class CommentDao.
 * @author uyphu
 * @Date 2016-02-10
 * @version 1.0
 */
public class CommentDao extends AbstractDao<Comment> {
	
	/** The instance. */
	private static CommentDao instance = null;
	
	/**
	 * Instantiates a new category dao.
	 *
	 * @return single instance of CommentDao
	 */
	
	public static CommentDao getInstance() {
		if (instance == null) {
			instance = new CommentDao();
		}
		return instance;
	}

	/**
	 * Instantiates a new comment dao.
	 */
	public CommentDao() {
		super(Comment.class);
	}

	/**
	 * Inits the data.
	 */
	public void initData() {
		Comment comment;

		for (Long i = 1L; i < 10; i++) {
			comment = new Comment(i, "1", 1L, "text", 5, 1, Calendar.getInstance().getTime(), Calendar.getInstance()
					.getTime());
			persist(comment);
		}

	}

	/**
	 * Clean data.
	 */
	public void cleanData() {
		List<Comment> list = findAll();
		for (Comment item : list) {
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
	public Query<Comment> getQuery(String querySearch) throws CommonException {
		try {
			if (querySearch != null) {
				Query<Comment> query;
				Map<String, Object> map = new HashMap<String, Object>();
				if (querySearch.indexOf("tubeId:") != -1) {
					String[] queries = querySearch.split(":");
					map.put("tubeId", queries[1]);
					query = getQuery(map);
				} else {
					query = getQueryByName("text", querySearch);
				}
				return query;
			} else {
				return ofy().load().type(Comment.class);
			}
		} catch (Exception e) {
			throw new CommonException(ErrorCode.SYSTEM_EXCEPTION.getId(), ErrorCodeDetail.ERROR_PARSE_QUERY
					+ Constant.STRING_EXEPTION_DETAIL + e.getMessage());
		}
	}

	/**
	 * Search comment.
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
	public CollectionResponse<Comment> searchComment(String querySearch, String cursorString, Integer count)
			throws CommonException {
		Query<Comment> query = getQuery(querySearch);
		return executeQuery(query, cursorString, count);
	}

}
