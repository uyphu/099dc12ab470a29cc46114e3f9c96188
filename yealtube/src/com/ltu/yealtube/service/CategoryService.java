package com.ltu.yealtube.service;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.client.http.HttpStatusCodes;
import com.google.api.server.spi.response.CollectionResponse;
import com.ltu.yealtube.constants.Constant;
import com.ltu.yealtube.dao.CategoryDao;
import com.ltu.yealtube.domain.Category;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.exception.ErrorCode;
import com.ltu.yealtube.exception.ErrorCodeDetail;

// TODO: Auto-generated Javadoc
/**
 * The Class CategoryService.
 * 
 * @author uyphu
 */
public class CategoryService {

	/** The log. */
	// private final Logger log = Logger.getLogger(CategoryService.class);

	/** The category dao. */
	private CategoryDao categoryDao = CategoryDao.getInstance();

	/** The instance. */
	private static CategoryService instance = null;

	/**
	 * Instantiates a new category dao.
	 */
	public CategoryService() {

	}

	/**
	 * Gets the single instance of CategoryService.
	 * 
	 * @return single instance of CategoryService
	 */
	public static CategoryService getInstance() {
		if (instance == null) {
			instance = new CategoryService();
		}
		return instance;
	}

	/**
	 * List category.
	 * 
	 * @param cursorString
	 *            the cursor string
	 * @param count
	 *            the count
	 * @return the collection response
	 */
	public CollectionResponse<Category> listCategory(String cursorString, Integer count) {
		return categoryDao.list(cursorString, count);
	}

	/**
	 * Insert category.
	 * 
	 * @param category
	 *            the category
	 * @return the category
	 * @throws CommonException
	 *             the common exception
	 */
	public Category insertCategory(Category category) throws CommonException {
		if (category != null && category.getId() != null) {
			if (containsCategory(category)) {
				throw new CommonException(HttpStatusCodes.STATUS_CODE_FOUND, ErrorCodeDetail.ERROR_EXIST_OBJECT.getMsg());
			}
			return categoryDao.persist(category);
		} else {
			throw new CommonException(HttpStatusCodes.STATUS_CODE_BAD_GATEWAY, ErrorCodeDetail.ERROR_INPUT_NOT_VALID.getMsg());
		}
	}

	/**
	 * Contains category.
	 * 
	 * @param category
	 *            the category
	 * @return true, if successful
	 */
	private boolean containsCategory(Category category) {
		return containsCategory(category.getId());
	}

	/**
	 * Contains category.
	 * 
	 * @param id
	 *            the id
	 * @return true, if successful
	 */
	private boolean containsCategory(Long id) {
		boolean contains = true;
		if (id != null) {
			Category item = categoryDao.find(id);
			if (item == null) {
				contains = false;
			}
		} else {
			return contains = false;
		}

		return contains;
	}

	/**
	 * Update category.
	 * 
	 * @param category
	 *            the category
	 * @return the category
	 * @throws CommonException
	 *             the common exception
	 */
	public Category updateCategory(Category category) throws CommonException {

		if (category != null && category.getId() != null) {
			if (!containsCategory(category)) {
				throw new CommonException(HttpStatusCodes.STATUS_CODE_NOT_FOUND,
						ErrorCodeDetail.ERROR_RECORD_NOT_FOUND.getMsg());
			}
			return categoryDao.update(category);
		} else {
			throw new CommonException(HttpStatusCodes.STATUS_CODE_BAD_GATEWAY, ErrorCodeDetail.ERROR_INPUT_NOT_VALID.getMsg());
		}
	}

	/**
	 * Removes the category.
	 * 
	 * @param id
	 *            the id
	 * @throws CommonException
	 *             the common exception
	 */
	public void removeCategory(Long id) throws CommonException {
		Category record = findRecord(id);
		if (record == null) {
			throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION, ErrorCodeDetail.ERROR_RECORD_NOT_FOUND);
		}
		categoryDao.delete(record);
	}

	/**
	 * Find record.
	 * 
	 * @param id
	 *            the id
	 * @return the category
	 */
	public Category findRecord(Long id) {
		CategoryDao dao = new CategoryDao();
		return dao.find(id);
	}

	/**
	 * Inits the data.
	 */
	public void initData() {
		categoryDao.initData();
	}

	/**
	 * Clean data.
	 */
	public void cleanData() {
		categoryDao.cleanData();
	}

	/**
	 * Search category.
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
	public CollectionResponse<Category> searchCategory(@Nullable @Named("querySearch") String querySearch,
			@Nullable @Named("cursor") String cursorString, @Nullable @Named("count") Integer count) throws CommonException {
		return categoryDao.searchCategory(querySearch, cursorString, count);
	}
	
	/**
	 * Find one by name.
	 *
	 * @param name the name
	 * @return the category
	 */
	public Category findOneByName(String name) {
		return categoryDao.findOneByName(name);
	}
	
	/**
	 * Find one by keword.
	 *
	 * @param keyword the keyword
	 * @return the category
	 */
	public Category findOneByKeword(String keyword) {
		if (keyword != null) {
			keyword = keyword.toLowerCase();
			List<Category> categories = categoryDao.findAll();
			for (Category category : categories) {
				if (keyword.indexOf(category.getName().toLowerCase()) != -1) {
					return category;
				}
			}
		}
		return null;
	}
	
	/**
	 * Find one by tags.
	 *
	 * @param tags the tags
	 * @return the category
	 */
	public Category findOneByTags(String tags) {
		if (tags != null) {
			tags = tags.toLowerCase();
			List<Category> categories = categoryDao.findAll();
			for (Category category : categories) {
				if (category.getMetaKeyword() != null) {
					String[] metaKeys = category.getMetaKeyword().toLowerCase().split(Constant.COMMA_STRING);
					for (String metaKey : metaKeys) {
						if (tags.indexOf(metaKey.trim()) != -1) {
							return category;
						}
					}
				}
			}
		}
		return null;
	}

}
