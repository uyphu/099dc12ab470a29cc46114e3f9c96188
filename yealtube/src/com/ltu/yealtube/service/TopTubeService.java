package com.ltu.yealtube.service;

import java.util.Calendar;

import org.apache.log4j.Logger;

import com.google.api.client.http.HttpStatusCodes;
import com.google.api.server.spi.response.CollectionResponse;
import com.ltu.yealtube.constants.Constant;
import com.ltu.yealtube.dao.CategoryDao;
import com.ltu.yealtube.dao.TopTubeDao;
import com.ltu.yealtube.domain.Category;
import com.ltu.yealtube.domain.TopTube;
import com.ltu.yealtube.domain.Tube;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.exception.ErrorCodeDetail;
import com.ltu.yealtube.utils.YoutubeUtils;

/**
 * The Class TubeService.
 * @author uyphu
 */
public class TopTubeService {
	
	/** The log. */
	private final Logger log = Logger.getLogger(TopTubeService.class);
	
	/** The topTube dao. */
	private TopTubeDao topTubeDao = TopTubeDao.getInstance();
	
	/** The instance. */
	private static TopTubeService instance = null;
	
	/**
	 * Gets the single instance of TopTubeService.
	 *
	 * @return single instance of TopTubeService
	 */
	public static TopTubeService getInstance() {
		if (instance == null) {
			instance = new TopTubeService();
		}
		return instance;
	}
	
	/**
	 * List topTube.
	 *
	 * @param cursorString the cursor string
	 * @param count the count
	 * @return the collection response
	 */
	public CollectionResponse<TopTube> listTopTube(String cursorString, Integer count) {
		return topTubeDao.list(cursorString, count);
	}
	
	/**
	 * Insert.
	 *
	 * @param topTube the topTube
	 * @return the topTube
	 * @throws CommonException the common exception
	 */
	public TopTube insert(TopTube topTube) throws CommonException {
		if (topTube != null && topTube.getId() != null) {
			if (containsTopTube(topTube)) {
				throw new CommonException(HttpStatusCodes.STATUS_CODE_FOUND, ErrorCodeDetail.ERROR_EXIST_OBJECT.getMsg());
			}
			topTube.setCreatedAt(Calendar.getInstance().getTime());
			topTube.setModifiedAt(Calendar.getInstance().getTime());
			topTube.setStatus(Constant.PENDING_STATUS);
			return topTubeDao.persist(topTube);
		} else {
			throw new CommonException(HttpStatusCodes.STATUS_CODE_BAD_GATEWAY, ErrorCodeDetail.ERROR_INPUT_NOT_VALID.getMsg());
		}
	}
	
	/**
	 * Insert.
	 *
	 * @param youtopTubeId the youtopTube id
	 * @return the topTube
	 * @throws CommonException the common exception
	 */
	public TopTube insert(String id) throws CommonException {
		if (id != null) {
			if (containsTopTube(id)) {
				throw new CommonException(HttpStatusCodes.STATUS_CODE_FOUND, ErrorCodeDetail.ERROR_EXIST_OBJECT.getMsg());
			}
			Tube tube = YoutubeUtils.getTube(id);
			TopTube topTube = new TopTube(tube);
			topTube.setCreatedAt(Calendar.getInstance().getTime());
			topTube.setModifiedAt(Calendar.getInstance().getTime());
			topTube.setStatus(Constant.APPROVED_STATUS);
			topTube.setUserId(Constant.ADMIN_ID);
			Category category = YoutubeUtils.getCategory(id);
			if (category != null) {
				topTube.setCategory(category);
				return topTubeDao.persist(topTube);
			} else {
				throw new CommonException(HttpStatusCodes.STATUS_CODE_FORBIDDEN, ErrorCodeDetail.ERROR_INPUT_NOT_VALID.getMsg());
			}
			
		} else {
			throw new CommonException(HttpStatusCodes.STATUS_CODE_BAD_GATEWAY, ErrorCodeDetail.ERROR_INPUT_NOT_VALID.getMsg());
		}
	}
	
	public TopTube insert(String id, Float rating) throws CommonException {
		if (id != null) {
			if (containsTopTube(id)) {
				throw new CommonException(HttpStatusCodes.STATUS_CODE_FOUND, ErrorCodeDetail.ERROR_EXIST_OBJECT.getMsg());
			}
			Tube tube = YoutubeUtils.getTube(id);
			TopTube topTube = new TopTube(tube);
			topTube.setCreatedAt(Calendar.getInstance().getTime());
			topTube.setModifiedAt(Calendar.getInstance().getTime());
			topTube.setStatus(Constant.APPROVED_STATUS);
			topTube.setUserId(Constant.ADMIN_ID);
			if (rating != null) {
				topTube.setRating(rating);
			}
			Category category = YoutubeUtils.getCategory(id);
			if (category != null) {
				topTube.setCategory(category);
				return topTubeDao.persist(topTube);
			} else {
				throw new CommonException(HttpStatusCodes.STATUS_CODE_FORBIDDEN, ErrorCodeDetail.ERROR_CATEGORY_NOT_FOUND.getMsg());
			}
			
		} else {
			throw new CommonException(HttpStatusCodes.STATUS_CODE_BAD_GATEWAY, ErrorCodeDetail.ERROR_INPUT_NOT_VALID.getMsg());
		}
	}
	
	/**
	 * Update.
	 *
	 * @param topTube the topTube
	 * @return the topTube
	 * @throws CommonException the common exception
	 */
	public TopTube update(TopTube topTube) throws CommonException {
		if (topTube != null && topTube.getId() != null) {
			if (!containsTopTube(topTube)) {
				throw new CommonException(HttpStatusCodes.STATUS_CODE_NOT_FOUND,
						ErrorCodeDetail.ERROR_RECORD_NOT_FOUND.getMsg());
			}
			topTube.setModifiedAt(Calendar.getInstance().getTime());
			if (topTube.getCategory() != null) {
				//topTube.setCategoryKey(Key.create(Category.class, topTube.getCategory().getId()));
				topTube.setCategory(CategoryDao.getInstance().find(topTube.getCategory().getId()));
			}
			return topTubeDao.update(topTube);
		} else {
			throw new CommonException(HttpStatusCodes.STATUS_CODE_BAD_GATEWAY, ErrorCodeDetail.ERROR_INPUT_NOT_VALID.getMsg());
		}
	}
	
	/**
	 * Update.
	 *
	 * @param tube the tube
	 * @return the top tube
	 * @throws CommonException the common exception
	 */
	public TopTube update(Tube tube) throws CommonException {
		if (tube != null && tube.getId() != null) {
			TopTube topTube = new TopTube(tube);
			if (!containsTopTube(topTube)) {
				return insert(topTube);
			} else {
				topTube.setModifiedAt(Calendar.getInstance().getTime());
				if (topTube.getCategory() != null) {
					topTube.setCategory(CategoryDao.getInstance().find(topTube.getCategory().getId()));
				}
				return topTubeDao.update(topTube);
			}
		} else {
			throw new CommonException(HttpStatusCodes.STATUS_CODE_BAD_GATEWAY, ErrorCodeDetail.ERROR_INPUT_NOT_VALID.getMsg());
		}
	}
	
	/**
	 * Delete.
	 *
	 * @param topTube the topTube
	 * @throws CommonException the common exception
	 */
	public void delete(TopTube topTube) throws CommonException {
		if (topTube != null && topTube.getId() != null) {
			if (!containsTopTube(topTube)) {
				throw new CommonException(HttpStatusCodes.STATUS_CODE_NOT_FOUND,
						ErrorCodeDetail.ERROR_RECORD_NOT_FOUND.getMsg());
			}
			topTube.setModifiedAt(Calendar.getInstance().getTime());
			topTubeDao.delete(topTube);
		} else {
			throw new CommonException(HttpStatusCodes.STATUS_CODE_BAD_GATEWAY, ErrorCodeDetail.ERROR_INPUT_NOT_VALID.getMsg());
		}
	}
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 * @throws CommonException the common exception
	 */
	public void delete(String id) throws CommonException {
		TopTube topTube = findRecord(id);
		delete(topTube);
	}
	
	/**
	 * Contains topTube.
	 *
	 * @param topTube the topTube
	 * @return true, if successful
	 */
	private boolean containsTopTube(TopTube topTube) {
		return containsTopTube(topTube.getId());
	}
	
	/**
	 * Contains topTube.
	 *
	 * @param youtopTubeId the youtopTube id
	 * @return true, if successful
	 */
	private boolean containsTopTube(String youtopTubeId) {
		boolean contains = true;
		if (youtopTubeId != null) {
			TopTube item = topTubeDao.find(youtopTubeId);
			if (item == null) {
				contains = false;
			}
		} else {
			return contains = false;
		}
		
		return contains;
	}
	
	
	/**
	 * Gets the detail topTube.
	 *
	 * @param id the id
	 * @return the detail topTube
	 */
	public TopTube getDetailTopTube(String id) {
		
		Tube youtopTube = YoutubeUtils.getTube(id);
		TopTube topTube = topTubeDao.find(id);
		//Update topTube info from youtopTube
		if(topTube != null) {
			try {
				topTube.setViewCount(youtopTube.getViewCount());
				topTube.setLikeCount(youtopTube.getLikeCount());
				topTube.setDislikeCount(youtopTube.getDislikeCount());
				topTube.setFavoriteCount(youtopTube.getFavoriteCount());
				topTube.setCommentCount(youtopTube.getCommentCount());
				topTube.setEmbedHtml(youtopTube.getEmbedHtml());
				update(topTube);
			} catch (CommonException e) {
				log.error(e.getMessage(), e.getCause());
			}
			return topTube; 
		}
		return topTube;
	}
	
	/**
	 * Find record.
	 *
	 * @param id the id
	 * @return the topTube
	 */
	public TopTube findRecord(String id) {
		return topTubeDao.find(id);
	}
	
	/**
	 * Search topTube.
	 *
	 * @param querySearch the query search
	 * @param cursorString the cursor string
	 * @param count the count
	 * @return the collection response
	 * @throws CommonException the common exception
	 */
	public CollectionResponse<TopTube> searchTopTube(String querySearch, String cursorString, Integer count) throws CommonException {
		return topTubeDao.searchTopTube(querySearch, cursorString, count);
	}
	
	/**
	 * Gets the top plays.
	 *
	 * @param cursorString the cursor string
	 * @param count the count
	 * @return the top plays
	 * @throws CommonException the common exception
	 */
	public CollectionResponse<TopTube> getTopPlays(String cursorString, Integer count) throws CommonException {
		return topTubeDao.getTopPlays(cursorString, count);
	}
	
	/**
	 * Gets the top musics.
	 *
	 * @param cursorString the cursor string
	 * @param count the count
	 * @return the top musics
	 * @throws CommonException the common exception
	 */
	public CollectionResponse<TopTube> getTopMusics(String cursorString, Integer count) throws CommonException {
		return topTubeDao.getTopMusics(cursorString, count);
	}
	
	/**
	 * Gets the top movies.
	 *
	 * @param cursorString the cursor string
	 * @param count the count
	 * @return the top movies
	 * @throws CommonException the common exception
	 */
	public CollectionResponse<TopTube> getTopMovies(String cursorString, Integer count) throws CommonException {
		return topTubeDao.getTopMovies(cursorString, count);
	}
	
	/**
	 * Find one by name.
	 *
	 * @param name the name
	 * @param cursorString the cursor string
	 * @param count the count
	 * @return the collection response
	 * @throws CommonException the common exception
	 */
	public CollectionResponse<TopTube> findOneByTitle(String title, String cursorString, Integer count) throws CommonException {
		return topTubeDao.getTopTubeByTitle(title, cursorString, count);
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
		return topTubeDao.getDeletedTubes(cursorString, count);
	}

	/**
	 * Inits the data.
	 */
	public void initData() {
		topTubeDao.initData();
	}
	
	/**
	 * Clean data.
	 */
	public void cleanData() {
		topTubeDao.cleanData();
	}
	
	/**
	 * Clean data.
	 *
	 * @param status the status
	 * @throws CommonException the common exception
	 */
	public void cleanData(Integer status) throws CommonException{
		topTubeDao.cleanData(status);
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
		return topTubeDao.searchTubes(field, value, cursorString, count);
	}

}
