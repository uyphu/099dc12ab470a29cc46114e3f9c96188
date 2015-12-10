package com.ltu.yealtube.endpoint;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;
import com.ltu.yealtube.dao.AuthorityDao;
import com.ltu.yealtube.domain.Authority;

@Api(name = "authorityendpoint", namespace = @ApiNamespace(ownerDomain = "proconco.com", ownerName = "proconco.com", packagePath = "entity"))
public class AuthorityEndpoint {

	/**
	* Return a collection of authoritys
	*
	* @param count The number of authoritys
	* @return a list of Authoritys
	*/
	@ApiMethod(name = "listAuthority")
	public CollectionResponse<Authority> listAuthority(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("count") Integer count) {
		AuthorityDao dao = new AuthorityDao();
		return dao.list(cursorString, count);
	}
	
	/**
	* This inserts a new <code>Authority</code> object.
	* @param authority The object to be added.
	* @return The object to be added.
	*/
	@ApiMethod(name = "insertAuthority")
	public Authority insertAuthority(Authority authority) throws ConflictException {
		// If if is not null, then check if it exists. If yes, throw an
		// Exception
		// that it is already present
		if (authority.getId() != null) {
			if (authority.getId() == 0) {
				authority.setId(null);
			} else {
				if (findRecord(authority.getId()) != null) {
					throw new ConflictException("Object already exists");
				}
			}
		}
		// Since our @Id field is a Long, Objectify will generate a unique value
		// for us
		// when we use put
		AuthorityDao dao = new AuthorityDao();
		dao.persist(authority);
		return authority;
	}

	/**
	 * This updates an existing <code>Authority</code> object.
	 * 
	 * @param authority
	 *            The object to be added.
	 * @return The object to be updated.
	 */
	@ApiMethod(name = "updateAuthority")
	public Authority updateAuthority(Authority authority) throws NotFoundException {
		if (findRecord(authority.getId()) == null) {
			throw new NotFoundException("Authority Record does not exist");
		}
		AuthorityDao dao = new AuthorityDao();
		dao.update(authority);
		return authority;
	}

	/**
	 * This deletes an existing <code>Authority</code> object.
	 * 
	 * @param id
	 *            The id of the object to be deleted.
	 */
	@ApiMethod(name = "removeAuthority")
	public void removeAuthority(@Named("id") Long id) throws NotFoundException {
		Authority record = findRecord(id);
		if (record == null) {
			throw new NotFoundException("Authority Record does not exist");
		}
		AuthorityDao dao = new AuthorityDao();
		dao.delete(record);
	}
	
	/**
	 * Gets the authority.
	 *
	 * @param id the id
	 * @return the authority
	 */
	@ApiMethod(name = "getAuthority")
	public Authority getAuthority(@Named("id") Long id) {
		return findRecord(id);
	}
	
	/**
	 * Find record.
	 *
	 * @param id the id
	 * @return the user main
	 */
	private Authority findRecord(Long id) {
		AuthorityDao dao = new AuthorityDao();
		return dao.find(id);
	}
	
	/**
	 * Inits the data.
	 */
	@ApiMethod(name = "initData")
	public void initData() {
		AuthorityDao dao = new AuthorityDao();
		dao.initData();
	}
	
	/**
	 * Clean data.
	 */
	@ApiMethod(name = "cleanData")
	public void cleanData() {
		AuthorityDao dao = new AuthorityDao();
		dao.cleanData();
	}

}
