package com.ltu.yealtube.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Category {

	@Id
	private Long id;
	
	@Index
	private String name;
	
	@Index
	private Long parentId;
	
	@Index
	private String description;
	
	private String metaTitle;
	
	private String metaDescription;
	
	@Index
	private String metaKeyword;

	public final Long getId() {
		return this.id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public final String getName() {
		return this.name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final Long getParentId() {
		return this.parentId;
	}

	public final void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public final String getDescription() {
		return this.description;
	}

	public final void setDescription(String description) {
		this.description = description;
	}

	public final String getMetaTitle() {
		return this.metaTitle;
	}

	public final void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
	}

	public final String getMetaDescription() {
		return this.metaDescription;
	}

	public final void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	public final String getMetaKeyword() {
		return this.metaKeyword;
	}

	public final void setMetaKeyword(String metaKeyword) {
		this.metaKeyword = metaKeyword;
	}
	
	public Category() {
		
	}

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
