package com.ltu.yealtube.domain;

import java.util.Date;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.IgnoreSave;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.OnLoad;

@Entity
public class TopTube {

	/** The id. */
	@Id
	private String id;

	/** The title. */
	@Index
	private String title;

	/** The description. */
	private String description;

	/** The category ref. */
	@Index
	private Ref<Category> categoryRef;

	/** The user id. */
	@Index
	private Long userId;

	/** The view count. */
	@Index
	private int viewCount;

	/** The status. */
	@Index
	private int status;

	/** The created at. */
	@Index
	private Date createdAt;

	/** The modified at. */
	private Date modifiedAt;

	/** The like count. */
	@IgnoreSave
	private int likeCount;

	/** The dislike count. */
	@IgnoreSave
	private int dislikeCount;

	/** The favorite count. */
	@IgnoreSave
	private int favoriteCount;

	/** The comment count. */
	@IgnoreSave
	private int commentCount;

	/** The rating. */
	@Index
	private float rating;

	/** The author. */
	@IgnoreSave
	private String author;

	/** The category id. */
	@IgnoreSave
	private Category category;

	/** The tags. */
	@Index
	private String tags;

	/** The embed html. */
	@IgnoreSave
	private String embedHtml;
	
	/** The duration. */
	@Index
	private String duration; 
	
	/**
	 * On load.
	 */
	@OnLoad
	private void onLoad() {
//		if (categoryKey != null) {
//			CategoryDao dao = CategoryDao.getInstance();
//			category = dao.find(categoryKey.getId());
//		}
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 * 
	 * @param title
	 *            the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the user id.
	 * 
	 * @return the user id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 * 
	 * @param userId
	 *            the new user id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Gets the category key.
	 * 
	 * @return the category key
	 */
//	@ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
//	public Key<Category> getCategoryKey() {
//		return categoryKey;
//	}
//
//	/**
//	 * Sets the category key.
//	 * 
//	 * @param categoryKey
//	 *            the new category key
//	 */
//	public void setCategoryKey(Key<Category> categoryKey) {
//		this.categoryKey = categoryKey;
//	}
	
	public Category getCategory() {
		if (categoryRef != null) {
			return categoryRef.get();
		}
		return category;
	}

	/**
	 * Sets the category.
	 *
	 * @param category the new category
	 */
	public void setCategory(Category category) {
		if (category != null) {
			this.categoryRef = Ref.create(category);
			this.category = category;
		}
	}

	/**
	 * Gets the view count.
	 * 
	 * @return the view count
	 */
	public int getViewCount() {
		return viewCount;
	}

	/**
	 * Sets the view count.
	 * 
	 * @param viewCount
	 *            the new view count
	 */
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	/**
	 * Gets the status.
	 * 
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 * 
	 * @param status
	 *            the new status
	 */
	public void setStatus(int status) {
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
	 * @param createdAt
	 *            the new created at
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
	 * @param modifiedAt
	 *            the new modified at
	 */
	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	/**
	 * Gets the like count.
	 * 
	 * @return the like count
	 */
	public int getLikeCount() {
		return likeCount;
	}

	/**
	 * Sets the like count.
	 * 
	 * @param likeCount
	 *            the new like count
	 */
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	/**
	 * Gets the dislike count.
	 * 
	 * @return the dislike count
	 */
	public int getDislikeCount() {
		return dislikeCount;
	}

	/**
	 * Sets the dislike count.
	 * 
	 * @param dislikeCount
	 *            the new dislike count
	 */
	public void setDislikeCount(int dislikeCount) {
		this.dislikeCount = dislikeCount;
	}

	/**
	 * Gets the favorite count.
	 * 
	 * @return the favorite count
	 */
	public int getFavoriteCount() {
		return favoriteCount;
	}

	/**
	 * Sets the favorite count.
	 * 
	 * @param favoriteCount
	 *            the new favorite count
	 */
	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	/**
	 * Gets the comment count.
	 * 
	 * @return the comment count
	 */
	public int getCommentCount() {
		return commentCount;
	}

	/**
	 * Sets the comment count.
	 * 
	 * @param commentCount
	 *            the new comment count
	 */
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	/**
	 * Gets the rating.
	 * 
	 * @return the rating
	 */
	public float getRating() {
		return rating;
	}

	/**
	 * Sets the rating.
	 * 
	 * @param rating
	 *            the new rating
	 */
	public void setRating(float rating) {
		this.rating = rating;
	}

	/**
	 * Gets the author.
	 * 
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Sets the author.
	 * 
	 * @param author
	 *            the new author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Gets the tags.
	 * 
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * Sets the tags.
	 * 
	 * @param tags
	 *            the new tags
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}


	/**
	 * Gets the embed html.
	 * 
	 * @return the embed html
	 */
	public String getEmbedHtml() {
		return embedHtml;
	}

	/**
	 * Sets the embed html.
	 * 
	 * @param embedHtml
	 *            the new embed html
	 */
	public void setEmbedHtml(String embedHtml) {
		this.embedHtml = embedHtml;
	}
	
	/**
	 * Gets the category ref.
	 *
	 * @return the category ref
	 */
	@ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	public Ref<Category> getCategoryRef() {
		return categoryRef;
	}

	/**
	 * Sets the category ref.
	 *
	 * @param categoryRef the new category ref
	 */
	public void setCategoryRef(Ref<Category> categoryRef) {
		this.categoryRef = categoryRef;
	}
	
	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * Sets the duration.
	 *
	 * @param duration the new duration
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * Instantiates a new tube.
	 */
	public TopTube() {

	}

	/**
	 * Instantiates a new tube.
	 *
	 * @param id the id
	 * @param title the title
	 * @param description the description
	 * @param userId the user id
	 * @param viewCount the view count
	 * @param status the status
	 * @param createdAt the created at
	 * @param modifiedAt the modified at
	 * @param likeCount the like count
	 * @param dislikeCount the dislike count
	 * @param favoriteCount the favorite count
	 * @param commentCount the comment count
	 * @param rating the rating
	 * @param author the author
	 * @param tags the tags
	 * @param embedHtml the embed html
	 * @param category the category
	 */
	public TopTube(String id, String title, String description, Long userId, int viewCount, int status,
			Date createdAt, Date modifiedAt, int likeCount, int dislikeCount, int favoriteCount, int commentCount,
			float rating, String author, String tags, String embedHtml, Category category) {
		this.id = id;
		this.title = title;
		this.description = description;
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
		this.category = category;
		this.setCategory(category);
		this.tags = tags;
		this.embedHtml = embedHtml;
	}

	/**
	 * Instantiates a new tube.
	 *
	 * @param id the id
	 * @param title the title
	 * @param description the description
	 * @param userId the user id
	 * @param viewCount the view count
	 * @param status the status
	 * @param category the category
	 * @param tags the tags
	 */
	public TopTube(String id, String title, String description, Long userId, int viewCount, int status, Category category,
			String tags) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.userId = userId;
		this.viewCount = viewCount;
		this.status = status;
		this.category = category;
		this.tags = tags;
		this.setCategory(category);
	}
	
	/**
	 * Instantiates a new top movie.
	 *
	 * @param tube the tube
	 */
	public TopTube(Tube tube) {
		this.id = tube.getId();
		this.title = tube.getTitle();
		this.description = tube.getDescription();
		this.userId = tube.getUserId();
		this.viewCount = tube.getViewCount();
		this.status = tube.getStatus();
		this.createdAt = tube.getCreatedAt();
		this.modifiedAt = tube.getModifiedAt();
		this.likeCount = tube.getLikeCount();
		this.dislikeCount = tube.getDislikeCount();
		this.favoriteCount = tube.getFavoriteCount();
		this.commentCount = tube.getCommentCount();
		this.rating = tube.getRating();
		this.author = tube.getAuthor();
		this.category = tube.getCategory();
		this.setCategory(tube.getCategory());
		this.tags = tube.getTags();
		this.embedHtml = tube.getEmbedHtml();
		this.duration = tube.getDuration();
	}
}
