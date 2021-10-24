package com.arekhava.languageschool.model.service.validator;

import java.util.List;
import java.util.Map;

import com.arekhava.languageschool.entity.Course;

public interface CourseInfoValidator {
	
	/**
	 * Looking for invalid course data
	 * 
	 * @param courseInfo {@link Map} of {@link String} and {@link String} the names
	 *                    of the {@link Course} fields and its values
	 * @return {@link List} of {@link String} error messages if course info is
	 *         invalid, else emptyList
	 */
	
	List<String> findInvalidData(Map<String, String> courseInfo);
	
	/**
	 * Checks if price is valid
	 * 
	 * @param price {@link String}
	 * @return boolean true if price is valid, else false
	 */
	
	boolean isValidPrice(String price);
	
	/**
	 * Checks if image name is valid
	 * 
	 * @param imageName {@link String}
	 * @return boolean true if image name is valid, else false
	 */
	
	boolean isValidImageName(String imageName); 
	
	/**
	 * Checks if name is valid
	 * 
	 * @param name {@link String}
	 * @return boolean true if name is valid, else false
	 */
	boolean isValidName(String name);

}
