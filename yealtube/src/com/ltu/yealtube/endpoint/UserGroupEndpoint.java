package com.ltu.yealtube.endpoint;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.ltu.yealtube.domain.UserGroup;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.service.UserGroupService;

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
		UserGroupService service = UserGroupService.getInstance();
		return service.listUserGroup(cursorString, count);
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
		UserGroupService service = UserGroupService.getInstance();
		return service.insertUserGroup(userGroup);
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
		UserGroupService service = UserGroupService.getInstance();
		return service.updateUserGroup(userGroup);
	}

	/**
	 * This deletes an existing <code>UserGroup</code> object.
	 *
	 * @param id            The id of the object to be deleted.
	 * @throws CommonException the proconco exception
	 */
	@ApiMethod(name = "removeUserGroup")
	public void removeUserGroup(@Named("id") Long id) throws CommonException {
		UserGroupService service = UserGroupService.getInstance();
		service.removeUserGroup(id);
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
		UserGroupService service = UserGroupService.getInstance();
		return service.findRecord(id);
	}
	
	/**
	 * Inits the data.
	 */
	@ApiMethod(name = "initData", httpMethod = HttpMethod.POST, path = "initData")
	public void initData() {
		UserGroupService service = UserGroupService.getInstance();
		service.initData();
	}
	
	/**
	 * Clean data.
	 */
	@ApiMethod(name = "cleanData", httpMethod = HttpMethod.POST, path = "cleanData")
	public void cleanData() {
		UserGroupService service = UserGroupService.getInstance();
		service.cleanData();
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
		UserGroupService service = UserGroupService.getInstance();
		return service.searchUserGroup(querySearch, cursorString, count);
	}


}
