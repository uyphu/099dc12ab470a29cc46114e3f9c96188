package com.ltu.yealtube.domain;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * The Class User.
 */
@Entity
public class User {
	
	/** The id. */
	@Id
	private Long id;
	
	/** The user group id. */
	@Index
	private Long userGroupId;

	/** The login. */
	@Index
	private String login;

	/** The password. */
	@Index
	private String password;

	/** The salt. */
	private String salt;

	/** The first name. */
	@Index
	private String firstName;

	/** The last name. */
	@Index
	private String lastName;

	/** The email. */
	@Index
	private String email;

    /** The activated. */
    @Index
    private boolean activated = false;

    /** The lang key. */
    private String langKey;

    /** The activation key. */
    @Index
    private String activationKey;
    
    /** The token. */
    @Index
    private String token;
    
    /** The token date. */
    private Date tokenDate;

    /** The reset key. */
    private String resetKey;

    /** The reset date. */
    private Date resetDate = null;
    
    /** The create date. */
    private Date createDate;
    
	/** The image. */
	private String image;

	/** The code. */
	private String code;
	
	/** The ip. */
	private String ip;

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
	 * Gets the user group id.
	 *
	 * @return the user group id
	 */
	public final Long getUserGroupId() {
		return this.userGroupId;
	}

	/**
	 * Sets the user group id.
	 *
	 * @param userGroupId the new user group id
	 */
	public final void setUserGroupId(Long userGroupId) {
		this.userGroupId = userGroupId;
	}

	/**
	 * Gets the login.
	 *
	 * @return the login
	 */
	public final String getLogin() {
		return this.login;
	}

	/**
	 * Sets the login.
	 *
	 * @param login the new login
	 */
	public final void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public final String getPassword() {
		return this.password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public final void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the salt.
	 *
	 * @return the salt
	 */
	public final String getSalt() {
		return this.salt;
	}

	/**
	 * Sets the salt.
	 *
	 * @param salt the new salt
	 */
	public final void setSalt(String salt) {
		this.salt = salt;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public final String getFirstName() {
		return this.firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public final String getLastName() {
		return this.lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public final void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public final String getEmail() {
		return this.email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public final void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Checks if is activated.
	 *
	 * @return true, if is activated
	 */
	public final boolean isActivated() {
		return this.activated;
	}

	/**
	 * Sets the activated.
	 *
	 * @param activated the new activated
	 */
	public final void setActivated(boolean activated) {
		this.activated = activated;
	}

	/**
	 * Gets the lang key.
	 *
	 * @return the lang key
	 */
	public final String getLangKey() {
		return this.langKey;
	}

	/**
	 * Sets the lang key.
	 *
	 * @param langKey the new lang key
	 */
	public final void setLangKey(String langKey) {
		this.langKey = langKey;
	}

	/**
	 * Gets the activation key.
	 *
	 * @return the activation key
	 */
	public final String getActivationKey() {
		return this.activationKey;
	}

	/**
	 * Sets the activation key.
	 *
	 * @param activationKey the new activation key
	 */
	public final void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

	/**
	 * Gets the reset key.
	 *
	 * @return the reset key
	 */
	public final String getResetKey() {
		return this.resetKey;
	}

	/**
	 * Sets the reset key.
	 *
	 * @param resetKey the new reset key
	 */
	public final void setResetKey(String resetKey) {
		this.resetKey = resetKey;
	}

	/**
	 * Gets the reset date.
	 *
	 * @return the reset date
	 */
	public final Date getResetDate() {
		return this.resetDate;
	}

	/**
	 * Sets the reset date.
	 *
	 * @param resetDate the new reset date
	 */
	public final void setResetDate(Date resetDate) {
		this.resetDate = resetDate;
	}

	/**
	 * Gets the creates the date.
	 *
	 * @return the creates the date
	 */
	public final Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * Sets the creates the date.
	 *
	 * @param createDate the new creates the date
	 */
	public final void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	public final String getImage() {
		return this.image;
	}

	/**
	 * Sets the image.
	 *
	 * @param image the new image
	 */
	public final void setImage(String image) {
		this.image = image;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public final String getCode() {
		return this.code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public final void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	public final String getIp() {
		return this.ip;
	}

	/**
	 * Sets the ip.
	 *
	 * @param ip the new ip
	 */
	public final void setIp(String ip) {
		this.ip = ip;
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
	 * Gets the token date.
	 *
	 * @return the token date
	 */
	public final Date getTokenDate() {
		return this.tokenDate;
	}

	/**
	 * Sets the token date.
	 *
	 * @param tokenDate the new token date
	 */
	public final void setTokenDate(Date tokenDate) {
		this.tokenDate = tokenDate;
	}

	/**
	 * Instantiates a new user.
	 */
	public User() {
		
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param id the id
	 * @param userGroupId the user group id
	 * @param login the login
	 * @param password the password
	 * @param salt the salt
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param email the email
	 * @param activated the activated
	 * @param langKey the lang key
	 * @param activationKey the activation key
	 * @param resetKey the reset key
	 * @param resetDate the reset date
	 * @param createDate the create date
	 * @param image the image
	 * @param code the code
	 * @param ip the ip
	 */
	public User(Long id, Long userGroupId, String login, String password, String salt, String firstName,
			String lastName, String email, boolean activated, String langKey, String activationKey, String resetKey,
			Date resetDate, Date createDate, String image, String code, String ip) {
		this.id = id;
		this.userGroupId = userGroupId;
		this.login = login;
		this.password = password;
		this.salt = salt;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.activated = activated;
		this.langKey = langKey;
		this.activationKey = activationKey;
		this.resetKey = resetKey;
		this.resetDate = resetDate;
		this.createDate = createDate;
		this.image = image;
		this.code = code;
		this.ip = ip;
	}
	
	/**
	 * Instantiates a new user.
	 *
	 * @param userGroupId the user group id
	 * @param login the login
	 * @param password the password
	 * @param salt the salt
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param email the email
	 * @param activated the activated
	 * @param langKey the lang key
	 * @param activationKey the activation key
	 * @param resetKey the reset key
	 * @param resetDate the reset date
	 * @param createDate the create date
	 * @param image the image
	 * @param code the code
	 * @param ip the ip
	 */
	public User(Long userGroupId, String login, String password, String salt, String firstName,
			String lastName, String email, boolean activated, String langKey, String activationKey, String resetKey,
			Date resetDate, Date createDate, String image, String code, String ip) {
		this.userGroupId = userGroupId;
		this.login = login;
		this.password = password;
		this.salt = salt;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.activated = activated;
		this.langKey = langKey;
		this.activationKey = activationKey;
		this.resetKey = resetKey;
		this.resetDate = resetDate;
		this.createDate = createDate;
		this.image = image;
		this.code = code;
		this.ip = ip;
	}
	
	/**
	 * Instantiates a new user.
	 *
	 * @param login the login
	 * @param password the password
	 * @param email the email
	 */
	public User(String login, String password, String email) {
		this.login = login;
		this.password = password;
		this.email = email;
	}

}
