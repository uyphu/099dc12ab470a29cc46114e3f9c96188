package com.ltu.yealtube.service;

import java.util.Calendar;

import org.apache.log4j.Logger;

import com.google.api.client.http.HttpStatusCodes;
import com.google.api.server.spi.response.CollectionResponse;
import com.ltu.yealtube.constants.Constant;
import com.ltu.yealtube.dao.CategoryDao;
import com.ltu.yealtube.dao.TubeDao;
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
public class TubeService {
	
	/** The log. */
	private final Logger log = Logger.getLogger(TubeService.class);
	
	/** The tube dao. */
	private TubeDao tubeDao = TubeDao.getInstance();
	
	/** The instance. */
	private static TubeService instance = null;
	
	/**
	 * Gets the single instance of TubeService.
	 *
	 * @return single instance of TubeService
	 */
	public static TubeService getInstance() {
		if (instance == null) {
			instance = new TubeService();
		}
		return instance;
	}
	
	/**
	 * List tube.
	 *
	 * @param cursorString the cursor string
	 * @param count the count
	 * @return the collection response
	 */
	public CollectionResponse<Tube> listTube(String cursorString, Integer count) {
		return tubeDao.list(cursorString, count);
	}
	
	/**
	 * Insert.
	 *
	 * @param tube the tube
	 * @return the tube
	 * @throws CommonException the common exception
	 */
	public Tube insert(Tube tube) throws CommonException {
		if (tube != null && tube.getId() != null) {
			if (containsTube(tube)) {
				throw new CommonException(HttpStatusCodes.STATUS_CODE_FOUND, ErrorCodeDetail.ERROR_EXIST_OBJECT.getMsg());
			}
			tube.setCreatedAt(Calendar.getInstance().getTime());
			tube.setModifiedAt(Calendar.getInstance().getTime());
			tube.setStatus(Constant.PENDING_STATUS);
			TopTubeService service = TopTubeService.getInstance();
			//Insert top tube
			service.insert(new TopTube(tube));
			return tubeDao.persist(tube);
		} else {
			throw new CommonException(HttpStatusCodes.STATUS_CODE_BAD_GATEWAY, ErrorCodeDetail.ERROR_INPUT_NOT_VALID.getMsg());
		}
	}
	
	/**
	 * Insert.
	 *
	 * @param youtubeId the youtube id
	 * @return the tube
	 * @throws CommonException the common exception
	 */
	public Tube insert(String youtubeId) throws CommonException {
		if (youtubeId != null) {
			if (containsTube(youtubeId)) {
				throw new CommonException(HttpStatusCodes.STATUS_CODE_FOUND, ErrorCodeDetail.ERROR_EXIST_OBJECT.getMsg());
			}
			Tube tube = YoutubeUtils.getTube(youtubeId);
			tube.setCreatedAt(Calendar.getInstance().getTime());
			tube.setModifiedAt(Calendar.getInstance().getTime());
			tube.setStatus(Constant.APPROVED_STATUS);
			tube.setUserId(Constant.ADMIN_ID);
			Category category = YoutubeUtils.getCategory(youtubeId);
			if (category != null) {
				tube.setCategory(category);
				TopTubeService service = TopTubeService.getInstance();
				//Insert top tube
				service.insert(new TopTube(tube));
				return tubeDao.persist(tube);
			} else {
				throw new CommonException(HttpStatusCodes.STATUS_CODE_FORBIDDEN, ErrorCodeDetail.ERROR_INPUT_NOT_VALID.getMsg());
			}
			
		} else {
			throw new CommonException(HttpStatusCodes.STATUS_CODE_BAD_GATEWAY, ErrorCodeDetail.ERROR_INPUT_NOT_VALID.getMsg());
		}
	}
	
	public Tube insert(String youtubeId, Float rating) throws CommonException {
		if (youtubeId != null) {
			if (containsTube(youtubeId)) {
				throw new CommonException(HttpStatusCodes.STATUS_CODE_FOUND, ErrorCodeDetail.ERROR_EXIST_OBJECT.getMsg());
			}
			Tube tube = YoutubeUtils.getTube(youtubeId);
			tube.setCreatedAt(Calendar.getInstance().getTime());
			tube.setModifiedAt(Calendar.getInstance().getTime());
			tube.setStatus(Constant.APPROVED_STATUS);
			tube.setUserId(Constant.ADMIN_ID);
			if (rating != null) {
				tube.setRating(rating);
			}
			Category category = YoutubeUtils.getCategory(youtubeId);
			if (category != null) {
				tube.setCategory(category);
				TopTubeService service = TopTubeService.getInstance();
				//Insert top tube
				service.insert(new TopTube(tube));
				return tubeDao.persist(tube);
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
	 * @param tube the tube
	 * @return the tube
	 * @throws CommonException the common exception
	 */
	public Tube update(Tube tube) throws CommonException {
		if (tube != null && tube.getId() != null) {
			if (!containsTube(tube)) {
				throw new CommonException(HttpStatusCodes.STATUS_CODE_NOT_FOUND,
						ErrorCodeDetail.ERROR_RECORD_NOT_FOUND.getMsg());
			}
			tube.setModifiedAt(Calendar.getInstance().getTime());
			if (tube.getCategory() != null) {
				tube.setCategory(CategoryDao.getInstance().find(tube.getCategory().getId()));
			}
			TopTubeService service = TopTubeService.getInstance();
			//Update top tube
			service.update(new TopTube(tube));
			return tubeDao.update(tube);
		} else {
			throw new CommonException(HttpStatusCodes.STATUS_CODE_BAD_GATEWAY, ErrorCodeDetail.ERROR_INPUT_NOT_VALID.getMsg());
		}
	}
	
	/**
	 * Delete.
	 *
	 * @param tube the tube
	 * @throws CommonException the common exception
	 */
	public void delete(Tube tube) throws CommonException {
		if (tube != null && tube.getId() != null) {
			if (!containsTube(tube)) {
				throw new CommonException(HttpStatusCodes.STATUS_CODE_NOT_FOUND,
						ErrorCodeDetail.ERROR_RECORD_NOT_FOUND.getMsg());
			}
			tube.setModifiedAt(Calendar.getInstance().getTime());
			tubeDao.delete(tube);
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
		Tube tube = findRecord(id);
		delete(tube);
	}
	
	/**
	 * Contains tube.
	 *
	 * @param tube the tube
	 * @return true, if successful
	 */
	private boolean containsTube(Tube tube) {
		return containsTube(tube.getId());
	}
	
	/**
	 * Contains tube.
	 *
	 * @param youtubeId the youtube id
	 * @return true, if successful
	 */
	private boolean containsTube(String youtubeId) {
		boolean contains = true;
		if (youtubeId != null) {
			Tube item = tubeDao.find(youtubeId);
			if (item == null) {
				contains = false;
			}
		} else {
			return contains = false;
		}
		
		return contains;
	}
	
	
	/**
	 * Gets the detail tube.
	 *
	 * @param id the id
	 * @return the detail tube
	 */
	public Tube getDetailTube(String id) {
		
		Tube youtube = YoutubeUtils.getTube(id);
		Tube tube = tubeDao.find(id);
		//Update tube info from youtube
		if(tube != null) {
			try {
				tube.setViewCount(youtube.getViewCount());
				tube.setLikeCount(youtube.getLikeCount());
				tube.setDislikeCount(youtube.getDislikeCount());
				tube.setFavoriteCount(youtube.getFavoriteCount());
				tube.setCommentCount(youtube.getCommentCount());
				tube.setEmbedHtml(youtube.getEmbedHtml());
				update(tube);
			} catch (CommonException e) {
				log.error(e.getMessage(), e.getCause());
			}
			return tube; 
		}
		return youtube;
	}
	
	/**
	 * Find record.
	 *
	 * @param id the id
	 * @return the tube
	 */
	public Tube findRecord(String id) {
		return tubeDao.find(id);
	}
	
	/**
	 * Search tube.
	 *
	 * @param querySearch the query search
	 * @param cursorString the cursor string
	 * @param count the count
	 * @return the collection response
	 * @throws CommonException the common exception
	 */
	public CollectionResponse<Tube> searchTube(String querySearch, String cursorString, Integer count) throws CommonException {
		return tubeDao.searchTube(querySearch, cursorString, count);
	}
	
	/**
	 * Gets the top plays.
	 *
	 * @param cursorString the cursor string
	 * @param count the count
	 * @return the top plays
	 * @throws CommonException the common exception
	 */
	public CollectionResponse<Tube> getTopPlays(String cursorString, Integer count) throws CommonException {
		return tubeDao.getTopPlays(cursorString, count);
	}
	
	/**
	 * Gets the top musics.
	 *
	 * @param cursorString the cursor string
	 * @param count the count
	 * @return the top musics
	 * @throws CommonException the common exception
	 */
	public CollectionResponse<Tube> getTopMusics(String cursorString, Integer count) throws CommonException {
		return tubeDao.getTopMusics(cursorString, count);
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
	public CollectionResponse<Tube> findOneByTitle(String title, String cursorString, Integer count) throws CommonException {
		return tubeDao.getTubeByTitle(title, cursorString, count);
	}

	/**
	 * Inits the data.
	 */
	public void initData() {
		tubeDao.initData();
	}
	
	/**
	 * Clean data.
	 */
	public void cleanData() {
		tubeDao.cleanData();
	}
	
	/**
	 * Clean data.
	 *
	 * @param status the status
	 * @throws CommonException the common exception
	 */
	public void cleanData(Integer status) throws CommonException{
		tubeDao.cleanData(status);
	}

}
