package com.ltu.yealtube.domain;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Tube {

	@Id
	private Long id;
	
	@Index
	private Long userId;
	
	@Index
	private Long categoryId;
	
	@Index
	private String name;
	
	@Index
	private String description;
	
	@Index
	private String url;
	
	private int like;
	
	private int dislike;
	
	private float rating;
	
	@Index
	private int status;
	
	@Index
	private Date dateAdded;
	
	private Date dateModified;

	public final Long getId() {
		return this.id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public final Long getUserId() {
		return this.userId;
	}

	public final void setUserId(Long userId) {
		this.userId = userId;
	}

	public final Long getCategoryId() {
		return this.categoryId;
	}

	public final void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public final String getName() {
		return this.name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getDescription() {
		return this.description;
	}

	public final void setDescription(String description) {
		this.description = description;
	}

	public final String getUrl() {
		return this.url;
	}

	public final void setUrl(String url) {
		this.url = url;
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
	
	public final int getLike() {
		return this.like;
	}

	public final void setLike(int like) {
		this.like = like;
	}
	
	public final int getDislike() {
		return this.dislike;
	}

	public final void setDislike(int dislike) {
		this.dislike = dislike;
	}

	public final float getRating() {
		return this.rating;
	}

	public final void setRating(float rating) {
		this.rating = rating;
	}

	public Tube() {
		
	}

	public Tube(Long id, Long userId, Long categoryId, String name, String description, String url, int like,
			int dislike, float rating, int status, Date dateAdded, Date dateModified) {
		this.id = id;
		this.userId = userId;
		this.categoryId = categoryId;
		this.name = name;
		this.description = description;
		this.url = url;
		this.like = like;
		this.dislike = dislike;
		this.rating = rating;
		this.status = status;
		this.dateAdded = dateAdded;
		this.dateModified = dateModified;
	}
	
	public Tube(Long userId, Long categoryId, String name, String description, String url, int like,
			int dislike, float rating, int status, Date dateAdded, Date dateModified) {
		this.userId = userId;
		this.categoryId = categoryId;
		this.name = name;
		this.description = description;
		this.url = url;
		this.like = like;
		this.dislike = dislike;
		this.rating = rating;
		this.status = status;
		this.dateAdded = dateAdded;
		this.dateModified = dateModified;
	}

}
