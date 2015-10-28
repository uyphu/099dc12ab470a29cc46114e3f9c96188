package com.ltu.yealtube.domain;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class User {

	@Id
	private Long id;
	
	@Index
	private Long userGroupId;

	@Index
	private String username;

	@Index
	private String password;

	private String salt;

	@Index
	private String firstname;

	@Index
	private String lastname;

	@Index
	private String email;

	private String image;

	private String code;
	
	private String ip;

	@Index
	private int status;

	private Date date_added;

	public final Long getId() {
		return this.id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public final Long getUserGroupId() {
		return this.userGroupId;
	}

	public final void setUserGroupId(Long userGroupId) {
		this.userGroupId = userGroupId;
	}

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

	public final String getSalt() {
		return this.salt;
	}

	public final void setSalt(String salt) {
		this.salt = salt;
	}

	public final String getFirstname() {
		return this.firstname;
	}

	public final void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public final String getLastname() {
		return this.lastname;
	}

	public final void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public final String getEmail() {
		return this.email;
	}

	public final void setEmail(String email) {
		this.email = email;
	}

	public final String getImage() {
		return this.image;
	}

	public final void setImage(String image) {
		this.image = image;
	}

	public final String getCode() {
		return this.code;
	}

	public final void setCode(String code) {
		this.code = code;
	}

	public final String getIp() {
		return this.ip;
	}

	public final void setIp(String ip) {
		this.ip = ip;
	}

	public final int getStatus() {
		return this.status;
	}

	public final void setStatus(int status) {
		this.status = status;
	}

	public final Date getDate_added() {
		return this.date_added;
	}

	public final void setDate_added(Date date_added) {
		this.date_added = date_added;
	}
	
	public User() {
		
	}

	public User(Long id, Long userGroupId, String username, String password, String salt, String firstname,
			String lastname, String email, String image, String code, String ip, int status, Date date_added) {
		this.id = id;
		this.userGroupId = userGroupId;
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.image = image;
		this.code = code;
		this.ip = ip;
		this.status = status;
		this.date_added = date_added;
	}

	public User(Long userGroupId, String username, String password, String salt, String firstname,
			String lastname, String email, String image, String code, String ip, int status, Date date_added) {
		this.userGroupId = userGroupId;
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.image = image;
		this.code = code;
		this.ip = ip;
		this.status = status;
		this.date_added = date_added;
	}
}
