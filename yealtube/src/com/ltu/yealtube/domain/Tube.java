package com.ltu.yealtube.domain;

import java.util.Date;
import java.util.List;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.IgnoreSave;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

@Entity
public class Tube {

	@Id
	private String id;
	
	@Index
	private String title;
	
	@Index
	private String description;
	
	@Load
    private List<Key<Category>> categoryKeys;
	
	@Index
	private Long userId;
	
	@Index
	private int viewCount;
	
	@Index
	private int status;
	
	@Index
	private Date createdAt;
	
	private Date modifiedAt;
	
	@IgnoreSave
	private int likeCount;
	
	@IgnoreSave
	private int dislikeCount;
	
	@IgnoreSave
	private int favoriteCount;
	
	@IgnoreSave
	private int commentCount;
	
	@IgnoreSave
	private float rating;
	
	@IgnoreSave
	private String author;
	
	@Index
	private String tags;
	
	/** The categories. */
	@IgnoreSave
	private List<String> categories;
	
	@IgnoreSave
	private String embedHtml;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ApiResourceProperty(ignored = AnnotationBoolean.TRUE) 
	public List<Key<Category>> getCategoryKeys() {
		return categoryKeys;
	}

	public void setCategoryKeys(List<Key<Category>> categoryKeys) {
		this.categoryKeys = categoryKeys;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getDislikeCount() {
		return dislikeCount;
	}

	public void setDislikeCount(int dislikeCount) {
		this.dislikeCount = dislikeCount;
	}

	public int getFavoriteCount() {
		return favoriteCount;
	}

	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String getEmbedHtml() {
		return embedHtml;
	}

	public void setEmbedHtml(String embedHtml) {
		this.embedHtml = embedHtml;
	}
	
	public Tube() {
		
	}

	public Tube(String id, String title, String description,
			List<Key<Category>> categoryKeys, Long userId, int viewCount,
			int status, Date createdAt, Date modifiedAt, int likeCount,
			int dislikeCount, int favoriteCount, int commentCount,
			float rating, String author, String tags, List<String> categories,
			String embedHtml) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.categoryKeys = categoryKeys;
		this.userId = userId;
		this.viewCount = viewCount;
		this.status = status;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.likeCount = likeCount;
		this.dislikeCount = dislikeCount;
		this.favoriteCount = favoriteCount;
		this.commentCount = commentCount;
		this.rating = rating;
		this.author = author;
		this.tags = tags;
		this.categories = categories;
		this.embedHtml = embedHtml;
	}
	
	public Tube(String id, String title, String description,
			Long userId, int viewCount, int status) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.userId = userId;
		this.viewCount = viewCount;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Tube [id=" + id + ", title=" + title + ", description="
				+ description + ", categoryKeys=" + categoryKeys + ", userId="
				+ userId + ", viewCount=" + viewCount + ", status=" + status
				+ ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt
				+ ", likeCount=" + likeCount + ", dislikeCount=" + dislikeCount
				+ ", favoriteCount=" + favoriteCount + ", commentCount="
				+ commentCount + ", rating=" + rating + ", author=" + author
				+ ", tags=" + tags + ", categories=" + categories
				+ ", embedHtml=" + embedHtml + "]";
	}
	
}
