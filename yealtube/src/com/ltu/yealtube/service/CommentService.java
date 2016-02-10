package com.ltu.yealtube.service;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.client.http.HttpStatusCodes;
import com.google.api.server.spi.response.CollectionResponse;
import com.ltu.yealtube.dao.CommentDao;
import com.ltu.yealtube.domain.Comment;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.exception.ErrorCode;
import com.ltu.yealtube.exception.ErrorCodeDetail;

/**
 * The Class CommentService.
 * 
 * @author uyphu
 */
public class CommentService {

	/** The log. */
	// private final Logger log = Logger.getLogger(CommentService.class);

	/** The comment dao. */
	private CommentDao commentDao = CommentDao.getInstance();

	/** The instance. */
	private static CommentService instance = null;

	/**
	 * Instantiates a new comment dao.
	 */
	public CommentService() {

	}

	/**
	 * Gets the single instance of CommentService.
	 * 
	 * @return single instance of CommentService
	 */
	public static CommentService getInstance() {
		if (instance == null) {
			instance = new CommentService();
		}
		return instance;
	}

	/**
	 * List comment.
	 * 
	 * @param cursorString
	 *            the cursor string
	 * @param count
	 *            the count
	 * @return the collection response
	 */
	public CollectionResponse<Comment> listComment(String cursorString, Integer count) {
		return commentDao.list(cursorString, count);
	}

	/**
	 * Insert comment.
	 * 
	 * @param comment
	 *            the comment
	 * @return the comment
	 * @throws CommonException
	 *             the common exception
	 */
	public Comment insertComment(Comment comment) throws CommonException {
		if (comment != null && comment.getId() != null) {
			if (containsComment(comment)) {
				throw new CommonException(HttpStatusCodes.STATUS_CODE_FOUND, ErrorCodeDetail.ERROR_EXIST_OBJECT.getMsg());
			}
			return commentDao.persist(comment);
		} else {
			throw new CommonException(HttpStatusCodes.STATUS_CODE_BAD_GATEWAY, ErrorCodeDetail.ERROR_INPUT_NOT_VALID.getMsg());
		}
	}

	/**
	 * Contains comment.
	 * 
	 * @param comment
	 *            the comment
	 * @return true, if successful
	 */
	private boolean containsComment(Comment comment) {
		return containsComment(comment.getId());
	}

	/**
	 * Contains comment.
	 * 
	 * @param id
	 *            the id
	 * @return true, if successful
	 */
	private boolean containsComment(Long id) {
		boolean contains = true;
		if (id != null) {
			Comment item = commentDao.find(id);
			if (item == null) {
				contains = false;
			}
		} else {
			return contains = false;
		}

		return contains;
	}

	/**
	 * Update comment.
	 * 
	 * @param comment
	 *            the comment
	 * @return the comment
	 * @throws CommonException
	 *             the common exception
	 */
	public Comment updateComment(Comment comment) throws CommonException {

		if (comment != null && comment.getId() != null) {
			if (!containsComment(comment)) {
				throw new CommonException(HttpStatusCodes.STATUS_CODE_NOT_FOUND,
						ErrorCodeDetail.ERROR_RECORD_NOT_FOUND.getMsg());
			}
			return commentDao.update(comment);
		} else {
			throw new CommonException(HttpStatusCodes.STATUS_CODE_BAD_GATEWAY, ErrorCodeDetail.ERROR_INPUT_NOT_VALID.getMsg());
		}
	}

	/**
	 * Removes the comment.
	 * 
	 * @param id
	 *            the id
	 * @throws CommonException
	 *             the common exception
	 */
	public void removeComment(Long id) throws CommonException {
		Comment record = findRecord(id);
		if (record == null) {
			throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION, ErrorCodeDetail.ERROR_RECORD_NOT_FOUND);
		}
		commentDao.delete(record);
	}

	/**
	 * Find record.
	 * 
	 * @param id
	 *            the id
	 * @return the comment
	 */
	public Comment findRecord(Long id) {
		CommentDao dao = new CommentDao();
		return dao.find(id);
	}

	/**
	 * Inits the data.
	 */
	public void initData() {
		commentDao.initData();
	}

	/**
	 * Clean data.
	 */
	public void cleanData() {
		commentDao.cleanData();
	}

	/**
	 * Search comment.
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
	public CollectionResponse<Comment> searchComment(@Nullable @Named("querySearch") String querySearch,
			@Nullable @Named("cursor") String cursorString, @Nullable @Named("count") Integer count) throws CommonException {
		return commentDao.searchComment(querySearch, cursorString, count);
	}

}
