package com.arekhava.languageschool.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.arekhava.languageschool.entity.Course;
import com.arekhava.languageschool.model.pool.ConnectionPoolException;


/**
 * The interface for working with database table courses
 * 
 * @author N
 * @see BaseDao
 */
public interface CourseDao extends BaseDao<Course> {

	
	
	/**
	 * Looking for courses by language
	 * 
	 * @param language {@link String} language id
	 * @return {@link List} of {@link Course} received from database if course
	 *         are found, else emptyList
	 * @throws DaoException if {@link ConnectionPoolException} or
	 *                      {@link SQLException} occur
	 */
	List<Course> findCoursesByLanguage(String language) throws DaoException;

	/**
	 * Looking for course by id
	 * 
	 * @param courseId {@link String} course id
	 * @return {@link Optional} of {@link Course} received from database
	 * @throws DaoException if {@link ConnectionPoolException} or
	 *                      {@link SQLException} occur
	 */
	Optional<Course> findCoursesById(String courseId) throws DaoException;

	/**
	 * Looking for courses by name
	 * 
	 * @param courseName {@link String} course name
	 * @return {@link List} of {@link Course} received from database if courses
	 *         are found, else emptyList
	 * @throws DaoException if {@link ConnectionPoolException} or
	 *                      {@link SQLException} occur
	 */
	List<Course> findCoursesByName(String courseName) throws DaoException;
	


}