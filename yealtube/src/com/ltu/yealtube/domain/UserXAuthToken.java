package com.ltu.yealtube.domain;

import com.googlecode.objectify.annotation.Entity;

/**
 * @author Administrator
 *
 */
@Entity
public class UserXAuthToken {
	
	public final String getUsername() {
		return this.username;
	}

	public final void setUsername(String username) {
		this.username = username;
	}

	public final String getPassword() {
		return this.password;
	}

	public final void setPassword(String password) {
		this.password = password;
	}

	private String username;
	
	private String password;
	
	

}
