package com.arekhava.languageschool.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.arekhava.languageschool.entity.Subscription;
import com.arekhava.languageschool.entity.SubscriptionStatus;
import com.arekhava.languageschool.model.pool.ConnectionPoolException;


/**
 * The interface for working with database table subscription
 * 
 * @author N
 * @see BaseDao
 */
public interface SubscriptionDao extends BaseDao<Subscription> {

	/**
	 * Updates subscription status
	 * 
	 * @param subscriptionId    {@link String} subscription id
	 * @param statusFrom {@link SubscriptionStatus} current subscription status
	 * @param statusTo   {@link SubscriptionStatus} new subscription status
	 * @return boolean true if the status has been updated, else false
	 * @throws DaoException if {@link ConnectionPoolException} or
	 *                      {@link SQLException} occur
	 */
	boolean updateStatus(String subscriptionId, SubscriptionStatus statusFrom, SubscriptionStatus statusTo) throws DaoException;

	/**
	 * Looking for an subscription where the status is added
	 * 
	 * @param userId {@link Long} user id
	 * @return {@link Optional} of {@link Long} subscription added id received from
	 *         database
	 * @throws DaoException if {@link ConnectionPoolException} or
	 *                      {@link SQLException} occur
	 */
	Optional<Long> findSubscriptionAddedId(Long userId) throws DaoException;

	/**
	 * Looking for an subscription by id
	 * 
	 * @param subscriptionId {@link String} subscription id
	 * @return {@link Optional} of {@link Subscription} received from database
	 * @throws DaoException if {@link ConnectionPoolException} or
	 *                      {@link SQLException} occur
	 */
	Optional<Subscription> findSubscriptionById(String subscriptionId) throws DaoException;

	/**
	 * Looking for subscriptions by user login
	 * 
	 * @param login {@link String} user login
	 * @return {@link List} of {@link Subscription} received from database if subscriptions are
	 *         found, else emptyList
	 * @throws DaoException if {@link ConnectionPoolException} or
	 *                      {@link SQLException} occur
	 */
	List<Subscription> findSubscriptionByLogin(String login) throws DaoException;

	/**
	 * Looking for subscriptions by status
	 * 
	 * @param subscriptionStatus {@link String} subscription status
	 * @return {@link List} of {@link Subscription} received from database if subscriptions are
	 *         found, else emptyList
	 * @throws DaoException if {@link ConnectionPoolException} or
	 *                      {@link SQLException} occur
	 */
	List<Subscription> findSubscriptionsByStatus(String subscriptionStatus) throws DaoException;
}