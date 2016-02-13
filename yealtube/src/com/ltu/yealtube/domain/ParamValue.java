package com.ltu.yealtube.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * The Class ParamValue.
 * @author uyphu
 */
@Entity
public class ParamValue {
	
	/** The id. */
	@Id
	private String id;
	
	/** The value. */
	private String value;

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
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * Instantiates a new keyword.
	 */
	public ParamValue() {
		
	}

	/**
	 * Instantiates a new keyword.
	 *
	 * @param id the id
	 * @param value the value
	 */
	public ParamValue(String id, String value) {
		this.id = id;
		this.value = value;
	}
	
}
