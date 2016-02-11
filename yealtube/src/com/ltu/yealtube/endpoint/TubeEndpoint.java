package com.ltu.yealtube.endpoint;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.client.http.HttpMethods;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.ltu.yealtube.domain.Tube;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.service.TubeService;

/**
 * The Class TubeEndpoint.
 * @author uyphu
 */
@Api(name = "tubeendpoint", namespace = @ApiNamespace(ownerDomain = "ltu.com", ownerName = "ltu.com", packagePath = "yealtube.domain"))
public class TubeEndpoint {

	/**
	 * Return a collection of tubes.
	 * 
	 * @param cursorString
	 *            the cursor string
	 * @param count
	 *            The number of tubes
	 * @return a list of Tubes
	 */
	@ApiMethod(name = "listTube", httpMethod = HttpMethods.GET)
	public CollectionResponse<Tube> listTube(@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("count") Integer count) {
		TubeService service = TubeService.getInstance();
		return service.listTube(cursorString, count);
	}

	/**
	 * This inserts a new <code>Tube</code> object.
	 * 
	 * @param tube
	 *            The object to be added.
	 * @return The object to be added.
	 * @throws CommonException
	 *             the common exception
	 */
	@ApiMethod(name = "insertTube")
	public Tube insertTube(Tube tube) throws CommonException {
		TubeService service = TubeService.getInstance();
		return service.insert(tube);
	}

	/**
	 * Insert you tube.
	 * 
	 * @param videoId
	 *            the video id
	 * @return the tube
	 * @throws CommonException
	 *             the common exception
	 */
	@ApiMethod(name = "insertYouTube")
	public Tube insertYouTube(@Named("videoId") String videoId) throws CommonException {
		TubeService service = TubeService.getInstance();
		return service.insert(videoId);
	}

	/**
	 * This updates an existing <code>Tube</code> object.
	 * 
	 * @param tube
	 *            The object to be added.
	 * @return The object to be updated.
	 * @throws CommonException
	 *             the common exception
	 */
	@ApiMethod(name = "updateTube")
	public Tube updateTube(Tube tube) throws CommonException {
		TubeService service = TubeService.getInstance();
		return service.update(tube);
	}

	/**
	 * This deletes an existing <code>Tube</code> object.
	 * 
	 * @param id
	 *            The id of the object to be deleted.
	 * @throws CommonException
	 *             the proconco exception
	 */
	@ApiMethod(name = "removeTube")
	public void removeTube(@Named("id") String id) throws CommonException {
		TubeService service = TubeService.getInstance();
		service.delete(id);
	}

	/**
	 * Gets the tube.
	 * 
	 * @param id
	 *            the id
	 * @return the tube
	 */
	@ApiMethod(name = "getTube", httpMethod = HttpMethods.GET)
	public Tube getTube(@Named("id") String id) {
		return findRecord(id);
	}

	/**
	 * Find record.
	 * 
	 * @param id
	 *            the id
	 * @return the user main
	 */
	private Tube findRecord(String id) {
		TubeService service = TubeService.getInstance();
		return service.findRecord(id);
	}

	/**
	 * Search tube.
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
	@ApiMethod(name = "searchTube", httpMethod = HttpMethod.GET, path = "search_tube")
	public CollectionResponse<Tube> searchTube(@Nullable @Named("querySearch") String querySearch,
			@Nullable @Named("cursor") String cursorString, @Nullable @Named("count") Integer count) throws CommonException {
		TubeService service = TubeService.getInstance();
		return service.searchTube(querySearch, cursorString, count);
	}
	
	/**
	 * Inits the data.
	 */
	@ApiMethod(name = "initData", httpMethod = HttpMethod.POST, path = "initData")
	public void initData() {
		TubeService service = TubeService.getInstance();
		service.initData();
	}

	/**
	 * Clean data.
	 */
	@ApiMethod(name = "cleanData", httpMethod = HttpMethod.POST, path = "cleanData")
	public void cleanData() {
		TubeService service = TubeService.getInstance();
		service.cleanData();
	}

}
