package com.ltu.yealtube.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

// TODO: Auto-generated Javadoc
/**
 * The Class Category.
 */
@Entity
public class Category {

	/** The id. */
	@Id
	private Long id;
	
	/** The name. */
	@Index
	private String name;
	
	/** The parent id. */
	@Index
	private Long parentId;
	
	/** The description. */
	@Index
	private String description;
	
	/** The meta title. */
	private String metaTitle;
	
	/** The meta description. */
	private String metaDescription;
	
	/** The meta keyword. */
	@Index
	private String metaKeyword;

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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public final String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the parent id.
	 *
	 * @return the parent id
	 */
	public final Long getParentId() {
		return this.parentId;
	}

	/**
	 * Sets the parent id.
	 *
	 * @param parentId the new parent id
	 */
	public final void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public final String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public final void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the meta title.
	 *
	 * @return the meta title
	 */
	public final String getMetaTitle() {
		return this.metaTitle;
	}

	/**
	 * Sets the meta title.
	 *
	 * @param metaTitle the new meta title
	 */
	public final void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
	}

	/**
	 * Gets the meta description.
	 *
	 * @return the meta description
	 */
	public final String getMetaDescription() {
		return this.metaDescription;
	}

	/**
	 * Sets the meta description.
	 *
	 * @param metaDescription the new meta description
	 */
	public final void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	/**
	 * Gets the meta keyword.
	 *
	 * @return the meta keyword
	 */
	public final String getMetaKeyword() {
		return this.metaKeyword;
	}

	/**
	 * Sets the meta keyword.
	 *
	 * @param metaKeyword the new meta keyword
	 */
	public final void setMetaKeyword(String metaKeyword) {
		this.metaKeyword = metaKeyword;
	}
	
	/**
	 * Instantiates a new category.
	 */
	public Category() {
		
	}

	/**
	 * Instantiates a new category.
	 *
	 * @param id the id
	 * @param name the name
	 * @param parentId the parent id
	 * @param description the description
	 * @param metaTitle the meta title
	 * @param metaDescription the meta description
	 * @param metaKeyword the meta keyword
	 */
	public Category(Long id, String name, Long parentId, String description, String metaTitle, String metaDescription,
			String metaKeyword) {
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.description = description;
		this.metaTitle = metaTitle;
		this.metaDescription = metaDescription;
		this.metaKeyword = metaKeyword;
	}
	
	/**
	 * Instantiates a new category.
	 *
	 * @param name the name
	 * @param parentId the parent id
	 * @param description the description
	 * @param metaTitle the meta title
	 * @param metaDescription the meta description
	 * @param metaKeyword the meta keyword
	 */
	public Category(String name, Long parentId, String description, String metaTitle, String metaDescription,
			String metaKeyword) {
		this.name = name;
		this.parentId = parentId;
		this.description = description;
		this.metaTitle = metaTitle;
		this.metaDescription = metaDescription;
		this.metaKeyword = metaKeyword;
	}
	
}
