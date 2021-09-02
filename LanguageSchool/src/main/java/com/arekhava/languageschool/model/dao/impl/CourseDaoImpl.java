package com.arekhava.languageschool.model.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.arekhava.languageschool.entity.Course;
import com.arekhava.languageschool.model.dao.CourseDao;
import com.arekhava.languageschool.model.dao.DaoException;
import com.arekhava.languageschool.model.pool.ConnectionPool;
import com.arekhava.languageschool.model.pool.ConnectionPoolException;


/**
 * Works with database table courses
 * 
 * @author N
 * @see CourseDao
 */
public class CourseDaoImpl implements CourseDao{
	private static final String SQL_INSERT_COURSE = "INSERT INTO COURSES (ID, NAME, PRICE, IMAGE_NAME, LANGUAGE_ID,  NEXT_START ) VALUES (?,?,?,?,?)";
	private static final String SQL_UPDATE_COURSE = "UPDATE COURSES SET NAME=?, PRICE=?, IMAGE_NAME=?, NEXT_START=? WHERE ID=?";
	private static final String SQL_SELECT_COURSE_BY_LANGUAGE = "SELECT ID, NAME, PRICE, IMAGE_NAME, NEXT_START FROM COURSES WHERE LANGUAGE_ID=?";
	private static final String SQL_SELECT_COURSE_BY_NAME = "SELECT ID, NAME, PRICE, LANGUAGE_ID, IMAGE_NAME, NEXT_START WHERE NAME LIKE ?";
	private static final String SQL_SELECT_COURSE_BY_ID = "SELECT ID, LANGUAGE_ID, NAME, IMAGE_NAME, PRICE, NEXT_START FROM COURSES WHERE ID=?";
	private static final String ZERO_OR_MORE_CHARACTERS = "%";


	@Override
	public void create(Course course) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_INSERT_COURSE)) {
			statement.setLong(1, course.getLanguageId());
			statement.setString(2, course.getCourseName());
			statement.setString(3, course.getImageName());
			statement.setDate(4, course.getNextStart());
			statement.setBigDecimal(5, course.getPrice());
			statement.executeUpdate();
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException("database error", e);
		}
	}


	@Override
	public boolean update(Course course) throws DaoException {
		int numberUpdatedRows;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_COURSE)) {
			statement.setString(1, course.getCourseName());
			statement.setBigDecimal(2, course.getPrice());
			statement.setLong(3, course.getCourseId());
			numberUpdatedRows = statement.executeUpdate();
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException("database error", e);
		}
		return (numberUpdatedRows != 0);
	}


	@Override
	public Optional<Course> findCoursesById(String courseId) throws DaoException {
		Optional<Course> courseOptional;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_SELECT_COURSE_BY_ID)) {
			statement.setString(1, courseId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				Course course = DaoEntityBuilder.buildCourse(resultSet);
				courseOptional = Optional.of(course);
			} else {
				courseOptional = Optional.empty();
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException("database error", e);
		}
		return courseOptional;
	}

	@Override
	public List<Course> findCoursesByName(String courseName) throws DaoException {
		List<Course> courses = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_SELECT_COURSE_BY_NAME)) {
			statement.setString(1, ZERO_OR_MORE_CHARACTERS + courseName + ZERO_OR_MORE_CHARACTERS);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Course course = DaoEntityBuilder.buildCourse(resultSet);
				courses.add(course);
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException("database error", e);
		}
		return courses;
	}

	@Override
	public List<Course> findCoursesByLanguage(String language) throws DaoException {
		List<Course> courses = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_SELECT_COURSE_BY_LANGUAGE)) {
			statement.setString(1, language);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Course course = DaoEntityBuilder.buildCourse(resultSet);
				course.setLanguageId(Long.valueOf(language));
				courses.add(course);
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException("database error", e);
		}
		return courses;
	}
	
	@Override
	public List<Course> findAll() throws DaoException {
		throw new UnsupportedOperationException("operation not supported for class " + this.getClass().getName());
	}

}