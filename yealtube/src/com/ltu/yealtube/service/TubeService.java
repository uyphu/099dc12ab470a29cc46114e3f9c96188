package com.ltu.yealtube.service;

import java.util.Calendar;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.server.spi.response.CollectionResponse;
import com.ltu.yealtube.dao.TubeDao;
import com.ltu.yealtube.domain.Tube;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.exception.ErrorCode;
import com.ltu.yealtube.exception.ErrorCodeDetail;

public class TubeService {
	
	/** The log. */
	//private final Logger log = Logger.getLogger(TubeService.class);
	
	/** The tube dao. */
	private TubeDao tubeDao = TubeDao.getInstance();
	
	/** The instance. */
	private static TubeService instance = null;
	
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
	 * Insert tube.
	 *
	 * @param tube the tube
	 * @return the tube
	 * @throws CommonException the common exception
	 */
	public Tube insertTube(Tube tube) throws CommonException {
		
		tube.setDateAdded(Calendar.getInstance().getTime());
		tube.setDateModified(Calendar.getInstance().getTime());
		return tubeDao.persist(tube);
	}
	
	/**
	 * Update tube.
	 *
	 * @param tube the tube
	 * @return the tube
	 * @throws CommonException the common exception
	 */
	public Tube updateTube(Tube tube) throws CommonException {
		
		tube.setDateModified(Calendar.getInstance().getTime());
		return tubeDao.update(tube); 
	}
	
	/**
	 * Removes the tube.
	 *
	 * @param id the id
	 * @throws CommonException the common exception
	 */
	public void removeTube(String id) throws CommonException {
		Tube record = findRecord(id);
		if (record == null) {
			throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION,
					ErrorCodeDetail.ERROR_RECORD_NOT_FOUND);
		}
		tubeDao.delete(record);
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
	 * Search tube.
	 *
	 * @param querySearch the query search
	 * @param cursorString the cursor string
	 * @param count the count
	 * @return the collection response
	 * @throws CommonException the common exception
	 */
	public CollectionResponse<Tube> searchTube(
			@Nullable @Named("querySearch") String querySearch,
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("count") Integer count) throws CommonException {
		return tubeDao.searchTube(querySearch, cursorString, count);
	}
	
	public CollectionResponse<Tube> findOneByName(@Nullable @Named("name") String name,
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("count") Integer count) throws CommonException {
		return tubeDao.getTubeByName(name, cursorString, count);
	}

}
