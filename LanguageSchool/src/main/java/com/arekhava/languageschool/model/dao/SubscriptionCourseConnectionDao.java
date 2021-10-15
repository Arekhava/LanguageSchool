package com.arekhava.languageschool.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.arekhava.languageschool.entity.Course;
import com.arekhava.languageschool.entity.SubscriptionCourseConnection;
import com.arekhava.languageschool.model.pool.ConnectionPoolException;

/**
 * The interface for working with database table subscription_course_connection
 * 
 * @author N
 * @see BaseDao
 */

public interface SubscriptionCourseConnectionDao extends BaseDao<SubscriptionCourseConnection> {


	/**
	 * Deletes subscription course connection
	 * 
	 * @param subscriptionCourseConnection {@link SubscriptionCourseConnection} all data to
	 *                               delete
	 * @throws DaoException if {@link ConnectionPoolException} or
	 *                      {@link SQLException} occur
	 */
	void delete(SubscriptionCourseConnection subscriptionCourseConnection) throws DaoException;

	
	/**
	 * Looking for courses  by subscription id
	 * 
	 * @param subscriptionId {@link Long} subscription id
	 * @return {@link Map} of {@link Course} 
	 * @throws DaoException if {@link ConnectionPoolException} or
	 *                      {@link SQLException} occur
	 */
	List<Course> findBySubscriptionId(Long subscriptionId) throws DaoException;
}


