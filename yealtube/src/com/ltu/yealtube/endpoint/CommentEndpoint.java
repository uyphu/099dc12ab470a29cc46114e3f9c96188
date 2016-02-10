package com.ltu.yealtube.endpoint;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.ltu.yealtube.domain.Comment;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.service.CommentService;

@Api(name = "commentendpoint", namespace = @ApiNamespace(ownerDomain = "ltu.com", ownerName = "ltu.com", packagePath = "yealtube.domain"))
public class CommentEndpoint {

	/**
	* Return a collection of comments
	*
	* @param count The number of comments
	* @return a list of Comments
	*/
	@ApiMethod(name = "listComment")
	public CollectionResponse<Comment> listComment(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("count") Integer count) {
		CommentService service = CommentService.getInstance();
		return service.listComment(cursorString, count);
	}
	
	/**
	* This inserts a new <code>Comment</code> object.
	* @param comment The object to be added.
	* @return The object to be added.
	*/
	@ApiMethod(name = "insertComment")
	public Comment insertComment(Comment comment) throws CommonException {
		CommentService service = CommentService.getInstance();
		return service.insertComment(comment);
	}

	/**
	 * This updates an existing <code>Comment</code> object.
	 * 
	 * @param comment
	 *            The object to be added.
	 * @return The object to be updated.
	 */
	@ApiMethod(name = "updateComment")
	public Comment updateComment(Comment comment) throws CommonException {
		CommentService service = CommentService.getInstance();
		return service.updateComment(comment);
	}

	/**
	 * This deletes an existing <code>Comment</code> object.
	 *
	 * @param id            The id of the object to be deleted.
	 * @throws CommonException the proconco exception
	 */
	@ApiMethod(name = "removeComment")
	public void removeComment(@Named("id") Long id) throws CommonException {
		CommentService service = CommentService.getInstance();
		service.removeComment(id);
	}
	
	/**
	 * Gets the comment.
	 *
	 * @param id the id
	 * @return the comment
	 */
	@ApiMethod(name = "getComment")
	public Comment getComment(@Named("id") Long id) {
		return findRecord(id);
	}
	
	/**
	 * Find record.
	 *
	 * @param id the id
	 * @return the user main
	 */
	private Comment findRecord(Long id) {
		CommentService service = CommentService.getInstance();
		return service.findRecord(id);
	}
	
	/**
	 * Inits the data.
	 */
	@ApiMethod(name = "initData", httpMethod = HttpMethod.POST, path = "initData")
	public void initData() {
		CommentService service = CommentService.getInstance();
		service.initData();
	}
	
	/**
	 * Clean data.
	 */
	@ApiMethod(name = "cleanData", httpMethod = HttpMethod.POST, path = "cleanData")
	public void cleanData() {
		CommentService service = CommentService.getInstance();
		service.cleanData();
	}
	
	@ApiMethod(name = "searchComment", httpMethod=HttpMethod.GET, path="search_comment")
	public CollectionResponse<Comment> searchComment(
			@Nullable @Named("querySearch") String querySearch,
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("count") Integer count) throws CommonException {
		CommentService service = CommentService.getInstance();
		return service.searchComment(querySearch, cursorString, count);
	}


}
