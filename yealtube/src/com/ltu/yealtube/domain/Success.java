package com.ltu.yealtube.domain;

/**
 * The Class Success.
 * @author uyphu
 */
public class Success {

	/** The code. */
	private int code;
	
	/** The message. */
	private String message;

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Instantiates a new success.
	 */
	public Success() {
		
	}

	/**
	 * Instantiates a new success.
	 *
	 * @param code the code
	 * @param message the message
	 */
	public Success(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
}
