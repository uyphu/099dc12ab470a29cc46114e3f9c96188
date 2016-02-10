package com.ltu.yealtube.service;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.client.http.HttpStatusCodes;
import com.google.api.server.spi.response.CollectionResponse;
import com.ltu.yealtube.dao.UserGroupDao;
import com.ltu.yealtube.domain.UserGroup;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.exception.ErrorCode;
import com.ltu.yealtube.exception.ErrorCodeDetail;

/**
 * The Class UserGroupService.
 * 
 * @author uyphu
 */
public class UserGroupService {

	/** The log. */
	// private final Logger log = Logger.getLogger(UserGroupService.class);

	/** The userGroup dao. */
	private UserGroupDao userGroupDao = UserGroupDao.getInstance();

	/** The instance. */
	private static UserGroupService instance = null;

	/**
	 * Instantiates a new userGroup dao.
	 */
	public UserGroupService() {

	}

	/**
	 * Gets the single instance of UserGroupService.
	 * 
	 * @return single instance of UserGroupService
	 */
	public static UserGroupService getInstance() {
		if (instance == null) {
			instance = new UserGroupService();
		}
		return instance;
	}

	/**
	 * List userGroup.
	 * 
	 * @param cursorString
	 *            the cursor string
	 * @param count
	 *            the count
	 * @return the collection response
	 */
	public CollectionResponse<UserGroup> listUserGroup(String cursorString, Integer count) {
		return userGroupDao.list(cursorString, count);
	}

	/**
	 * Insert userGroup.
	 * 
	 * @param userGroup
	 *            the userGroup
	 * @return the userGroup
	 * @throws CommonException
	 *             the common exception
	 */
	public UserGroup insertUserGroup(UserGroup userGroup) throws CommonException {
		if (userGroup != null && userGroup.getId() != null) {
			if (containsUserGroup(userGroup)) {
				throw new CommonException(HttpStatusCodes.STATUS_CODE_FOUND, ErrorCodeDetail.ERROR_EXIST_OBJECT.getMsg());
			}
			return userGroupDao.persist(userGroup);
		} else {
			throw new CommonException(HttpStatusCodes.STATUS_CODE_BAD_GATEWAY, ErrorCodeDetail.ERROR_INPUT_NOT_VALID.getMsg());
		}
	}

	/**
	 * Contains userGroup.
	 * 
	 * @param userGroup
	 *            the userGroup
	 * @return true, if successful
	 */
	private boolean containsUserGroup(UserGroup userGroup) {
		return containsUserGroup(userGroup.getId());
	}

	/**
	 * Contains userGroup.
	 * 
	 * @param id
	 *            the id
	 * @return true, if successful
	 */
	private boolean containsUserGroup(Long id) {
		boolean contains = true;
		if (id != null) {
			UserGroup item = userGroupDao.find(id);
			if (item == null) {
				contains = false;
			}
		} else {
			return contains = false;
		}

		return contains;
	}

	/**
	 * Update userGroup.
	 * 
	 * @param userGroup
	 *            the userGroup
	 * @return the userGroup
	 * @throws CommonException
	 *             the common exception
	 */
	public UserGroup updateUserGroup(UserGroup userGroup) throws CommonException {

		if (userGroup != null && userGroup.getId() != null) {
			if (!containsUserGroup(userGroup)) {
				throw new CommonException(HttpStatusCodes.STATUS_CODE_NOT_FOUND,
						ErrorCodeDetail.ERROR_RECORD_NOT_FOUND.getMsg());
			}
			return userGroupDao.update(userGroup);
		} else {
			throw new CommonException(HttpStatusCodes.STATUS_CODE_BAD_GATEWAY, ErrorCodeDetail.ERROR_INPUT_NOT_VALID.getMsg());
		}
	}

	/**
	 * Removes the userGroup.
	 * 
	 * @param id
	 *            the id
	 * @throws CommonException
	 *             the common exception
	 */
	public void removeUserGroup(Long id) throws CommonException {
		UserGroup record = findRecord(id);
		if (record == null) {
			throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION, ErrorCodeDetail.ERROR_RECORD_NOT_FOUND);
		}
		userGroupDao.delete(record);
	}

	/**
	 * Find record.
	 * 
	 * @param id
	 *            the id
	 * @return the userGroup
	 */
	public UserGroup findRecord(Long id) {
		UserGroupDao dao = new UserGroupDao();
		return dao.find(id);
	}

	/**
	 * Inits the data.
	 */
	public void initData() {
		userGroupDao.initData();
	}

	/**
	 * Clean data.
	 */
	public void cleanData() {
		userGroupDao.cleanData();
	}

	/**
	 * Search userGroup.
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
	public CollectionResponse<UserGroup> searchUserGroup(@Nullable @Named("querySearch") String querySearch,
			@Nullable @Named("cursor") String cursorString, @Nullable @Named("count") Integer count) throws CommonException {
		return userGroupDao.searchUserGroup(querySearch, cursorString, count);
	}

}
