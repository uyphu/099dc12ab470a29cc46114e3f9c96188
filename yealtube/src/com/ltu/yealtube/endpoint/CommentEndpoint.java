package com.ltu.yealtube.endpoint;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.ltu.yealtube.dao.CommentDao;
import com.ltu.yealtube.domain.Comment;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.exception.ErrorCode;
import com.ltu.yealtube.exception.ErrorCodeDetail;

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
		CommentDao dao = new CommentDao();
		return dao.list(cursorString, count);
	}
	
	/**
	* This inserts a new <code>Comment</code> object.
	* @param comment The object to be added.
	* @return The object to be added.
	*/
	@ApiMethod(name = "insertComment")
	public Comment insertComment(Comment comment) throws CommonException {
		// If if is not null, then check if it exists. If yes, throw an
		// Exception
		// that it is already present
		if (comment.getId() != null) {
			if (comment.getId() == 0) {
				comment.setId(null);
			} else {
				if (findRecord(comment.getId()) != null) {
					throw new CommonException(ErrorCode.CONFLICT_EXCEPTION,
							ErrorCodeDetail.ERROR_EXIST_OBJECT);
				}
			}
		}
		// Since our @Id field is a Long, Objectify will generate a unique value
		// for us
		// when we use put
		CommentDao dao = new CommentDao();
		//Comment pos = dao.getCommentByName(comment.get);
		//FIXME Check the code below
		//if (pos == null) {
			dao.persist(comment);
//		} else {
//			throw new CommonException(ErrorCode.CONFLICT_EXCEPTION,
//					ErrorCodeDetail.ERROR_EXIST_OBJECT);
//		}
		return comment;
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
		Comment oldComment = findRecord(comment.getId());
		if (oldComment == null) {
			throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION,
					ErrorCodeDetail.ERROR_RECORD_NOT_FOUND);
		}
		CommentDao dao = new CommentDao();
		Comment pos = null; //dao.getCommentByName(comment.getName());
		//FIXME Check this logic
		if (pos == null || pos.getId().equals(comment.getId())) {
			//oldComment.setName(comment.getName());
//			if (comment.getManager() != null) {
//				UserDao userDao = new UserDao();
//				User manager = userDao.getUserByLogin(comment.getManager());
//				if (manager != null) {
//					comment.setManager(manager.getLogin());
//				} else {
//					throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION,
//							ErrorCodeDetail.ERROR_USER_NOT_FOUND);
//				}
//			}
			dao.update(comment);
		} else {
//			throw new CommonException(ErrorCode.CONFLICT_EXCEPTION,
//					ErrorCodeDetail.ERROR_EXIST_OBJECT);
		}
		return comment;
	}

	/**
	 * This deletes an existing <code>Comment</code> object.
	 *
	 * @param id            The id of the object to be deleted.
	 * @throws CommonException the proconco exception
	 */
	@ApiMethod(name = "removeComment")
	public void removeComment(@Named("id") Long id) throws CommonException {
		Comment record = findRecord(id);
		if (record == null) {
			throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION,
					ErrorCodeDetail.ERROR_RECORD_NOT_FOUND);
		}
		CommentDao dao = new CommentDao();
		dao.delete(record);
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
		CommentDao dao = new CommentDao();
		return dao.find(id);
	}
	
	/**
	 * Inits the data.
	 */
	@ApiMethod(name = "initData", httpMethod = HttpMethod.POST, path = "initData")
	public void initData() {
		CommentDao dao = new CommentDao();
		dao.initData();
	}
	
	/**
	 * Clean data.
	 */
	@ApiMethod(name = "cleanData", httpMethod = HttpMethod.POST, path = "cleanData")
	public void cleanData() {
		CommentDao dao = new CommentDao();
		dao.cleanData();
	}
	
	@ApiMethod(name = "searchComment", httpMethod=HttpMethod.GET, path="search_comment")
	public CollectionResponse<Comment> searchComment(
			@Nullable @Named("querySearch") String querySearch,
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("count") Integer count) throws CommonException {
		CommentDao dao = new CommentDao();
		return dao.searchComment(querySearch, cursorString, count);
	}


}
