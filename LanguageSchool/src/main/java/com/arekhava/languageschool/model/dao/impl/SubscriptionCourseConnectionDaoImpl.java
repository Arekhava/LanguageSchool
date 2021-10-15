package com.arekhava.languageschool.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.arekhava.languageschool.entity.Course;
import com.arekhava.languageschool.entity.SubscriptionCourseConnection;
import com.arekhava.languageschool.model.dao.DaoException;
import com.arekhava.languageschool.model.dao.SubscriptionCourseConnectionDao;
import com.arekhava.languageschool.model.pool.ConnectionPool;
import com.arekhava.languageschool.model.pool.ConnectionPoolException;



/**
 * Works with database table course_suscriptions
 * 
 * @author N
 * @see SubscriptionCourseConnectionDao
 */
public class SubscriptionCourseConnectionDaoImpl implements SubscriptionCourseConnectionDao {
	private static final String SQL_INSERT_SUBSCRIPTION_COURSE_CONNECTION = "INSERT INTO SUBSCRIPTION_COURSE_CONNECTION "
			+ "(SUBSCRIPTIONS_ID, COURSES_ID ) VALUES (?, ?)";
	private static final String SQL_UPDATE_SUBSCRIPTION_COURSE_CONNECTION = "UPDATE SUBSCRIPTION_COURSE_CONNECTION "
			+ "SET SUBSCRIPTIONS_ID=? AND COURSES_ID=?";
	private static final String SQL_DELETE_SUBSCRIPTION_COURSE_CONNECTION = "DELETE FROM SUBSCRIPTION_COURSE_CONNECTION "
			+ "WHERE SUBSCRIPTIONS_ID=? AND COURSES_ID=?";
	private static final String SQL_SELECT_SUBSCRIPTION_BY_ID = "SELECT COURSES.ID, COURSES.LANGUAGE_ID, COURSES.NAME, COURSES.PRICE, COURSES.NEXT_START "
			+ "FROM SUBSCRIPTION_COURSE_CONNECTION JOIN COURSES ON SUBSCRIPTION_COURSE_CONNECTION.COURSES_ID=COURSES.ID JOIN LANGUAGES ON COURSES.LANGUAGE_ID=LANGUAGES.ID WHERE SUBSCRIPTION_COURSE_CONNECTION.SUBSCRIPTIONS_ID=?";
	

	@Override
	public void create(SubscriptionCourseConnection subscriptionCourseConnection) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_INSERT_SUBSCRIPTION_COURSE_CONNECTION)) {
			statement.setLong(1, subscriptionCourseConnection.getSubscriptionId());
			statement.setLong(2, subscriptionCourseConnection.getCourseId());
			statement.executeUpdate();
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException("database error", e);
		}
	}

	@Override
	public boolean update(SubscriptionCourseConnection subscriptionCourseConnection) throws DaoException {
		int numberUpdatedRows;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_SUBSCRIPTION_COURSE_CONNECTION)) {
			statement.setLong(1, subscriptionCourseConnection.getSubscriptionId());
			statement.setLong(2, subscriptionCourseConnection.getCourseId());
			numberUpdatedRows = statement.executeUpdate();
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException("database error", e);
		}
		return numberUpdatedRows != 0;
	}

	

	@Override
	public void delete(SubscriptionCourseConnection subscriptionCourseConnection) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_DELETE_SUBSCRIPTION_COURSE_CONNECTION)) {
			statement.setLong(1, subscriptionCourseConnection.getSubscriptionId());
			statement.setLong(2, subscriptionCourseConnection.getCourseId());
			statement.executeUpdate();
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException("database error", e);
		}
	}


	@Override
	public List<SubscriptionCourseConnection> findAll() throws DaoException {
		throw new UnsupportedOperationException("operation not supported for class " + this.getClass().getName());
	}
	
	@Override
	public List<Course> findBySubscriptionId(Long subscriptionId) throws DaoException {
		List<Course> courses = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_SELECT_SUBSCRIPTION_BY_ID)) {
			statement.setLong(1, subscriptionId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Course course = DaoEntityBuilder.buildLikedCourse(resultSet);
				courses.add(course);
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException("database error", e);
		}
	return courses;
	}

	
	
}

