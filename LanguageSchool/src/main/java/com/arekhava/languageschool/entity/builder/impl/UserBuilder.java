package com.arekhava.languageschool.entity.builder.impl;

import static com.arekhava.languageschool.controller.command.ParameterAndAttribute.LOGIN;
import static com.arekhava.languageschool.controller.command.ParameterAndAttribute.PASSWORD;
import static com.arekhava.languageschool.controller.command.ParameterAndAttribute.PHONE;
import static com.arekhava.languageschool.controller.command.ParameterAndAttribute.USER_NAME;

import java.util.Map;

import com.arekhava.languageschool.entity.User;
import com.arekhava.languageschool.entity.builder.EntityBuilder;


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
