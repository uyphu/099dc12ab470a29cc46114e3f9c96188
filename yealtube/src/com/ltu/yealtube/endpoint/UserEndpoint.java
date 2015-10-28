package com.ltu.yealtube.endpoint;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.ltu.yealtube.dao.UserDao;
import com.ltu.yealtube.domain.User;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.exception.ErrorCode;
import com.ltu.yealtube.exception.ErrorCodeDetail;

@Api(name = "userendpoint", namespace = @ApiNamespace(ownerDomain = "ltu.com", ownerName = "ltu.com", packagePath = "yealtube.domain"))
public class UserEndpoint {

	/**
	* Return a collection of users
	*
	* @param count The number of users
	* @return a list of Users
	*/
	@ApiMethod(name = "listUser")
	public CollectionResponse<User> listUser(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("count") Integer count) {
		UserDao dao = new UserDao();
		return dao.list(cursorString, count);
	}
	
	/**
	* This inserts a new <code>User</code> object.
	* @param user The object to be added.
	* @return The object to be added.
	*/
	@ApiMethod(name = "insertUser")
	public User insertUser(User user) throws CommonException {
		// If if is not null, then check if it exists. If yes, throw an
		// Exception
		// that it is already present
		if (user.getId() != null) {
			if (user.getId() == 0) {
				user.setId(null);
			} else {
				if (findRecord(user.getId()) != null) {
					throw new CommonException(ErrorCode.CONFLICT_EXCEPTION,
							ErrorCodeDetail.ERROR_EXIST_OBJECT);
				}
			}
		}
		// Since our @Id field is a Long, Objectify will generate a unique value
		// for us
		// when we use put
		UserDao dao = new UserDao();
		//User pos = dao.getUserByName(user.get);
		//FIXME Check the code below
		//if (pos == null) {
			dao.persist(user);
//		} else {
//			throw new CommonException(ErrorCode.CONFLICT_EXCEPTION,
//					ErrorCodeDetail.ERROR_EXIST_OBJECT);
//		}
		return user;
	}

	/**
	 * This updates an existing <code>User</code> object.
	 * 
	 * @param user
	 *            The object to be added.
	 * @return The object to be updated.
	 */
	@ApiMethod(name = "updateUser")
	public User updateUser(User user) throws CommonException {
		User oldUser = findRecord(user.getId());
		if (oldUser == null) {
			throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION,
					ErrorCodeDetail.ERROR_RECORD_NOT_FOUND);
		}
		UserDao dao = new UserDao();
		//User pos = null; //dao.getUserByName(user.getName());
		//FIXME Check this logic
		//if (pos == null || pos.getId().equals(user.getId())) {
			//oldUser.setName(user.getName());
//			if (user.getManager() != null) {
//				UserDao userDao = new UserDao();
//				User manager = userDao.getUserByLogin(user.getManager());
//				if (manager != null) {
//					user.setManager(manager.getLogin());
//				} else {
//					throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION,
//							ErrorCodeDetail.ERROR_USER_NOT_FOUND);
//				}
//			}
			dao.update(user);
		//} else {
//			throw new CommonException(ErrorCode.CONFLICT_EXCEPTION,
//					ErrorCodeDetail.ERROR_EXIST_OBJECT);
		//}
		return user;
	}

	/**
	 * This deletes an existing <code>User</code> object.
	 *
	 * @param id            The id of the object to be deleted.
	 * @throws CommonException the proconco exception
	 */
	@ApiMethod(name = "removeUser")
	public void removeUser(@Named("id") Long id) throws CommonException {
		User record = findRecord(id);
		if (record == null) {
			throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION,
					ErrorCodeDetail.ERROR_RECORD_NOT_FOUND);
		}
		UserDao dao = new UserDao();
		dao.delete(record);
	}
	
	/**
	 * Gets the user.
	 *
	 * @param id the id
	 * @return the user
	 */
	@ApiMethod(name = "getUser")
	public User getUser(@Named("id") Long id) {
		return findRecord(id);
	}
	
	/**
	 * Find record.
	 *
	 * @param id the id
	 * @return the user main
	 */
	private User findRecord(Long id) {
		UserDao dao = new UserDao();
		return dao.find(id);
	}
	
	/**
	 * Inits the data.
	 */
	@ApiMethod(name = "initData", httpMethod = HttpMethod.POST, path = "initData")
	public void initData() {
		UserDao dao = new UserDao();
		dao.initData();
	}
	
	/**
	 * Clean data.
	 */
	@ApiMethod(name = "cleanData", httpMethod = HttpMethod.POST, path = "cleanData")
	public void cleanData() {
		UserDao dao = new UserDao();
		dao.cleanData();
	}
	
	@ApiMethod(name = "searchUser", httpMethod=HttpMethod.GET, path="search_user")
	public CollectionResponse<User> searchUser(
			@Nullable @Named("querySearch") String querySearch,
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("count") Integer count) throws CommonException {
		UserDao dao = new UserDao();
		return dao.searchUser(querySearch, cursorString, count);
	}


}
