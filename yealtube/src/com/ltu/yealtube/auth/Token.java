package com.ltu.yealtube.auth;

/**
 * The security token.
 */
public class Token {

    /** The token. */
    String token;
    
    /** The expires. */
    long expires;
    
    /** The type. */
    String type;
	
	/**
	 * Instantiates a new token.
	 *
	 * @param token the token
	 * @param expires the expires
	 * @param type the type
	 */
	public Token(String token, long expires, String type) {
		super();
		this.token = token;
		this.expires = expires;
		this.type = type;
	}
	
	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public final String getToken() {
		return this.token;
	}
	
	/**
	 * Sets the token.
	 *
	 * @param token the new token
	 */
	public final void setToken(String token) {
		this.token = token;
	}
	
	/**
	 * Gets the expires.
	 *
	 * @return the expires
	 */
	public final long getExpires() {
		return this.expires;
	}
	
	/**
	 * Sets the expires.
	 *
	 * @param expires the new expires
	 */
	public final void setExpires(long expires) {
		this.expires = expires;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public final String getType() {
		return this.type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public final void setType(String type) {
		this.type = type;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Token [token=" + this.token + ", expires=" + this.expires + ", type=" + this.type + "]";
	}
	
}