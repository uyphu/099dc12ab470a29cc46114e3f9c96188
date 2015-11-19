package com.ltu.yealtube.service;

import java.util.Calendar;

import javax.annotation.Nullable;
import javax.inject.Named;

import org.apache.log4j.Logger;

import com.google.api.server.spi.response.CollectionResponse;
import com.ltu.yealtube.dao.UserDao;
import com.ltu.yealtube.domain.User;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.exception.ErrorCode;
import com.ltu.yealtube.exception.ErrorCodeDetail;
import com.ltu.yealtube.utils.AppUtils;
import com.ltu.yealtube.utils.RandomUtil;

/**
 * The Class UserService.
 */
public class UserService {
	
	/** The log. */
	private final Logger log = Logger.getLogger(UserService.class);
	
	/** The user dao. */
	private UserDao userDao = new UserDao();
	
	/**
	 * List user.
	 *
	 * @param cursorString the cursor string
	 * @param count the count
	 * @return the collection response
	 */
	public CollectionResponse<User> listUser(String cursorString, Integer count) {
		return userDao.list(cursorString, count);
	}
	
	/**
	 * Activate registration.
	 *
	 * @param key the key
	 * @return the user
	 */
	public  User activateRegistration(String key) {
        log.debug("Activating user for activation key " + key);
        User user = userDao.findOneByActivationKey(key);
        // activate given user for the registration key.
        if (user != null) {
            user.setActivated(true);
            user.setActivationKey(null);
            userDao.persist(user);
            log.debug("Activated user: " + user);
        }
        return user;
    }
	
	/**
	 * Insert user.
	 *
	 * @param user the user
	 * @return the user
	 * @throws CommonException the common exception
	 */
	public User insertUser(User user) throws CommonException {
		// If if is not null, then check if it exists. If yes, throw an
		// Exception
		// that it is already present
		if (user.getId() != null) {
			if (user.getId() == 0) {
				user.setId(null);
			} else {
				if (findRecord(user.getId()) != null) {
					throw new CommonException(ErrorCode.CONFLICT_EXCEPTION,
							ErrorCodeDetail.ERROR_EXIST_OBJECT);
				}
			}
		}
		
		
		User objUser = userDao.findOneByLogin(user.getLogin());
        if (objUser != null) {
        	throw new CommonException(ErrorCode.CONFLICT_EXCEPTION,
					ErrorCodeDetail.ERROR_CONFLICT_LOGIN);
        } else {
            if (userDao.findOneByEmail(user.getEmail()) != null) {
//            	throw new CommonException(ErrorCode.CONFLICT_EXCEPTION,
//    					ErrorCodeDetail.ERROR_DUPLICATED_EMAIL);
            	//FIXME comment it for testing 
            }
            
            return createUserInformation(user);
        }
		
	}
	
	/**
	 * Update user.
	 *
	 * @param user the user
	 * @return the user
	 * @throws CommonException the common exception
	 */
	public User updateUser(User user) throws CommonException {
		User oldUser = findRecord(user.getId());
		if (oldUser == null) {
			throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION,
					ErrorCodeDetail.ERROR_RECORD_NOT_FOUND);
		}
		//User pos = null; //dao.getUserByName(user.getName());
		//FIXME Check this logic
		//if (pos == null || pos.getId().equals(user.getId())) {
			//oldUser.setName(user.getName());
//			if (user.getManager() != null) {
//				UserDao userDao = new UserDao();
//				User manager = userDao.getUserByLogin(user.getManager());
//				if (manager != null) {
//					user.setManager(manager.getLogin());
//				} else {
//					throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION,
//							ErrorCodeDetail.ERROR_USER_NOT_FOUND);
//				}
//			}
			userDao.update(user);
		//} else {
//			throw new CommonException(ErrorCode.CONFLICT_EXCEPTION,
//					ErrorCodeDetail.ERROR_EXIST_OBJECT);
		//}
		return user;
	}
	
	/**
	 * Removes the user.
	 *
	 * @param id the id
	 * @throws CommonException the common exception
	 */
	public void removeUser(Long id) throws CommonException {
		User record = findRecord(id);
		if (record == null) {
			throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION,
					ErrorCodeDetail.ERROR_RECORD_NOT_FOUND);
		}
		userDao.delete(record);
	}
	
	/**
	 * Creates the user information.
	 *
	 * @param user the user
	 * @return the user
	 */
	private User createUserInformation(User user) {

		//wUser user = new User();
		
		//FIXME add authorities.
//		Authority authority = authorityRepository.findOne("ROLE_USER");
//		Set<Authority> authorities = new HashSet<>();
		String encryptedPassword = AppUtils.cryptWithMD5(user.getPassword());
//		user.setLogin(login);
		// new user gets initially a generated password
		user.setPassword(encryptedPassword);
//		user.setFirstName(firstName);
//		user.setLastName(lastName);
//		user.setEmail(email);
//		user.setLangKey(langKey);
		// new user is not active
		user.setActivated(false);
		// new user gets registration key
		user.setActivationKey(RandomUtil.generateActivationKey());
		//FIXME handle ROLE_USER
//		authorities.add(authority);
//		user.setAuthorities(authorities);
//		userRepository.save(user);
		UserDao dao = new UserDao();
		dao.persist(user);
        //FIXME adding baseUrl to properties file
        String baseUrl = "https://yealtubetest.appspot.com/admin1";
        MailService mailService = new MailService();
        mailService.sendActivationEmail(user, baseUrl);
		log.debug("Created Information for User: " + user.toString());
		return user;
	}
	
	/**
	 * Find record.
	 *
	 * @param id the id
	 * @return the user
	 */
	public User findRecord(Long id) {
		UserDao dao = new UserDao();
		return dao.find(id);
	}
	
	/**
	 * Inits the data.
	 */
	public void initData() {
		userDao.initData();
	}
	
	/**
	 * Clean data.
	 */
	public void cleanData() {
		userDao.cleanData();
	}
	
	/**
	 * Search user.
	 *
	 * @param querySearch the query search
	 * @param cursorString the cursor string
	 * @param count the count
	 * @return the collection response
	 * @throws CommonException the common exception
	 */
	public CollectionResponse<User> searchUser(
			@Nullable @Named("querySearch") String querySearch,
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("count") Integer count) throws CommonException {
		return userDao.searchUser(querySearch, cursorString, count);
	}
	
	/**
	 * Activate account.
	 *
	 * @param key the key
	 * @return the user
	 * @throws CommonException the common exception
	 */
	public User activateAccount(String key) throws CommonException {
        User user = activateRegistration(key);
        if (user == null) {
        	throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR_EXCEPTION,
					ErrorCodeDetail.ERROR_RECORD_NOT_FOUND);
        }
        return user;
    }
	
	/**
	 * Login.
	 *
	 * @param login the login
	 * @param password the password
	 * @return the user
	 */
	public User login(String login, String password) throws CommonException {
		User user = userDao.login(login, password);
		if (user != null ) {
			if (user.isActivated()) {
				user.setToken(AppUtils.generateToken(user.getLogin()));
				user.setTokenDate(Calendar.getInstance().getTime());
				userDao.update(user);
				return user;
			} else {
				throw new CommonException(ErrorCode.FORBIDDEN_EXCEPTION,
						ErrorCodeDetail.ERROR_NOT_ACTIVATED); 
			}
		} else {
			throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION,
					ErrorCodeDetail.ERROR_USER_NOT_FOUND); 
		}
		
	}
	
	/**
	 * Gets the token.
	 *
	 * @param token the token
	 * @return the token
	 */
	public User getToken(String token) {
		return userDao.findOneByToken(token);
	}
	
	/**
	 * Test insert.
	 *
	 * @param key the key
	 * @return the user
	 * @throws CommonException the common exception
	 */
	public User testInsert(String key) throws CommonException {
		User user = new User("login" + key, "password", "uyphu@yahoo.com");
		return insertUser(user);
    }
	
	/**
	 * Test login.
	 *
	 * @param key the key
	 * @return the user
	 * @throws CommonException the common exception
	 */
	public User testLogin(String key) throws CommonException {
		UserDao dao = new UserDao();
		return dao.findOneByLogin(key);
    }
	
	public User findOneByActivationKey(String key) throws CommonException {
		UserDao dao = new UserDao();
		return dao.findOneByActivationKey(key);
    }

}
