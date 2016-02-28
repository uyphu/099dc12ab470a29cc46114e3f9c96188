package com.ltu.yealtube.endpoint;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.client.http.HttpStatusCodes;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.ltu.yealtube.constants.Constant;
import com.ltu.yealtube.domain.Category;
import com.ltu.yealtube.domain.Tube;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.service.TubeService;
import com.ltu.yealtube.utils.YoutubeUtils;

/**
 * The Class YoutubeEndpoint.
 * 
 * @author uyphu
 */
@Api(name = "youtubeendpoint", namespace = @ApiNamespace(ownerDomain = "ltu.com", ownerName = "ltu.com", packagePath = "yealtube.domain"), version = "v1", clientIds = { Constant.WEB_CLIENT_ID })
public class YoutubeEndpoint {

	/** The user service. */
	private TubeService tubeService = TubeService.getInstance();

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET
	 * method.
	 * 
	 * @param id
	 *            the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getVideo", httpMethod = HttpMethod.GET, path = "getVideo")
	public Tube getVideo(@Named("id") String id) {
		Tube tube = YoutubeUtils.getTube(id);
		return tube;
	}

	/**
	 * Insert video.
	 * 
	 * @param id
	 *            the id
	 * @return the tube
	 * @throws CommonException
	 *             the common exception
	 */
//	@ApiMethod(name = "insertVideo", httpMethod = HttpMethod.POST, path = "insertVideo")
//	public Tube insertVideo(@Named("id") String id) throws CommonException {
//		return tubeService.insert(id);
//	}
	
	@ApiMethod(name = "insertVideo", httpMethod = HttpMethod.POST, path = "insertVideo")
	public Tube insertVideo(@Named("id") String id, @Nullable @Named("rating") String rating) throws CommonException {
		if (rating != null) {
			try {
				float value = Float.parseFloat(rating);
				return tubeService.insert(id, value);
			} catch (Exception e) {
				throw new CommonException(HttpStatusCodes.STATUS_CODE_BAD_GATEWAY, e.getCause());
			}
			
		}
		return tubeService.insert(id);
	}

	/**
	 * Update video.
	 * 
	 * @param id
	 *            the id
	 * @return the tube
	 * @throws CommonException
	 *             the common exception
	 */
	@ApiMethod(name = "updateVideo", httpMethod = HttpMethod.POST, path = "updateVideo")
	public Tube updateVideo(@Named("id") String id) throws CommonException {
		Tube tube = YoutubeUtils.getTube(id);
		Tube obj = tubeService.findRecord(id);
		if (obj != null) {
			return tubeService.update(tube);
		} else {
			return tubeService.insert(tube);
		}
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET
	 * method.
	 * 
	 * @param id
	 *            the primary key of the java bean.
	 * @return The entity with primary key id.
	 * @throws CommonException
	 *             the common exception
	 */
	@ApiMethod(name = "getTube", httpMethod = HttpMethod.GET, path = "getTube")
	public Tube getTube(@Named("id") String id) throws CommonException {
		return tubeService.findRecord(id);
	}

	/**
	 * Gets the detail video.
	 * 
	 * @param id
	 *            the id
	 * @return the detail video
	 */
	@ApiMethod(name = "getDetailVideo", httpMethod = HttpMethod.GET, path = "getDetailVideo")
	public Tube getDetailVideo(@Named("id") String id) {
		return tubeService.getDetailTube(id);
	}
	
	/**
	 * Gets the all categories.
	 *
	 * @param id the id
	 * @return the all categories
	 */
	@ApiMethod(name = "getAllCategories", httpMethod = HttpMethod.GET, path = "getAllCategories")
	public CollectionResponse<Category> getAllCategories() {
		List<Category> categories = YoutubeUtils.getAllCategories();
		return CollectionResponse.<Category> builder().setItems(categories)
	            .setNextPageToken(null).build();
	}

}
