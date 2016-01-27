package com.ltu.yealtube.endpoint;

import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.ltu.yealtube.domain.Tube;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.service.TubeService;
import com.ltu.yealtube.utils.YoutubeUtils;

@Api(name = "youtubeendpoint", namespace = @ApiNamespace(ownerDomain = "ltu.com", ownerName = "ltu.com", packagePath = "yealtube.domain"))
public class YoutubeEndpoint {
	
	/** The user service. */
	private TubeService tubeService = TubeService.getInstance();

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getVideo", httpMethod=HttpMethod.GET, path="getVideo")
	public Tube getVideo(@Named("id") String id) {
		Tube tube = YoutubeUtils.getTube(id);
		return tube;
	}
	
	@ApiMethod(name = "insertVideo", httpMethod=HttpMethod.POST, path="insertVideo")
	public Tube insertVideo(@Named("id") String id) throws CommonException {
		Tube tube = YoutubeUtils.getTube(id);
		return tubeService.insertTube(tube);
	}
	
	@ApiMethod(name = "getTube", httpMethod=HttpMethod.GET, path="getTube")
	public Tube getTube(@Named("id") String id) throws CommonException{
		return tubeService.findRecord(id);
	}

}
