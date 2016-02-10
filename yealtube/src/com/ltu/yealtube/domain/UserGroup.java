package com.ltu.yealtube.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * The Class UserGroup.
 * @author uyphu
 */
@Entity
public class UserGroup {

	/** The id. */
	@Id
	private Long id;

	/** The name. */
	@Index
	private String name;

	/** The permission. */
	private String permission;
	
	/** The keyword. */
	private String keyword;

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
	 * Gets the permission.
	 *
	 * @return the permission
	 */
	public final String getPermission() {
		return this.permission;
	}

	/**
	 * Sets the permission.
	 *
	 * @param permission the new permission
	 */
	public final void setPermission(String permission) {
		this.permission = permission;
	}
	
	/**
	 * Gets the keyword.
	 *
	 * @return the keyword
	 */
	public final String getKeyword() {
		return this.keyword;
	}

	/**
	 * Sets the keyword.
	 *
	 * @param keyword the new keyword
	 */
	public final void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * Instantiates a new user group.
	 */
	public UserGroup() {
		
	}

	/**
	 * Instantiates a new user group.
	 *
	 * @param id the id
	 * @param name the name
	 * @param permission the permission
	 * @param keyword the keyword
	 */
	public UserGroup(Long id, String name, String permission, String keyword) {
		this.id = id;
		this.name = name;
		this.permission = permission;
		this.keyword = keyword;
	}

	/**
	 * Instantiates a new user group.
	 *
	 * @param name the name
	 * @param permission the permission
	 * @param keyword the keyword
	 */
	public UserGroup(String name, String permission, String keyword) {
		this.name = name;
		this.permission = permission;
		this.keyword = keyword;
	}
	
}
