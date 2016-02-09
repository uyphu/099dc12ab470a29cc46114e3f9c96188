package com.ltu.yealtube.endpoint;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.ltu.yealtube.dao.UserGroupDao;
import com.ltu.yealtube.domain.UserGroup;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.exception.ErrorCode;
import com.ltu.yealtube.exception.ErrorCodeDetail;

/**
 * The Class UserGroupEndpoint.
 * @author uyphu
 */
@Api(name = "usergroupendpoint", namespace = @ApiNamespace(ownerDomain = "ltu.com", ownerName = "ltu.com", packagePath = "yealtube.domain"))
public class UserGroupEndpoint {

	/**
	 * Return a collection of userGroups.
	 *
	 * @param cursorString the cursor string
	 * @param count The number of userGroups
	 * @return a list of UserGroups
	 */
	@ApiMethod(name = "listUserGroup")
	public CollectionResponse<UserGroup> listUserGroup(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("count") Integer count) {
		UserGroupDao dao = new UserGroupDao();
		return dao.list(cursorString, count);
	}
	
	/**
	 * This inserts a new <code>UserGroup</code> object.
	 *
	 * @param userGroup The object to be added.
	 * @return The object to be added.
	 * @throws CommonException the common exception
	 */
	@ApiMethod(name = "insertUserGroup")
	public UserGroup insertUserGroup(UserGroup userGroup) throws CommonException {
		// If if is not null, then check if it exists. If yes, throw an
		// Exception
		// that it is already present
		if (userGroup.getId() != null) {
			if (userGroup.getId() == 0) {
				userGroup.setId(null);
			} else {
				if (findRecord(userGroup.getId()) != null) {
					throw new CommonException(ErrorCode.CONFLICT_EXCEPTION,
							ErrorCodeDetail.ERROR_EXIST_OBJECT);
				}
			}
		}
		// Since our @Id field is a Long, Objectify will generate a unique value
		// for us
		// when we use put
		UserGroupDao dao = new UserGroupDao();
		UserGroup pos = dao.getUserGroupByName(userGroup.getName());
		//FIXME Check the code below
		if (pos == null) {
			dao.persist(userGroup);
		} else {
			throw new CommonException(ErrorCode.CONFLICT_EXCEPTION,
					ErrorCodeDetail.ERROR_EXIST_OBJECT);
		}
		return userGroup;
	}

	/**
	 * This updates an existing <code>UserGroup</code> object.
	 *
	 * @param userGroup            The object to be added.
	 * @return The object to be updated.
	 * @throws CommonException the common exception
	 */
	@ApiMethod(name = "updateUserGroup")
	public UserGroup updateUserGroup(UserGroup userGroup) throws CommonException {
		UserGroup oldUserGroup = findRecord(userGroup.getId());
		if (oldUserGroup == null) {
			throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION,
					ErrorCodeDetail.ERROR_RECORD_NOT_FOUND);
		}
		UserGroupDao dao = new UserGroupDao();
		UserGroup pos = dao.getUserGroupByName(userGroup.getName());
		//FIXME Check this logic
		if (pos == null || pos.getId().equals(userGroup.getId())) {
			oldUserGroup.setName(userGroup.getName());
//			if (userGroup.getManager() != null) {
//				UserDao userDao = new UserDao();
//				User manager = userDao.getUserByLogin(userGroup.getManager());
//				if (manager != null) {
//					userGroup.setManager(manager.getLogin());
//				} else {
//					throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION,
//							ErrorCodeDetail.ERROR_USER_NOT_FOUND);
//				}
//			}
			dao.update(userGroup);
		} else {
			throw new CommonException(ErrorCode.CONFLICT_EXCEPTION,
					ErrorCodeDetail.ERROR_EXIST_OBJECT);
		}
		return userGroup;
	}

	/**
	 * This deletes an existing <code>UserGroup</code> object.
	 *
	 * @param id            The id of the object to be deleted.
	 * @throws CommonException the proconco exception
	 */
	@ApiMethod(name = "removeUserGroup")
	public void removeUserGroup(@Named("id") Long id) throws CommonException {
		UserGroup record = findRecord(id);
		if (record == null) {
			throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION,
					ErrorCodeDetail.ERROR_RECORD_NOT_FOUND);
		}
		UserGroupDao dao = new UserGroupDao();
		dao.delete(record);
	}
	
	/**
	 * Gets the userGroup.
	 *
	 * @param id the id
	 * @return the userGroup
	 */
	@ApiMethod(name = "getUserGroup")
	public UserGroup getUserGroup(@Named("id") Long id) {
		return findRecord(id);
	}
	
	/**
	 * Find record.
	 *
	 * @param id the id
	 * @return the user main
	 */
	private UserGroup findRecord(Long id) {
		UserGroupDao dao = new UserGroupDao();
		return dao.find(id);
	}
	
	/**
	 * Inits the data.
	 */
	@ApiMethod(name = "initData", httpMethod = HttpMethod.POST, path = "initData")
	public void initData() {
		UserGroupDao dao = new UserGroupDao();
		dao.initData();
	}
	
	/**
	 * Clean data.
	 */
	@ApiMethod(name = "cleanData", httpMethod = HttpMethod.POST, path = "cleanData")
	public void cleanData() {
		UserGroupDao dao = new UserGroupDao();
		dao.cleanData();
	}
	
	/**
	 * Search user group.
	 *
	 * @param querySearch the query search
	 * @param cursorString the cursor string
	 * @param count the count
	 * @return the collection response
	 * @throws CommonException the common exception
	 */
	@ApiMethod(name = "searchUserGroup", httpMethod=HttpMethod.GET, path="search_userGroup")
	public CollectionResponse<UserGroup> searchUserGroup(
			@Nullable @Named("querySearch") String querySearch,
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("count") Integer count) throws CommonException {
		UserGroupDao dao = new UserGroupDao();
		return dao.searchUserGroup(querySearch, cursorString, count);
	}


}
