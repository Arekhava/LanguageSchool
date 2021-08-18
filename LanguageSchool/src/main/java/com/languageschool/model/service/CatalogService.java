package com.languageschool.model.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.languageschool.entity.Course;
import com.languageschool.entity.CourseList;
import com.languageschool.entity.Language;

/**
 * The interface for catalog operations
 * 
 * @author N
 */
public interface CatalogService {

	/**
	 * Adds a new course to the catalog
	 * 
	 * @param courseInfo {@link Map} of {@link String} and {@link String} the names
	 *                   of the {@link Course} fields and its values
	 * @throws ServiceException     if {@link DaoException} occurs
	 * @throws InvalidDataException if course info is invalid
	 */
	void addCourse(Map<String, String> courseInfo) throws ServiceException, InvalidDataException;

	/**
	 * Changes course data
	 * 
	 * @param courseInfo {@link Map} of {@link String} and {@link String} the names
	 *                   of the {@link Course} fields and its values
	 * @return boolean true if course data has been changed, else false
	 * @throws ServiceException     if {@link DaoException} occurs
	 * @throws InvalidDataException if course info is invalid
	 */
	boolean changeCourseData(Map<String, String> courseInfo) throws ServiceException, InvalidDataException;

	/**
	 * Gives all languages
	 * 
	 * @return {@link List} of {@link Language} received from database if languages
	 *         are found, else emptyList
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	List<Language> takeAllLanguages() throws ServiceException;

	/**
	 * Gives a course by id
	 * 
	 * @param courseId {@link String} course id
	 * @return {@link Optional} of {@link Course} received from database
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	Optional<Course> takeCourseById(String courseId) throws ServiceException;

	/**
	 * Gives courses from language
	 * 
	 * @param languageId    {@link String} language id
	 * @param sortingMethod {@link String} a way to sort courses, can be null if
	 *                      sorting is not needed
	 * @return {@link List} of {@link Course} received from database and sorted when
	 *         there is a sort method if courses are found, else emptyList
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	List<Course> takeCoursesByLanguage(String languageId, String sortingMethod) throws ServiceException;
	
	/**
		* Gives courses by the name
		* 
		* @param courseName    {@link String} course name
		* @param sortingMethod {@link String} a way to sort courses, can be null if
		*                      sorting is not needed
		* @return {@link List} of {@link Course} received from database and sorted when
		*         there is a sort method if courses are found, else emptyList
		* @throws ServiceException if {@link DaoException} occurs
	*/
	List<Course> takeCoursesWithName(String courseName, String sortingMethod) throws ServiceException;
				 
	

}