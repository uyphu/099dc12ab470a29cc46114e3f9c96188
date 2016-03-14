package com.ltu.yealtube.service;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.client.http.HttpStatusCodes;
import com.google.api.server.spi.response.CollectionResponse;
import com.ltu.yealtube.dao.ThingDao;
import com.ltu.yealtube.domain.Thing;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.exception.ErrorCode;
import com.ltu.yealtube.exception.ErrorCodeDetail;

/**
 * The Class ThingService.
 * 
 * @author uyphu
 */
public class ThingService {

	/** The log. */
	// private final Logger log = Logger.getLogger(ThingService.class);

	/** The thing dao. */
	private ThingDao thingDao = ThingDao.getInstance();

	/** The instance. */
	private static ThingService instance = null;

	/**
	 * Instantiates a new thing dao.
	 */
	public ThingService() {

	}

	/**
	 * Gets the single instance of ThingService.
	 * 
	 * @return single instance of ThingService
	 */
	public static ThingService getInstance() {
		if (instance == null) {
			instance = new ThingService();
		}
		return instance;
	}

	/**
	 * List thing.
	 * 
	 * @param cursorString
	 *            the cursor string
	 * @param count
	 *            the count
	 * @return the collection response
	 */
	public CollectionResponse<Thing> listThing(String cursorString, Integer count) {
		return thingDao.list(cursorString, count);
	}
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Thing> findAll() {
		return thingDao.findAll();
	}

	/**
	 * Insert thing.
	 * 
	 * @param thing
	 *            the thing
	 * @return the thing
	 * @throws CommonException
	 *             the common exception
	 */
	public Thing insertThing(Thing thing) throws CommonException {
		//if (thing != null && thing.getId() != null) {
//			if (containsThing(thing)) {
//				throw new CommonException(HttpStatusCodes.STATUS_CODE_FOUND, ErrorCodeDetail.ERROR_EXIST_OBJECT.getMsg());
//			}
			return thingDao.persist(thing);
//		} else {
//			throw new CommonException(HttpStatusCodes.STATUS_CODE_BAD_GATEWAY, ErrorCodeDetail.ERROR_INPUT_NOT_VALID.getMsg());
//		}
	}

	/**
	 * Contains thing.
	 * 
	 * @param thing
	 *            the thing
	 * @return true, if successful
	 */
	private boolean containsThing(Thing thing) {
		return containsThing(thing.getId());
	}

	/**
	 * Contains thing.
	 * 
	 * @param id
	 *            the id
	 * @return true, if successful
	 */
	private boolean containsThing(Long id) {
		boolean contains = true;
		if (id != null) {
			Thing item = thingDao.find(id);
			if (item == null) {
				contains = false;
			}
		} else {
			return contains = false;
		}

		return contains;
	}

	/**
	 * Update thing.
	 * 
	 * @param thing
	 *            the thing
	 * @return the thing
	 * @throws CommonException
	 *             the common exception
	 */
	public Thing updateThing(Thing thing) throws CommonException {

		if (thing != null && thing.getId() != null) {
			if (!containsThing(thing)) {
				throw new CommonException(HttpStatusCodes.STATUS_CODE_NOT_FOUND,
						ErrorCodeDetail.ERROR_RECORD_NOT_FOUND.getMsg());
			}
			return thingDao.update(thing);
		} else {
			throw new CommonException(HttpStatusCodes.STATUS_CODE_BAD_GATEWAY, ErrorCodeDetail.ERROR_INPUT_NOT_VALID.getMsg());
		}
	}

	/**
	 * Removes the thing.
	 * 
	 * @param id
	 *            the id
	 * @throws CommonException
	 *             the common exception
	 */
	public void removeThing(Long id) throws CommonException {
		Thing record = findRecord(id);
		if (record == null) {
			throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION, ErrorCodeDetail.ERROR_RECORD_NOT_FOUND);
		}
		thingDao.delete(record);
	}

	/**
	 * Find record.
	 * 
	 * @param id
	 *            the id
	 * @return the thing
	 */
	public Thing findRecord(Long id) {
		ThingDao dao = new ThingDao();
		return dao.find(id);
	}

	/**
	 * Inits the data.
	 */
	public void initData() {
		thingDao.initData();
	}

	/**
	 * Clean data.
	 */
	public void cleanData() {
		thingDao.cleanData();
	}

	/**
	 * Search thing.
	 * 
	 * @param querySearch
	 *            the query search
	 * @param cursorString
	 *            the cursor string
	 * @param count
	 *            the count
	 * @return the collection response
	 * @throws CommonException
	 *             the common exception
	 */
	public CollectionResponse<Thing> searchThing(@Nullable @Named("querySearch") String querySearch,
			@Nullable @Named("cursor") String cursorString, @Nullable @Named("count") Integer count) throws CommonException {
		return thingDao.searchThing(querySearch, cursorString, count);
	}

}
