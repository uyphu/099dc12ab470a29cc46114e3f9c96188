package com.ltu.yealtube.endpoint;

import java.util.Date;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpStatusCodes;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.ltu.yealtube.domain.TopTube;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.exception.ErrorCodeDetail;
import com.ltu.yealtube.service.TopTubeService;
import com.ltu.yealtube.utils.AppUtils;

@Api(name = "toptubeendpoint", namespace = @ApiNamespace(ownerDomain = "ltu.com", ownerName = "ltu.com", packagePath = "yealtube.domain"))
public class TopTubeEndpoint {

	/**
	 * Return a collection of topTubes.
	 * 
	 * @param cursorString
	 *            the cursor string
	 * @param count
	 *            The number of topTubes
	 * @return a list of TopTubes
	 */
	@ApiMethod(name = "listTopTube", httpMethod = HttpMethods.GET)
	public CollectionResponse<TopTube> listTopTube(@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("count") Integer count) {
		TopTubeService service = TopTubeService.getInstance();
		return service.listTopTube(cursorString, count);
	}

	/**
	 * This inserts a new <code>TopTube</code> object.
	 * 
	 * @param topTube
	 *            The object to be added.
	 * @return The object to be added.
	 * @throws CommonException
	 *             the common exception
	 */
	@ApiMethod(name = "insertTopTube")
	public TopTube insertTopTube(TopTube topTube) throws CommonException {
		TopTubeService service = TopTubeService.getInstance();
		return service.insert(topTube);
	}

	/**
	 * Insert you topTube.
	 * 
	 * @param videoId
	 *            the video id
	 * @return the topTube
	 * @throws CommonException
	 *             the common exception
	 */
	@ApiMethod(name = "insertYouTopTube", httpMethod = HttpMethods.POST, path = "insertYouTopTube")
	public TopTube insertYouTopTube(@Named("videoId") String videoId) throws CommonException {
		TopTubeService service = TopTubeService.getInstance();
		return service.insert(videoId);
	}

	/**
	 * This updates an existing <code>TopTube</code> object.
	 * 
	 * @param topTube
	 *            The object to be added.
	 * @return The object to be updated.
	 * @throws CommonException
	 *             the common exception
	 */
	@ApiMethod(name = "updateTopTube")
	public TopTube updateTopTube(TopTube topTube) throws CommonException {
		TopTubeService service = TopTubeService.getInstance();
		return service.update(topTube);
	}

	/**
	 * This deletes an existing <code>TopTube</code> object.
	 * 
	 * @param id
	 *            The id of the object to be deleted.
	 * @throws CommonException
	 *             the proconco exception
	 */
	@ApiMethod(name = "removeTopTube")
	public void removeTopTube(@Named("id") String id) throws CommonException {
		TopTubeService service = TopTubeService.getInstance();
		service.delete(id);
	}

	/**
	 * Gets the topTube.
	 * 
	 * @param id
	 *            the id
	 * @return the topTube
	 */
	@ApiMethod(name = "getTopTube", httpMethod = HttpMethods.GET)
	public TopTube getTopTube(@Named("id") String id) {
		return findRecord(id);
	}

	/**
	 * Find record.
	 * 
	 * @param id
	 *            the id
	 * @return the user main
	 */
	private TopTube findRecord(String id) {
		TopTubeService service = TopTubeService.getInstance();
		return service.findRecord(id);
	}

	/**
	 * Search topTube.
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
	@ApiMethod(name = "searchTopTube", httpMethod = HttpMethod.GET, path = "searchTopTube")
	public CollectionResponse<TopTube> searchTopTube(@Nullable @Named("querySearch") String querySearch,
			@Nullable @Named("cursor") String cursorString, @Nullable @Named("count") Integer count) throws CommonException {
		TopTubeService service = TopTubeService.getInstance();
		return service.searchTopTube(querySearch, cursorString, count);
	}

	/**
	 * Inits the data.
	 */
	@ApiMethod(name = "initData", httpMethod = HttpMethod.POST, path = "initData")
	public void initData() {
		TopTubeService service = TopTubeService.getInstance();
		service.initData();
	}

	/**
	 * Clean data.
	 */
	@ApiMethod(name = "cleanData", httpMethod = HttpMethod.POST, path = "cleanData")
	public void cleanData() {
		TopTubeService service = TopTubeService.getInstance();
		service.cleanData();
	}

	/**
	 * Clean data by status.
	 * 
	 * @param status
	 *            the status
	 * @throws CommonException
	 *             the common exception
	 */
	@ApiMethod(name = "cleanDataByStatus", httpMethod = HttpMethod.POST, path = "cleanDataByStatus")
	public void cleanDataByStatus(@Nullable @Named("status") Integer status) throws CommonException {
		TopTubeService service = TopTubeService.getInstance();
		service.cleanData(status);
	}

	/**
	 * Search top plays.
	 * 
	 * @param cursorString
	 *            the cursor string
	 * @param count
	 *            the count
	 * @return the collection response
	 * @throws CommonException
	 *             the common exception
	 */
	@ApiMethod(name = "getTopPlays", httpMethod = HttpMethod.GET, path = "getTopPlays")
	public CollectionResponse<TopTube> getTopPlays(@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("count") Integer count) throws CommonException {
		TopTubeService service = TopTubeService.getInstance();
		return service.getTopPlays(cursorString, count);
	}

	/**
	 * Gets the top musics.
	 * 
	 * @param cursorString
	 *            the cursor string
	 * @param count
	 *            the count
	 * @return the top musics
	 * @throws CommonException
	 *             the common exception
	 */
	@ApiMethod(name = "getTopMusics", httpMethod = HttpMethod.GET, path = "getTopMusics")
	public CollectionResponse<TopTube> getTopMusics(@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("count") Integer count) throws CommonException {
		TopTubeService service = TopTubeService.getInstance();
		return service.getTopMusics(cursorString, count);
	}
	
	/**
	 * Gets the top movies.
	 *
	 * @param cursorString the cursor string
	 * @param count the count
	 * @return the top movies
	 * @throws CommonException the common exception
	 */
	@ApiMethod(name = "getTopMovies", httpMethod = HttpMethod.GET, path = "getTopMovies")
	public CollectionResponse<TopTube> getTopMovies(@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("count") Integer count) throws CommonException {
		TopTubeService service = TopTubeService.getInstance();
		return service.getTopMovies(cursorString, count);
	}
	
	/**
	 * Search tubes by created at.
	 *
	 * @param field the field
	 * @param createdAt the created at
	 * @param cursorString the cursor string
	 * @param count the count
	 * @return the collection response
	 * @throws CommonException the common exception
	 */
	@ApiMethod(name = "searchTubesByCreatedAt", httpMethod = HttpMethod.GET, path = "searchTubesByCreatedAt")
	public CollectionResponse<TopTube> searchTubesByCreatedAt(@Named("field") String field, @Named("createdAt") String createdAt,
			@Nullable @Named("cursor") String cursorString, @Nullable @Named("count") Integer count) throws CommonException {
		TopTubeService service = TopTubeService.getInstance();
		Date value = AppUtils.toDate(createdAt); 
		if ( value == null) {
			throw new CommonException(HttpStatusCodes.STATUS_CODE_BAD_GATEWAY, ErrorCodeDetail.ERROR_INPUT_NOT_VALID.getMsg());
		}
		return service.searchTubes(field, value, cursorString, count);
	}
	
	/**
	 * Gets the deleted tubes.
	 *
	 * @param cursorString the cursor string
	 * @param count the count
	 * @return the deleted tubes
	 * @throws CommonException the common exception
	 */
	@ApiMethod(name = "getDeletedTubes", httpMethod = HttpMethod.GET, path = "getDeletedTubes")
	public CollectionResponse<TopTube> getDeletedTubes(
			@Nullable @Named("cursor") String cursorString, @Nullable @Named("count") Integer count) throws CommonException {
		TopTubeService service = TopTubeService.getInstance();
		return service.getDeletedTubes(cursorString, count);
	}

}
