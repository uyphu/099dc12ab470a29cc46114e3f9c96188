package com.ltu.yealtube.domain;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * The Class Comment.
 * @author uyphu
 */
@Entity
public class Comment {

	/** The id. */
	@Id
	private Long id;
	
	/** The tube id. */
	@Index
	private String tubeId;
	
	/** The user id. */
	@Index
	private Long userId;
	
	/** The text. */
	@Index
	private String text;
	
	/** The rating. */
	@Index
	private int rating;
	
	/** The status. */
	@Index
	private int status;
	
	/** The created at. */
	@Index
	private Date createdAt;
	
	/** The modified at. */
	private Date modifiedAt;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public final Long getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public final void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the tube id.
	 *
	 * @return the tube id
	 */
	public final String getTubeId() {
		return this.tubeId;
	}

	/**
	 * Sets the tube id.
	 *
	 * @param tubeId the new tube id
	 */
	public final void setTubeId(String tubeId) {
		this.tubeId = tubeId;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public final Long getUserId() {
		return this.userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public final void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public final String getText() {
		return this.text;
	}

	/**
	 * Sets the text.
	 *
	 * @param text the new text
	 */
	public final void setText(String text) {
		this.text = text;
	}

	/**
	 * Gets the rating.
	 *
	 * @return the rating
	 */
	public final int getRating() {
		return this.rating;
	}

	/**
	 * Sets the rating.
	 *
	 * @param rating the new rating
	 */
	public final void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public final int getStatus() {
		return this.status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public final void setStatus(int status) {
		this.status = status;
	}

	
	
	/**
	 * Gets the created at.
	 *
	 * @return the created at
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * Sets the created at.
	 *
	 * @param createdAt the new created at
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * Gets the modified at.
	 *
	 * @return the modified at
	 */
	public Date getModifiedAt() {
		return modifiedAt;
	}

	/**
	 * Sets the modified at.
	 *
	 * @param modifiedAt the new modified at
	 */
	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	/**
	 * Instantiates a new comment.
	 */
	public Comment() {
		
	}

	/**
	 * Instantiates a new comment.
	 *
	 * @param id the id
	 * @param tubeId the tube id
	 * @param userId the user id
	 * @param text the text
	 * @param rating the rating
	 * @param status the status
	 * @param createdAt the created at
	 * @param modifiedAt the modified at
	 */
	public Comment(Long id, String tubeId, Long userId, String text, int rating, int status, Date createdAt,
			Date modifiedAt) {
		this.id = id;
		this.tubeId = tubeId;
		this.userId = userId;
		this.text = text;
		this.rating = rating;
		this.status = status;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
	
	/**
	 * Instantiates a new comment.
	 *
	 * @param tubeId the tube id
	 * @param userId the user id
	 * @param text the text
	 * @param rating the rating
	 * @param status the status
	 * @param createdAt the created at
	 * @param modifiedAt the modified at
	 */
	public Comment(String tubeId, Long userId, String text, int rating, int status, Date createdAt,
			Date modifiedAt) {
		this.tubeId = tubeId;
		this.userId = userId;
		this.text = text;
		this.rating = rating;
		this.status = status;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
	
}
