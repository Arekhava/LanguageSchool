package com.languageschool.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.languageschool.entity.Course;
import com.languageschool.entity.Language;
import com.languageschool.entity.SubscriptionCourseConnection;
import com.languageschool.model.dao.DaoException;
import com.languageschool.model.dao.SubscriptionCourseConnectionDao;
import com.languageschool.model.pool.ConnectionPool;
import com.languageschool.model.pool.ConnectionPoolException;

/**
 * Works with database table course_suscriptions
 * 
 * @author N
 * @see SubscriptionCourseConnectionDao
 */
public class SubscriptionCourseConnectionDaoImpl implements SubscriptionCourseConnectionDao {
	private static final String SQL_INSERT_SUBSCRIPTION_COURSE_CONNECTION = "INSERT INTO SUBSCRIPTION_COURSE_CONNECTION "
			+ "(SUBSCRIPTION_ID, COURSE_ID ) VALUES (?, ?)";
	private static final String SQL_UPDATE_SUBSCRIPTION_COURSE_CONNECTION = "UPDATE SUBSCRIPTION_COURSE_CONNECTION "
			+ "SET SUBSCRIPTION_ID=? AND COURSE_ID=?";
	private static final String SQL_DELETE_SUBSCRIPTION_COURSE_CONNECTION = "DELETE FROM SUBSCRIPTION_COURSE_CONNECTION "
			+ "WHERE SUBSCRIPTION_ID=? AND COURSE_ID=?";
	

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
		// TODO Auto-generated method stub
		return null;
	}

}

