package com.ltu.yealtube.service;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.client.http.HttpStatusCodes;
import com.google.api.server.spi.response.CollectionResponse;
import com.ltu.yealtube.dao.AuthorityDao;
import com.ltu.yealtube.domain.Authority;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.exception.ErrorCode;
import com.ltu.yealtube.exception.ErrorCodeDetail;

/**
 * The Class AuthorityService.
 * 
 * @author uyphu
 */
public class AuthorityService {

	/** The log. */
	// private final Logger log = Logger.getLogger(AuthorityService.class);

	/** The authority dao. */
	private AuthorityDao authorityDao = AuthorityDao.getInstance();

	/** The instance. */
	private static AuthorityService instance = null;

	/**
	 * Instantiates a new authority dao.
	 */
	public AuthorityService() {

	}

	/**
	 * Gets the single instance of AuthorityService.
	 * 
	 * @return single instance of AuthorityService
	 */
	public static AuthorityService getInstance() {
		if (instance == null) {
			instance = new AuthorityService();
		}
		return instance;
	}

	/**
	 * List authority.
	 * 
	 * @param cursorString
	 *            the cursor string
	 * @param count
	 *            the count
	 * @return the collection response
	 */
	public CollectionResponse<Authority> listAuthority(String cursorString, Integer count) {
		return authorityDao.list(cursorString, count);
	}

	/**
	 * Insert authority.
	 * 
	 * @param authority
	 *            the authority
	 * @return the authority
	 * @throws CommonException
	 *             the common exception
	 */
	public Authority insertAuthority(Authority authority) throws CommonException {
		if (authority != null && authority.getId() != null) {
			if (containsAuthority(authority)) {
				throw new CommonException(HttpStatusCodes.STATUS_CODE_FOUND, ErrorCodeDetail.ERROR_EXIST_OBJECT.getMsg());
			}
			return authorityDao.persist(authority);
		} else {
			throw new CommonException(HttpStatusCodes.STATUS_CODE_BAD_GATEWAY, ErrorCodeDetail.ERROR_INPUT_NOT_VALID.getMsg());
		}
	}

	/**
	 * Contains authority.
	 * 
	 * @param authority
	 *            the authority
	 * @return true, if successful
	 */
	private boolean containsAuthority(Authority authority) {
		return containsAuthority(authority.getId());
	}

	/**
	 * Contains authority.
	 * 
	 * @param id
	 *            the id
	 * @return true, if successful
	 */
	private boolean containsAuthority(Long id) {
		boolean contains = true;
		if (id != null) {
			Authority item = authorityDao.find(id);
			if (item == null) {
				contains = false;
			}
		} else {
			return contains = false;
		}

		return contains;
	}

	/**
	 * Update authority.
	 * 
	 * @param authority
	 *            the authority
	 * @return the authority
	 * @throws CommonException
	 *             the common exception
	 */
	public Authority updateAuthority(Authority authority) throws CommonException {

		if (authority != null && authority.getId() != null) {
			if (!containsAuthority(authority)) {
				throw new CommonException(HttpStatusCodes.STATUS_CODE_NOT_FOUND,
						ErrorCodeDetail.ERROR_RECORD_NOT_FOUND.getMsg());
			}
			return authorityDao.update(authority);
		} else {
			throw new CommonException(HttpStatusCodes.STATUS_CODE_BAD_GATEWAY, ErrorCodeDetail.ERROR_INPUT_NOT_VALID.getMsg());
		}
	}

	/**
	 * Removes the authority.
	 * 
	 * @param id
	 *            the id
	 * @throws CommonException
	 *             the common exception
	 */
	public void removeAuthority(Long id) throws CommonException {
		Authority record = findRecord(id);
		if (record == null) {
			throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION, ErrorCodeDetail.ERROR_RECORD_NOT_FOUND);
		}
		authorityDao.delete(record);
	}

	/**
	 * Find record.
	 * 
	 * @param id
	 *            the id
	 * @return the authority
	 */
	public Authority findRecord(Long id) {
		AuthorityDao dao = new AuthorityDao();
		return dao.find(id);
	}

	/**
	 * Inits the data.
	 */
	public void initData() {
		authorityDao.initData();
	}

	/**
	 * Clean data.
	 */
	public void cleanData() {
		authorityDao.cleanData();
	}

	/**
	 * Search authority.
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
	public CollectionResponse<Authority> searchAuthority(@Nullable @Named("querySearch") String querySearch,
			@Nullable @Named("cursor") String cursorString, @Nullable @Named("count") Integer count) throws CommonException {
		return authorityDao.searchAuthority(querySearch, cursorString, count);
	}

}
