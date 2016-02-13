package com.ltu.yealtube.endpoint;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.ltu.yealtube.domain.ParamValue;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.service.ParamValueService;

@Api(name = "paramvalueendpoint", namespace = @ApiNamespace(ownerDomain = "ltu.com", ownerName = "ltu.com", packagePath="yealtube.domain"))
public class ParamValueEndpoint {

	/**
	 * Return a collection of paramValues.
	 *
	 * @param cursorString the cursor string
	 * @param count The number of paramValues
	 * @return a list of ParamValues
	 */
	@ApiMethod(name = "listParamValue")
	public CollectionResponse<ParamValue> listParamValue(@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("count") Integer count) {
		ParamValueService service = ParamValueService.getInstance();
		return service.listParamValue(cursorString, count);
	}
	
	/**
	 * This inserts a new <code>ParamValue</code> object.
	 *
	 * @param paramValue The object to be added.
	 * @return The object to be added.
	 * @throws CommonException the common exception
	 */
	@ApiMethod(name = "insertParamValue")
	public ParamValue insertParamValue(ParamValue paramValue) throws CommonException {
		ParamValueService service = ParamValueService.getInstance();
		return service.insertParamValue(paramValue);
	}

	/**
	 * This updates an existing <code>ParamValue</code> object.
	 *
	 * @param paramValue            The object to be added.
	 * @return The object to be updated.
	 * @throws CommonException the common exception
	 */
	@ApiMethod(name = "updateParamValue")
	public ParamValue updateParamValue(ParamValue paramValue) throws CommonException {
		ParamValueService service = ParamValueService.getInstance();
		return service.updateParamValue(paramValue);
	}

	/**
	 * This deletes an existing <code>ParamValue</code> object.
	 *
	 * @param id            The id of the object to be deleted.
	 * @throws CommonException the proconco exception
	 */
	@ApiMethod(name = "removeParamValue")
	public void removeParamValue(@Named("id") String id) throws CommonException {
		ParamValueService service = ParamValueService.getInstance();
		service.removeParamValue(id);
	}
	
	/**
	 * Gets the paramValue.
	 *
	 * @param id the id
	 * @return the paramValue
	 */
	@ApiMethod(name = "getParamValue")
	public ParamValue getParamValue(@Named("id") String id) {
		return findRecord(id);
	}
	
	/**
	 * Find record.
	 *
	 * @param id the id
	 * @return the user main
	 */
	private ParamValue findRecord(String id) {
		ParamValueService service = ParamValueService.getInstance();
		return service.findRecord(id);
	}
	
	/**
	 * Inits the data.
	 */
	@ApiMethod(name = "initData", httpMethod = HttpMethod.POST, path = "initData")
	public void initData() throws CommonException{
		ParamValueService service = ParamValueService.getInstance();
		service.initData();
	}
	
	/**
	 * Clean data.
	 */
	@ApiMethod(name = "cleanData", httpMethod = HttpMethod.POST, path = "cleanData")
	public void cleanData() throws CommonException{
		ParamValueService service = ParamValueService.getInstance();
		service.cleanData();
	}
	
	/**
	 * Search paramValue.
	 *
	 * @param querySearch the query search
	 * @param cursorString the cursor string
	 * @param count the count
	 * @return the collection response
	 * @throws CommonException the common exception
	 */
	@ApiMethod(name = "searchParamValue", httpMethod=HttpMethod.GET, path="searchParamValue")
	public CollectionResponse<ParamValue> searchParamValue(
			@Nullable @Named("querySearch") String querySearch,
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("count") Integer count) throws CommonException {
		ParamValueService service = ParamValueService.getInstance();
		return service.searchParamValue(querySearch, cursorString, count);
	}

}
