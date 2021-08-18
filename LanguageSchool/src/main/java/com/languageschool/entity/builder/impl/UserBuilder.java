package com.languageschool.entity.builder.impl;

import java.util.Map;

import com.languageschool.entity.User;
import com.languageschool.entity.builder.EntityBuilder;
import static com.languageschool.controller.command.ParameterAndAttribute.*;


/**
 * The builder is responsible for building user
 * 
 * @author N
 */
public class UserBuilder implements EntityBuilder<User> {
	private static final UserBuilder instance = new UserBuilder();
	
	private UserBuilder() {
	}

	/**
	 * Get instance of this class
	 * 
	 * @return {@link UserBuilder} instance
	 */
	public static UserBuilder getInstance() {
		return instance;
	}

	@Override
	public User build(Map<String, String> userInfo) {
		User user = new User();
		user.setLogin(userInfo.get(LOGIN));
		user.setPassword(userInfo.get(PASSWORD));
		user.setName(userInfo.get(USER_NAME));
		user.setPhone(userInfo.get(PHONE));
		return user;
	}
}
