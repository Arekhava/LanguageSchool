package com.arekhava.languageschool.model.service.validator.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;

import com.arekhava.languageschool.controller.command.ParameterAndAttribute;
import com.arekhava.languageschool.model.service.validator.CourseInfoValidator;
import com.arekhava.languageschool.util.MessageKey;
import com.arekhava.languageschool.util.XssProtectionUtil;

public class CourseInfoValidatorImpl implements CourseInfoValidator {
	
	private static final String PRICE_PATTERN = "^[1-9]\\d{0,8}(\\.\\d{2})?$";
	private static final String IMAGE_NAME_PATTERN = "^.{1,40}\\.jpg";
	private static final String NAME_PATTERN = "^.{1,45}";

	@Override
	public List<String> findInvalidData(Map<String, String> courseInfo) {
		List<String> errorMessageList = new ArrayList<>();
		if (MapUtils.isEmpty(courseInfo)) {
			errorMessageList.add(MessageKey.ERROR_IMPOSSIBLE_OPERATION_MESSAGE);
			return errorMessageList;
		}
		if (!isValidPrice(courseInfo.get(ParameterAndAttribute.PRICE))) {
			errorMessageList.add(MessageKey.ERROR_INCORRECT_PRICE_MESSAGE);
		}
		if (!isValidName(courseInfo.get(ParameterAndAttribute.COURSE_NAME))) {
			errorMessageList.add(MessageKey.ERROR_INCORRECT_COURSE_NAME_MESSAGE);
		} else {
			courseInfo.put(ParameterAndAttribute.COURSE_NAME,
					XssProtectionUtil.correctText(courseInfo.get(ParameterAndAttribute.COURSE_NAME)));
		}
		return errorMessageList;
	}

	@Override
	public boolean isValidPrice(String price) {
		return (price != null) ? price.matches(PRICE_PATTERN) : false;
	}

	@Override
	public boolean isValidImageName(String imageName) {
		return (imageName != null) ? imageName.matches(IMAGE_NAME_PATTERN) : false;
	}

	@Override
	public boolean isValidName(String name) {
		return (name != null) ? name.matches(NAME_PATTERN) : false;
	}
	

}
