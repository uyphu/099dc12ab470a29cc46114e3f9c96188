package com.ltu.yealtube.domain;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Comment {

	@Id
	private Long id;
	
	@Index
	private Long tubeId;
	
	@Index
	private Long userId;
	
	@Index
	private String text;
	
	@Index
	private int rating;
	
	@Index
	private int status;
	
	private Date dateAdded;
	
	private Date dateModified;

	public final Long getId() {
		return this.id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public final Long getTubeId() {
		return this.tubeId;
	}

	public final void setTubeId(Long tubeId) {
		this.tubeId = tubeId;
	}

	public final Long getUserId() {
		return this.userId;
	}

	public final void setUserId(Long userId) {
		this.userId = userId;
	}

	public final String getText() {
		return this.text;
	}

	public final void setText(String text) {
		this.text = text;
	}

	public final int getRating() {
		return this.rating;
	}

	public final void setRating(int rating) {
		this.rating = rating;
	}

	public final int getStatus() {
		return this.status;
	}

	public final void setStatus(int status) {
		this.status = status;
	}

	public final Date getDateAdded() {
		return this.dateAdded;
	}

	public final void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public final Date getDateModified() {
		return this.dateModified;
	}

	public final void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}
	
	public Comment() {
		
	}

	public Comment(Long id, Long tubeId, Long userId, String text, int rating, int status, Date dateAdded,
			Date dateModified) {
		this.id = id;
		this.tubeId = tubeId;
		this.userId = userId;
		this.text = text;
		this.rating = rating;
		this.status = status;
		this.dateAdded = dateAdded;
		this.dateModified = dateModified;
	}
	
	public Comment(Long tubeId, Long userId, String text, int rating, int status, Date dateAdded,
			Date dateModified) {
		this.tubeId = tubeId;
		this.userId = userId;
		this.text = text;
		this.rating = rating;
		this.status = status;
		this.dateAdded = dateAdded;
		this.dateModified = dateModified;
	}
	
}
