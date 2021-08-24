package com.languageschool.model.service.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;

import com.languageschool.controller.command.ParameterAndAttribute;
import com.languageschool.util.MessageKey;
import com.languageschool.util.XssProtectionUtil;

/**
 * Validates course info
 * 
 * @author N
 */
public final class CourseInfoValidator {
	private static final String PRICE_PATTERN = "^[1-9]\\d{0,8}(\\.\\d{2})?$";
	private static final String IMAGE_NAME_PATTERN = "^.{1,40}\\.jpg$";
	private static final String NAME_PATTERN = "^.{1,45}$";
	

	private CourseInfoValidator() {
	}

	/**
	 * Looking for invalid course data
	 * 
	 * @param courseInfo {@link Map} of {@link String} and {@link String} the names
	 *                    of the {@link Course} fields and its values
	 * @return {@link List} of {@link String} error messages if course info is
	 *         invalid, else emptyList
	 */
	public static List<String> findInvalidData(Map<String, String> courseInfo) {
		List<String> errorMessageList = new ArrayList<>();
		if (MapUtils.isEmpty(courseInfo)) {
			errorMessageList.add(MessageKey.ERROR_IMPOSSIBLE_OPERATION_MESSAGE);
			return errorMessageList;
		}
		if (!isValidPrice(courseInfo.get(ParameterAndAttribute.PRICE))) {
			errorMessageList.add(MessageKey.ERROR_INCORRECT_PRICE_MESSAGE);
		}
		if (!isValidName(courseInfo.get(ParameterAndAttribute.LANGUAGE_NAME))) {
			errorMessageList.add(MessageKey.ERROR_INCORRECT_COURSE_NAME_MESSAGE);
		} else {
			courseInfo.put(ParameterAndAttribute.LANGUAGE_NAME,
					XssProtectionUtil.correctText(courseInfo.get(ParameterAndAttribute.LANGUAGE_NAME)));
		}
		return errorMessageList;
	}

	/**
	 * Checks if price is valid
	 * 
	 * @param price {@link String}
	 * @return boolean true if price is valid, else false
	 */
	public static boolean isValidPrice(String price) {
		return (price != null) ? price.matches(PRICE_PATTERN) : false;
	}
	/*
		*//**
			 * Checks if image name is valid
			 * 
			 * @param imageName {@link String}
			 * @return boolean true if image name is valid, else false
			 *//*
				 * public static boolean isValidImageName(String imageName) { return (imageName
				 * != null) ? imageName.matches(IMAGE_NAME_PATTERN) : false; }
				 */

	/**
	 * Checks if name is valid
	 * 
	 * @param name {@link String}
	 * @return boolean true if name is valid, else false
	 */
	public static boolean isValidName(String name) {
		return (name != null) ? name.matches(NAME_PATTERN) : false;
	}
	/**
	 * Checks if image name is valid
	 * 
	 * @param imageName {@link String}
	 * @return boolean true if image name is valid, else false
	 */
	public static boolean isValidImageName(String imageName) {
		return (imageName != null) ? imageName.matches(IMAGE_NAME_PATTERN) : false;
	}


	
}
