package com.ltu.yealtube.endpoint;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.ltu.yealtube.dao.CategoryDao;
import com.ltu.yealtube.domain.Category;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.exception.ErrorCode;
import com.ltu.yealtube.exception.ErrorCodeDetail;

@Api(name = "categoryendpoint", namespace = @ApiNamespace(ownerDomain = "ltu.com", ownerName = "ltu.com", packagePath = "yealtube.domain"))
public class CategoryEndpoint {

	/**
	* Return a collection of categorys
	*
	* @param count The number of categorys
	* @return a list of Categorys
	*/
	@ApiMethod(name = "listCategory")
	public CollectionResponse<Category> listCategory(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("count") Integer count) {
		CategoryDao dao = new CategoryDao();
		return dao.list(cursorString, count);
	}
	
	/**
	* This inserts a new <code>Category</code> object.
	* @param category The object to be added.
	* @return The object to be added.
	*/
	@ApiMethod(name = "insertCategory")
	public Category insertCategory(Category category) throws CommonException {
		// If if is not null, then check if it exists. If yes, throw an
		// Exception
		// that it is already present
		if (category.getId() != null) {
			if (category.getId() == 0) {
				category.setId(null);
			} else {
				if (findRecord(category.getId()) != null) {
					throw new CommonException(ErrorCode.CONFLICT_EXCEPTION,
							ErrorCodeDetail.ERROR_EXIST_OBJECT);
				}
			}
		}
		// Since our @Id field is a Long, Objectify will generate a unique value
		// for us
		// when we use put
		CategoryDao dao = new CategoryDao();
		Category pos = dao.getCategoryByName(category.getName());
		//FIXME Check the code below
		if (pos == null) {
			dao.persist(category);
		} else {
			throw new CommonException(ErrorCode.CONFLICT_EXCEPTION,
					ErrorCodeDetail.ERROR_EXIST_OBJECT);
		}
		return category;
	}

	/**
	 * This updates an existing <code>Category</code> object.
	 * 
	 * @param category
	 *            The object to be added.
	 * @return The object to be updated.
	 */
	@ApiMethod(name = "updateCategory")
	public Category updateCategory(Category category) throws CommonException {
		Category oldCategory = findRecord(category.getId());
		if (oldCategory == null) {
			throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION,
					ErrorCodeDetail.ERROR_RECORD_NOT_FOUND);
		}
		CategoryDao dao = new CategoryDao();
		Category pos = dao.getCategoryByName(category.getName());
		//FIXME Check this logic
		if (pos == null || pos.getId().equals(category.getId())) {
			oldCategory.setName(category.getName());
//			if (category.getManager() != null) {
//				UserDao userDao = new UserDao();
//				User manager = userDao.getUserByLogin(category.getManager());
//				if (manager != null) {
//					category.setManager(manager.getLogin());
//				} else {
//					throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION,
//							ErrorCodeDetail.ERROR_USER_NOT_FOUND);
//				}
//			}
			dao.update(category);
		} else {
			throw new CommonException(ErrorCode.CONFLICT_EXCEPTION,
					ErrorCodeDetail.ERROR_EXIST_OBJECT);
		}
		return category;
	}

	/**
	 * This deletes an existing <code>Category</code> object.
	 *
	 * @param id            The id of the object to be deleted.
	 * @throws CommonException the proconco exception
	 */
	@ApiMethod(name = "removeCategory")
	public void removeCategory(@Named("id") Long id) throws CommonException {
		Category record = findRecord(id);
		if (record == null) {
			throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION,
					ErrorCodeDetail.ERROR_RECORD_NOT_FOUND);
		}
		CategoryDao dao = new CategoryDao();
		dao.delete(record);
	}
	
	/**
	 * Gets the category.
	 *
	 * @param id the id
	 * @return the category
	 */
	@ApiMethod(name = "getCategory")
	public Category getCategory(@Named("id") Long id) {
		return findRecord(id);
	}
	
	/**
	 * Find record.
	 *
	 * @param id the id
	 * @return the user main
	 */
	private Category findRecord(Long id) {
		CategoryDao dao = new CategoryDao();
		return dao.find(id);
	}
	
	/**
	 * Inits the data.
	 */
	@ApiMethod(name = "initData", httpMethod = HttpMethod.POST, path = "initData")
	public void initData() {
		CategoryDao dao = new CategoryDao();
		dao.initData();
	}
	
	/**
	 * Clean data.
	 */
	@ApiMethod(name = "cleanData", httpMethod = HttpMethod.POST, path = "cleanData")
	public void cleanData() {
		CategoryDao dao = new CategoryDao();
		dao.cleanData();
	}
	
	@ApiMethod(name = "searchCategory", httpMethod=HttpMethod.GET, path="search_category")
	public CollectionResponse<Category> searchCategory(
			@Nullable @Named("querySearch") String querySearch,
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("count") Integer count) throws CommonException {
		CategoryDao dao = new CategoryDao();
		return dao.searchCategory(querySearch, cursorString, count);
	}

}
