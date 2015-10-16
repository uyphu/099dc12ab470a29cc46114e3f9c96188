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

	public UserGroup(Long id, String name, String permission) {
		this.id = id;
		this.name = name;
		this.permission = permission;
	}

	public UserGroup(String name, String permission) {
		this.name = name;
		this.permission = permission;
	}
}
