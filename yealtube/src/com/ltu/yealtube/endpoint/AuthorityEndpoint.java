package com.ltu.yealtube.endpoint;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.ltu.yealtube.domain.Authority;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.service.AuthorityService;

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
		AuthorityService service = AuthorityService.getInstance();
		return service.listAuthority(cursorString, count);
	}
	
	/**
	* This inserts a new <code>Authority</code> object.
	* @param authority The object to be added.
	* @return The object to be added.
	*/
	@ApiMethod(name = "insertAuthority")
	public Authority insertAuthority(Authority authority) throws CommonException {
		AuthorityService service = AuthorityService.getInstance();
		return service.insertAuthority(authority);
	}

	/**
	 * This updates an existing <code>Authority</code> object.
	 * 
	 * @param authority
	 *            The object to be added.
	 * @return The object to be updated.
	 */
	@ApiMethod(name = "updateAuthority")
	public Authority updateAuthority(Authority authority) throws CommonException {
		AuthorityService service = AuthorityService.getInstance();
		return service.updateAuthority(authority);
	}

	/**
	 * This deletes an existing <code>Authority</code> object.
	 * 
	 * @param id
	 *            The id of the object to be deleted.
	 */
	@ApiMethod(name = "removeAuthority")
	public void removeAuthority(@Named("id") Long id) throws CommonException {
		AuthorityService service = AuthorityService.getInstance();
		service.removeAuthority(id);
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
		AuthorityService service = AuthorityService.getInstance();
		return service.findRecord(id);
	}
	
	/**
	 * Inits the data.
	 */
	@ApiMethod(name = "initData")
	public void initData() {
		AuthorityService service = AuthorityService.getInstance();
		service.initData();
	}
	
	/**
	 * Clean data.
	 */
	@ApiMethod(name = "cleanData")
	public void cleanData() {
		AuthorityService service = AuthorityService.getInstance();
		service.cleanData();
	}

}
