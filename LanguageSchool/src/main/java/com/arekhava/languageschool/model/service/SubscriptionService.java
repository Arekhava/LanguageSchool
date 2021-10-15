package com.arekhava.languageschool.model.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.arekhava.languageschool.entity.Subscription;
import com.arekhava.languageschool.entity.UserRole;
import com.arekhava.languageschool.model.dao.DaoException;


/**
 * The interface for operations with the subscriptions
 * 
 * @author N
 */
public interface SubscriptionService {

	/**
	 * Adds a course to the liked
	 * 
	 * @param userId        {@link Long} user id
	 * @param subscriptionLikedId {@link Long} subscription id with liked status
	 * @param courseId     {@link String} course id
	 * @return {@link Optional} of {@link Subscription} received from database
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	Optional<Subscription> addCourseToLiked(Long userId, Long courseLikedId, String courseId) throws ServiceException;


	/**
	 * Removes a course from subscription
	 * 
	 * @param subscriptionId   {@link Long} subscription id
	 * @param courseId {@link String} course id
	 * @return boolean true if the course has been removed, else false
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	boolean removeCourseFromSubscription(Long subscriptionId, String courseId) throws ServiceException;

	/**
	 * Makes a subscription
	 * 
	 * @param subscriptionInfo {@link Map} of {@link String} and {@link String} the names
	 *                  of the {@link Subscription} fields and its values
	 * @return boolean true if the subscription has been placed, else false
	 * @throws ServiceException     if {@link DaoException} occurs
	 * @throws InvalidDataException if subscription info is invalid
	 */
	boolean checkout(Map<String, String> subscriptionInfo) throws ServiceException, InvalidDataException;

	/**
	 * Cancels subscription
	 * 
	 * @param subscriptionId     {@link String} subscription id
	 * @param subscriptionStatus {@link String} current subscription status
	 * @param role        {@link UserRole} the role of the user who cancels the
	 *                    subscription
	 * @return boolean true if the subscription has been canceled, else false
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	boolean cancelSubscription(String subscriptionId, String subscriptionStatus, UserRole role) throws ServiceException;

	/**
	 * Processes a subscription
	 * 
	 * @param subscriptionId     {@link String} subscription id
	 * @param subscriptionStatus {@link String} current subscription status
	 * @return boolean true if the subscription has been processed, else false
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	boolean processSubscription(String subscriptionId, String subscriptionStatus) throws ServiceException;

	/**
	 * Gives an subscription with the status liked
	 * 
	 * @param userId        {@link Long} user id
	 * @param subscriptionLikedId {@link Long} subscription liked id
	 * @return {@link Optional} of {@link Subscription} received from database
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	Optional<Subscription> takeSubscriptionLiked(Long userId, Long SubscriptionLikedId) throws ServiceException;

	/**
	 * Gives a subscription by id
	 * 
	 * @param subscriptionId {@link String} subscription id
	 * @return {@link Optional} of {@link Subscription} received from database
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	Optional<Subscription> takeSubscriptionById(String subscriptionId) throws ServiceException;

	/**
	 * Gives subscriptions by user login
	 * 
	 * @param login {@link String} user login
	 * @return {@link List} of {@link Subscription} received from database if subscriptions are
	 *         found, else emptyList
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	List<Subscription> takeSubscriptionsByLogin(String login) throws ServiceException;

	/**
	 * Gives subscriptions by status
	 * 
	 * @param subscriptionStatus {@link String} subscription status
	 * @return {@link List} of {@link Subscription} received from database if subscriptions are
	 *         found, else emptyList
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	List<Subscription> takeSubscriptionsByStatus(String subscriptionStatus) throws ServiceException;
	

	/**
	 * Gives subscriptions by user login
	 * 
	 * @return {@link List} of {@link Subscription} received from database if subscriptions are
	 *         found, else emptyList
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	List<Subscription> findAllSubscriptions() throws ServiceException;
}

