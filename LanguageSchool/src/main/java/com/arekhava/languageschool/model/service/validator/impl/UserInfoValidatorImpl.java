package com.arekhava.languageschool.model.service.validator.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;

import com.arekhava.languageschool.controller.command.ParameterAndAttribute;
import com.arekhava.languageschool.model.service.validator.UserInfoValidator;
import com.arekhava.languageschool.util.MessageKey;

public class UserInfoValidatorImpl implements UserInfoValidator{
	
	private static final String LOGIN_PATTERN = "^([.[^@\\s]]+)@([.[^@\\s]]+)\\.([a-z]+)$";
	private static final String PASSWORD_PATTERN = "^[a-zA-Z\\d]{5,15}$";
	private static final String NAME_PATTERN = "^[a-zA-Zа-яА-Я-\\s]{1,45}$";
	private static final String PHONE_PATTERN = "^\\+375\\d{9}$";

	@Override
	public List<String> findInvalidData(Map<String, String> userInfo) {
		List<String> errorMessageList = new ArrayList<>();
		if (MapUtils.isEmpty(userInfo)) {
			errorMessageList.add(MessageKey.ERROR_IMPOSSIBLE_OPERATION_MESSAGE);
			return errorMessageList;
		}
		if (!isValidName(userInfo.get(ParameterAndAttribute.USER_NAME))) {
			errorMessageList.add(MessageKey.ERROR_NAME_MESSAGE);
		}
		if (!isValidPhone(userInfo.get(ParameterAndAttribute.PHONE))) {
			errorMessageList.add(MessageKey.ERROR_PHONE_MESSAGE);
		}
		if (!isValidLogin(userInfo.get(ParameterAndAttribute.LOGIN))) {
			errorMessageList.add(MessageKey.ERROR_EMAIL_MESSAGE);
		}
		if (!isValidPassword(userInfo.get(ParameterAndAttribute.PASSWORD))) {
			errorMessageList.add(MessageKey.ERROR_PASSWORD_MESSAGE);
		}
		return errorMessageList;
	}

	@Override
	public boolean isValidLogin(String login) {
			return (login != null) ? login.matches(LOGIN_PATTERN) : false;
		}
	

	@Override
	public boolean isValidPassword(String password) {
		return (password != null) ? password.matches(PASSWORD_PATTERN) : false;
	}

	@Override
	public boolean isValidName(String name) {
		return (name != null) ? name.matches(NAME_PATTERN) : false;
	}

	@Override
	public boolean isValidPhone(String phone) {
		return (phone != null) ? phone.matches(PHONE_PATTERN) : false;
	}

}
