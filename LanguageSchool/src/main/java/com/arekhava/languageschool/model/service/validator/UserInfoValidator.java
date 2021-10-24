package com.arekhava.languageschool.model.service.validator;

import java.util.List;
import java.util.Map;

import com.arekhava.languageschool.entity.User;

public interface UserInfoValidator {

	/**
	 * Looking for invalid user data
	 * 
	 * @param userInfo {@link Map} of {@link String} and {@link String} the names of
	 *                 the {@link User} fields and its values
	 * @return {@link List} of {@link String} error messages if user info is
	 *         invalid, else emptyList
	 */
	
	List<String> findInvalidData(Map<String, String> userInfo) ;
	
	/**
	 * Checks if login is valid
	 * 
	 * @param login {@link String}
	 * @return boolean true if login is valid, else false
	 */
	
	 boolean isValidLogin(String login);
	 
	 /**
		 * Checks if password is valid
		 * 
		 * @param password {@link String}
		 * @return boolean true if password is valid, else false
		 */
	 
	boolean isValidPassword(String password);
	
	/**
	 * Checks if name is valid
	 * 
	 * @param name {@link String}
	 * @return boolean true if name is valid, else false
	 */
	
	boolean isValidName(String name);
	
	/**
	 * Checks if phone is valid
	 * 
	 * @param phone {@link String}
	 * @return boolean true if phone is valid, else false
	 */
	
	boolean isValidPhone(String phone);
	 
}
