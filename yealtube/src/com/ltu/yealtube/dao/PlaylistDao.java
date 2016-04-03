package com.ltu.yealtube.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.api.server.spi.response.CollectionResponse;
import com.googlecode.objectify.cmd.Query;
import com.ltu.yealtube.constants.Constant;
import com.ltu.yealtube.domain.Playlist;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.exception.ErrorCode;
import com.ltu.yealtube.exception.ErrorCodeDetail;

/**
 * The Class PlaylistDao.
 * @author uyphu
 * @Date 2016-02-10
 * @version 1.0
 */
public class PlaylistDao extends AbstractDao<Playlist> {
	
	/** The instance. */
	private static PlaylistDao instance = null;
	
	/**
	 * Instantiates a new category dao.
	 *
	 * @return single instance of PlaylistDao
	 */
	
	public static PlaylistDao getInstance() {
		if (instance == null) {
			instance = new PlaylistDao();
		}
		return instance;
	}

	/**
	 * Instantiates a new playlist dao.
	 */
	public PlaylistDao() {
		super(Playlist.class);
	}

	/**
	 * Inits the data.
	 */
	public void initData() {

	}

	/**
	 * Clean data.
	 */
	public void cleanData() {
		List<Playlist> list = findAll();
		for (Playlist item : list) {
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
	public Query<Playlist> getQuery(String querySearch) throws CommonException {
		try {
			if (querySearch != null) {
				Query<Playlist> query;
				Map<String, Object> map = new HashMap<String, Object>();
				if (querySearch.indexOf("status:") != -1) {
					String[] queries = querySearch.split(":");
					map.put("status", queries[1]);
					query = getQuery(map);
				} else {
					query = getQueryByName("title", querySearch);
				}
				return query;
			} else {
				return ofy().load().type(Playlist.class);
			}
		} catch (Exception e) {
			throw new CommonException(ErrorCode.SYSTEM_EXCEPTION.getId(), ErrorCodeDetail.ERROR_PARSE_QUERY
					+ Constant.STRING_EXEPTION_DETAIL + e.getMessage());
		}
	}

	/**
	 * Search playlist.
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
	public CollectionResponse<Playlist> searchPlaylist(String querySearch, String cursorString, Integer count)
			throws CommonException {
		Query<Playlist> query = getQuery(querySearch);
		return executeQuery(query, cursorString, count);
	}

}
