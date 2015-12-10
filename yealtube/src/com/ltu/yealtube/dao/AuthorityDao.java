package com.ltu.yealtube.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.googlecode.objectify.cmd.Query;
import com.ltu.yealtube.constants.AuthoritiesConstants;
import com.ltu.yealtube.domain.Authority;

/**
 * The Class AuthorityDao.
 */
public class AuthorityDao extends AbstractDao<Authority>{

	/**
	 * Instantiates a new authority dao.
	 */
	public AuthorityDao() {
		super(Authority.class);
	}
	
	/**
	 * Gets the authority by name.
	 *
	 * @param name the name
	 * @return the authority by name
	 */
	public Authority getAuthorityByName(String name) {
		Map<String, Object> columns = new HashMap<String, Object>();
		columns.put("name", name);
		Query<Authority> query = getQuery(columns);
		List<Authority> list = executeQuery(query, 1);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} 
		return null;
	}

	@Override
	public void initData() {
		Authority authority;
		
		authority = new Authority(1L, AuthoritiesConstants.ROLE_ADMIN);
		persist(authority);
		authority = new Authority(2L, AuthoritiesConstants.ROLE_USER);
		persist(authority);
		authority = new Authority(3L, AuthoritiesConstants.ROLE_ANONYMOUS);
		persist(authority);
		authority = new Authority(4L, AuthoritiesConstants.ROLE_READONLY);
		persist(authority);
		authority = new Authority(5L, AuthoritiesConstants.ROLE_EDIT);
		persist(authority);
		authority = new Authority(6L, AuthoritiesConstants.ROLE_APPROVAL);
		persist(authority);
		authority = new Authority(7L, AuthoritiesConstants.ROLE_MANAGER);
		persist(authority);
		authority = new Authority(8L, AuthoritiesConstants.ROLE_LEADER);
		persist(authority);
	}

	@Override
	public void cleanData() {
		List<Authority> authorities = findAll();
		for (Authority authority : authorities) {
			delete(authority);
		}
	}

}
