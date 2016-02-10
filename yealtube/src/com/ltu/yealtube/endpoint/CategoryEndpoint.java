package com.ltu.yealtube.endpoint;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.ltu.yealtube.domain.Category;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.service.CategoryService;

/**
 * The Class CategoryEndpoint.
 * @author uyphu
 */
@Api(name = "categoryendpoint", namespace = @ApiNamespace(ownerDomain = "ltu.com", ownerName = "ltu.com", packagePath = "yealtube.domain"))
public class CategoryEndpoint {

	/**
	 * Return a collection of categorys.
	 *
	 * @param cursorString the cursor string
	 * @param count The number of categorys
	 * @return a list of Categorys
	 */
	@ApiMethod(name = "listCategory")
	public CollectionResponse<Category> listCategory(@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("count") Integer count) {
		CategoryService service = CategoryService.getInstance();
		return service.listCategory(cursorString, count);
	}
	
	/**
	 * This inserts a new <code>Category</code> object.
	 *
	 * @param category The object to be added.
	 * @return The object to be added.
	 * @throws CommonException the common exception
	 */
	@ApiMethod(name = "insertCategory")
	public Category insertCategory(Category category) throws CommonException {
		CategoryService service = CategoryService.getInstance();
		return service.insertCategory(category);
	}

	/**
	 * This updates an existing <code>Category</code> object.
	 *
	 * @param category            The object to be added.
	 * @return The object to be updated.
	 * @throws CommonException the common exception
	 */
	@ApiMethod(name = "updateCategory")
	public Category updateCategory(Category category) throws CommonException {
		CategoryService service = CategoryService.getInstance();
		return service.updateCategory(category);
	}

	/**
	 * This deletes an existing <code>Category</code> object.
	 *
	 * @param id            The id of the object to be deleted.
	 * @throws CommonException the proconco exception
	 */
	@ApiMethod(name = "removeCategory")
	public void removeCategory(@Named("id") Long id) throws CommonException {
		CategoryService service = CategoryService.getInstance();
		service.removeCategory(id);
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
		CategoryService service = CategoryService.getInstance();
		return service.findRecord(id);
	}
	
	/**
	 * Inits the data.
	 */
	@ApiMethod(name = "initData", httpMethod = HttpMethod.POST, path = "initData")
	public void initData() {
		CategoryService service = CategoryService.getInstance();
		service.initData();
	}
	
	/**
	 * Clean data.
	 */
	@ApiMethod(name = "cleanData", httpMethod = HttpMethod.POST, path = "cleanData")
	public void cleanData() {
		CategoryService service = CategoryService.getInstance();
		service.cleanData();
	}
	
	/**
	 * Search category.
	 *
	 * @param querySearch the query search
	 * @param cursorString the cursor string
	 * @param count the count
	 * @return the collection response
	 * @throws CommonException the common exception
	 */
	@ApiMethod(name = "searchCategory", httpMethod=HttpMethod.GET, path="search_category")
	public CollectionResponse<Category> searchCategory(
			@Nullable @Named("querySearch") String querySearch,
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("count") Integer count) throws CommonException {
		CategoryService service = CategoryService.getInstance();
		return service.searchCategory(querySearch, cursorString, count);
	}

}
