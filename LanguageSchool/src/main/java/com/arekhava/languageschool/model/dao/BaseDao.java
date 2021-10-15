package com.arekhava.languageschool.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.arekhava.languageschool.entity.Course;
import com.arekhava.languageschool.model.pool.ConnectionPoolException;

/**
 * The root interface in the dao hierarchy
 * 
 *@author N
 * @param <T> entity
 */

public interface BaseDao<T> {

	/**
	 * Creates a new record in the corresponding database table
	 * 
	 * @param entity all data to create
	 * @throws DaoException if {@link ConnectionPoolException} or
	 *                      {@link SQLException} occur
	 */
	void create(T entity) throws DaoException;

	/**
	 * Updates record in the corresponding database table
	 * 
	 * @param entity all data to update
	 * @return boolean true true if the record has been updated, else false
	 * @throws DaoException if {@link ConnectionPoolException} or
	 *                      {@link SQLException} occur
	 */
	boolean update(T entity) throws DaoException;

	/**
	 * Find all records in the corresponding database table
	 * 
	 * @return {@link List} of entity
	 * @throws DaoException if {@link ConnectionPoolException} or
	 *                      {@link SQLException} occur
	 */
	List<T> findAll() throws DaoException;


}
