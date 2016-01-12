package com.ltu.yealtube.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class UserGroup {

	@Id
	private Long id;

	@Index
	private String name;

	private String permission;
	
	private String keyword;

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

	public final String getPermission() {
		return this.permission;
	}

	public final void setPermission(String permission) {
		this.permission = permission;
	}
	
	public final String getKeyword() {
		return this.keyword;
	}

	public final void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public UserGroup() {
		
	}

	public UserGroup(Long id, String name, String permission, String keyword) {
		this.id = id;
		this.name = name;
		this.permission = permission;
		this.keyword = keyword;
	}

	public UserGroup(String name, String permission, String keyword) {
		this.name = name;
		this.permission = permission;
		this.keyword = keyword;
	}
}
