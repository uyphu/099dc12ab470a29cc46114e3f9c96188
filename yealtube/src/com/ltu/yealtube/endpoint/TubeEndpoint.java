package com.ltu.yealtube.endpoint;

import java.util.Calendar;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.ltu.yealtube.dao.TubeDao;
import com.ltu.yealtube.domain.Tube;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.exception.ErrorCode;
import com.ltu.yealtube.exception.ErrorCodeDetail;

@Api(name = "tubeendpoint", namespace = @ApiNamespace(ownerDomain = "ltu.com", ownerName = "ltu.com", packagePath = "yealtube.domain"))
public class TubeEndpoint {

	/**
	* Return a collection of tubes
	*
	* @param count The number of tubes
	* @return a list of Tubes
	*/
	@ApiMethod(name = "listTube")
	public CollectionResponse<Tube> listTube(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("count") Integer count) {
		TubeDao dao = new TubeDao();
		return dao.list(cursorString, count);
	}
	
	/**
	* This inserts a new <code>Tube</code> object.
	* @param tube The object to be added.
	* @return The object to be added.
	*/
	@ApiMethod(name = "insertTube")
	public Tube insertTube(Tube tube) throws CommonException {
		// If if is not null, then check if it exists. If yes, throw an
		// Exception
		// that it is already present
		if (tube.getId() != null) {
			if (tube.getId() == 0) {
				tube.setId(null);
			} else {
				if (findRecord(tube.getId()) != null) {
					throw new CommonException(ErrorCode.CONFLICT_EXCEPTION,
							ErrorCodeDetail.ERROR_EXIST_OBJECT);
				}
			}
		}
		// Since our @Id field is a Long, Objectify will generate a unique value
		// for us
		// when we use put
		TubeDao dao = new TubeDao();
		Tube pos = dao.getTubeByUrl(tube.getUrl());
		//FIXME Check the code below
		if (pos == null) {
			tube.setDateAdded(Calendar.getInstance().getTime());
			tube.setDateModified(Calendar.getInstance().getTime());
			dao.persist(tube);
		} else {
			throw new CommonException(ErrorCode.CONFLICT_EXCEPTION,
					ErrorCodeDetail.ERROR_EXIST_OBJECT);
		}
		return tube;
	}

	/**
	 * This updates an existing <code>Tube</code> object.
	 * 
	 * @param tube
	 *            The object to be added.
	 * @return The object to be updated.
	 */
	@ApiMethod(name = "updateTube")
	public Tube updateTube(Tube tube) throws CommonException {
		Tube oldTube = findRecord(tube.getId());
		if (oldTube == null) {
			throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION,
					ErrorCodeDetail.ERROR_RECORD_NOT_FOUND);
		}
		TubeDao dao = new TubeDao();
		tube.setDateModified(Calendar.getInstance().getTime());
		dao.update(tube);
		return tube;
	}

	/**
	 * This deletes an existing <code>Tube</code> object.
	 *
	 * @param id            The id of the object to be deleted.
	 * @throws CommonException the proconco exception
	 */
	@ApiMethod(name = "removeTube")
	public void removeTube(@Named("id") Long id) throws CommonException {
		Tube record = findRecord(id);
		if (record == null) {
			throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION,
					ErrorCodeDetail.ERROR_RECORD_NOT_FOUND);
		}
		TubeDao dao = new TubeDao();
		dao.delete(record);
	}
	
	/**
	 * Gets the tube.
	 *
	 * @param id the id
	 * @return the tube
	 */
	@ApiMethod(name = "getTube")
	public Tube getTube(@Named("id") Long id) {
		return findRecord(id);
	}
	
	/**
	 * Find record.
	 *
	 * @param id the id
	 * @return the user main
	 */
	private Tube findRecord(Long id) {
		TubeDao dao = new TubeDao();
		return dao.find(id);
	}
	
	/**
	 * Inits the data.
	 */
	@ApiMethod(name = "initData", httpMethod = HttpMethod.POST, path = "initData")
	public void initData() {
		TubeDao dao = new TubeDao();
		dao.initData();
	}
	
	/**
	 * Clean data.
	 */
	@ApiMethod(name = "cleanData", httpMethod = HttpMethod.POST, path = "cleanData")
	public void cleanData() {
		TubeDao dao = new TubeDao();
		dao.cleanData();
	}
	
	@ApiMethod(name = "searchTube", httpMethod=HttpMethod.GET, path="search_tube")
	public CollectionResponse<Tube> searchTube(
			@Nullable @Named("querySearch") String querySearch,
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("count") Integer count) throws CommonException {
		TubeDao dao = new TubeDao();
		return dao.searchTube(querySearch, cursorString, count);
	}


}
