package com.ltu.yealtube.service;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.client.http.HttpStatusCodes;
import com.google.api.server.spi.response.CollectionResponse;
import com.ltu.yealtube.constants.Constant;
import com.ltu.yealtube.dao.ParamValueDao;
import com.ltu.yealtube.domain.ParamValue;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.exception.ErrorCode;
import com.ltu.yealtube.exception.ErrorCodeDetail;

/**
 * The Class ParamValueService.
 * 
 * @author uyphu
 */
public class ParamValueService {

	/** The log. */
	//private final Logger log = Logger.getLogger(ParamValueService.class);

	/** The paramValue dao. */
	private ParamValueDao paramValueDao = ParamValueDao.getInstance();

	/** The instance. */
	private static ParamValueService instance = null;

	/**
	 * Instantiates a new paramValue dao.
	 */
	public ParamValueService() {

	}

	/**
	 * Gets the single instance of ParamValueService.
	 * 
	 * @return single instance of ParamValueService
	 */
	public static ParamValueService getInstance() {
		if (instance == null) {
			instance = new ParamValueService();
		}
		return instance;
	}

	/**
	 * List paramValue.
	 * 
	 * @param cursorString
	 *            the cursor string
	 * @param count
	 *            the count
	 * @return the collection response
	 */
	public CollectionResponse<ParamValue> listParamValue(String cursorString, Integer count) {
		return paramValueDao.list(cursorString, count);
	}

	/**
	 * Insert paramValue.
	 * 
	 * @param paramValue
	 *            the paramValue
	 * @return the paramValue
	 * @throws CommonException
	 *             the common exception
	 */
	public ParamValue insertParamValue(ParamValue paramValue) throws CommonException {
		if (paramValue != null && paramValue.getId() != null) {
			if (containsParamValue(paramValue)) {
				throw new CommonException(HttpStatusCodes.STATUS_CODE_FOUND, ErrorCodeDetail.ERROR_EXIST_OBJECT.getMsg());
			}
			return paramValueDao.persist(paramValue);
		} else {
			throw new CommonException(HttpStatusCodes.STATUS_CODE_BAD_GATEWAY, ErrorCodeDetail.ERROR_INPUT_NOT_VALID.getMsg());
		}
	}

	/**
	 * Contains paramValue.
	 * 
	 * @param paramValue
	 *            the paramValue
	 * @return true, if successful
	 */
	private boolean containsParamValue(ParamValue paramValue) {
		return containsParamValue(paramValue.getId());
	}

	/**
	 * Contains paramValue.
	 * 
	 * @param id
	 *            the id
	 * @return true, if successful
	 */
	private boolean containsParamValue(String id) {
		boolean contains = true;
		if (id != null) {
			ParamValue item = paramValueDao.find(id);
			if (item == null) {
				contains = false;
			}
		} else {
			return contains = false;
		}

		return contains;
	}

	/**
	 * Update paramValue.
	 * 
	 * @param paramValue
	 *            the paramValue
	 * @return the paramValue
	 * @throws CommonException
	 *             the common exception
	 */
	public ParamValue updateParamValue(ParamValue paramValue) throws CommonException {

		if (paramValue != null && paramValue.getId() != null) {
			if (!containsParamValue(paramValue)) {
				throw new CommonException(HttpStatusCodes.STATUS_CODE_NOT_FOUND,
						ErrorCodeDetail.ERROR_RECORD_NOT_FOUND.getMsg());
			}
			return paramValueDao.update(paramValue);
		} else {
			throw new CommonException(HttpStatusCodes.STATUS_CODE_BAD_GATEWAY, ErrorCodeDetail.ERROR_INPUT_NOT_VALID.getMsg());
		}
	}

	/**
	 * Removes the paramValue.
	 * 
	 * @param id
	 *            the id
	 * @throws CommonException
	 *             the common exception
	 */
	public void removeParamValue(String id) throws CommonException {
		ParamValue record = findRecord(id);
		if (record == null) {
			throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION, ErrorCodeDetail.ERROR_RECORD_NOT_FOUND);
		}
		paramValueDao.delete(record);
	}

	/**
	 * Find record.
	 * 
	 * @param id
	 *            the id
	 * @return the paramValue
	 */
	public ParamValue findRecord(String id) {
		return paramValueDao.find(id);
	}

	/**
	 * Inits the data.
	 */
	public void initData() throws CommonException{
		paramValueDao.initData();
	}

	/**
	 * Clean data.
	 */
	public void cleanData() throws CommonException {
		paramValueDao.cleanData();
	}

	/**
	 * Search paramValue.
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
	public CollectionResponse<ParamValue> searchParamValue(@Nullable @Named("querySearch") String querySearch,
			@Nullable @Named("cursor") String cursorString, @Nullable @Named("count") Integer count) throws CommonException {
		return paramValueDao.searchParamValue(querySearch, cursorString, count);
	}
	
	/**
	 * Retrieve value or default.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String retrieveValueOrDefault(String id) {
		ParamValue paramValue = findRecord(id);
		String value = null;
		if (paramValue != null) {
			value = paramValue.getValue();
		} 
		if (value == null || value.indexOf(Constant.COMMA_STRING) == -1) {
			value = Constant.KEYWORD_18PLUS_VALUE;
		}
		return value;
	}

}
