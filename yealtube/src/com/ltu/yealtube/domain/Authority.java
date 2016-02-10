package com.ltu.yealtube.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * The Class Authority.
 * @author uyphu
 */
@Entity
public class Authority {
	
	/** The id. */
	@Id
	private Long id;

	/** The name. */
	@Index
	private String name;
	
	/**
	 * Instantiates a new authority.
	 */
	public Authority() {
		
	}

	/**
	 * Instantiates a new authority.
	 *
	 * @param id the id
	 * @param name the name
	 */
	public Authority(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * Instantiates a new authority.
	 *
	 * @param name the name
	 */
	public Authority(String name) {
		this.name = name;
	}
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Authority other = (Authority) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		if (this.name == null) {
			if (other.name != null)
				return false;
		} else if (!this.name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Authority [id=" + this.id + ", name=" + this.name + "]";
	}
	
}
