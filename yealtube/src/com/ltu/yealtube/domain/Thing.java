package com.ltu.yealtube.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Thing {

	@Id
	private Long id;

	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Thing() {
		
	}
	
	public Thing(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Thing(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Thing [id=" + id + ", name=" + name + "]";
	}
	
}