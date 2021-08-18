package com.languageschool.model.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections4.MapUtils;

import com.languageschool.controller.command.ParameterAndAttribute;
import com.languageschool.entity.Course;
import com.languageschool.entity.Subscription;
import com.languageschool.entity.SubscriptionCourseConnection;
import com.languageschool.entity.SubscriptionStatus;
import com.languageschool.entity.UserRole;
import com.languageschool.entity.builder.impl.SubscriptionBuilder;
import com.languageschool.model.dao.CourseDao;
import com.languageschool.model.dao.DaoException;
import com.languageschool.model.dao.SubscriptionCourseConnectionDao;
import com.languageschool.model.dao.SubscriptionDao;
import com.languageschool.model.dao.impl.CourseDaoImpl;
import com.languageschool.model.dao.impl.SubscriptionCourseConnectionDaoImpl;
import com.languageschool.model.dao.impl.SubscriptionDaoImpl;
import com.languageschool.model.service.InvalidDataException;
import com.languageschool.model.service.ServiceException;
import com.languageschool.model.service.SubscriptionService;
import com.languageschool.model.service.validator.IdValidator;
import com.languageschool.model.service.validator.SubscriptionInfoValidator;
import com.languageschool.model.service.validator.UserInfoValidator;
import com.languageschool.util.PriceCalculator;



/**
 * The service is responsible for operations with the subscriptions
 * 
 * @author N
 * @see SubscriptionService
 */
public class SubscriptionServiceImpl implements SubscriptionService {
	//private static final int ONE_COURSE = 1;
	private SubscriptionDao subscriptionDao = new SubscriptionDaoImpl();
	private SubscriptionCourseConnectionDao subscriptionCourseConnectionDao = new SubscriptionCourseConnectionDaoImpl();
	private CourseDao courseDao = new CourseDaoImpl();
	

	@Override
	public Optional<Subscription> addCourseToLiked(Long userId, Long courseLikedId, String courseId)
			throws ServiceException {
		if (userId == null || !IdValidator.isValidId(courseId)) {
			return Optional.empty();
		}
		Subscription subscriptionLiked;
		try {
			if (courseLikedId == null) {
				courseLikedId = takeCourseToLikedId(userId);
			}
			SubscriptionCourseConnection subscriptionCourseConnection = new SubscriptionCourseConnection(courseLikedId,
					Long.valueOf(courseId));
	
			subscriptionLiked = new Subscription(courseLikedId, userId);
		} catch (DaoException e) {
			throw new ServiceException("course adding error", e);
		}
		return Optional.of(subscriptionLiked);
	}
	
	/**
	 * Gives subscription id with liked status
	 * 
	 * @param userId {@link Long} user id
	 * @return {@link Long}
	 * @throws DaoException
	 */
	private Long takeCourseToLikedId(Long userId) throws DaoException {
		Long courseLikedId;
		Optional<Long> courseLikedIdOptional = subscriptionDao.findSubscriptionAddedId(userId);
		if (courseLikedIdOptional.isPresent()) {
			courseLikedId = courseLikedIdOptional.get();
		} else {
			Subscription subscription = new Subscription(userId);
			subscriptionDao.create(subscription);
			courseLikedId = subscription.getSubscriptionId();
		}
		return courseLikedId;
	}

	@Override
	public boolean removeCourseFromSubscription(Long subscriptionId, String courseId) throws ServiceException {
		if (subscriptionId == null || !IdValidator.isValidId(courseId)) {
			return false;
		}
		SubscriptionCourseConnection subscriptionCourseConnection = new SubscriptionCourseConnection(subscriptionId, Long.valueOf(courseId));
		try {
			subscriptionCourseConnectionDao.delete(subscriptionCourseConnection);
		} catch (DaoException e) {
			throw new ServiceException("error removing a course from the subscription", e);
		}
		return true;
	}

	@Override//TODO
	public boolean checkout(Map<String, String> subscriptionInfo) throws ServiceException, InvalidDataException {
		if (MapUtils.isEmpty(subscriptionInfo)
				|| !IdValidator.isValidId(subscriptionInfo.get(ParameterAndAttribute.SUBSCRIPTION_LIKED_ID))) {
			return false;
		}
		List<String> errorMessageList = SubscriptionInfoValidator.findInvalidData(subscriptionInfo);
		if (!errorMessageList.isEmpty()) {
			throw new InvalidDataException("invalid data", errorMessageList);
		}
		Subscription subscription = SubscriptionBuilder.getInstance().build(subscriptionInfo);
		boolean subscriptionPlaced;
		try {
			subscriptionPlaced = subscriptionDao.update(subscription);
			if (subscriptionPlaced) {
				List<Course> subscriptions = subscriptionCourseConnectionDao.findBySubscriptionId(subscription.getSubscriptionId());
				
			}
		} catch (DaoException e) {
			throw new ServiceException("subscription updating error", e);
		}
		return subscriptionPlaced;
	}



	@Override
	public boolean processSubscription(String subscriptionId, String subscriptionStatus) throws ServiceException {
		if (!IdValidator.isValidId(subscriptionId) || !SubscriptionInfoValidator.isValidSubscriptionStatus(subscriptionStatus)) {
			return false;
		}
		SubscriptionStatus subscriptionFrom = SubscriptionStatus.valueOf(subscriptionStatus.toUpperCase());
		SubscriptionStatus statusTo;
		switch (subscriptionFrom) {
		case COURSE_ADDED:
			statusTo = SubscriptionStatus.COURSE_ACCEPTED;
			break;
		case COURSE_ACCEPTED:
			statusTo = SubscriptionStatus.ACCEPTED_AND_PAID;
			break;
		case ACCEPTED_AND_PAID:
			statusTo = SubscriptionStatus.COURSE_COMPLITED;
			break;
		default:
			return false;
		}
		boolean subscriptionProcessed;
		try {
			subscriptionProcessed = subscriptionDao.updateStatus(subscriptionId, subscriptionFrom, statusTo);
		} catch (DaoException e) {
			throw new ServiceException("subscriptions changing status error", e);
		}
		return subscriptionProcessed;
	}

	@Override
	public Optional<Subscription> takeSubscriptionLiked(Long userId, Long SubscriptionLikedId) throws ServiceException {
		if (userId == null) {
			return Optional.empty();
		}
		Subscription subscriptionLiked;
		try {
			if (SubscriptionLikedId == null) {
				SubscriptionLikedId = takeCourseToLikedId(userId);
			}
			subscriptionLiked = new Subscription(SubscriptionLikedId, userId);
			List<Course> courses = subscriptionCourseConnectionDao.findBySubscriptionId(SubscriptionLikedId);
			subscriptionLiked.setCourses(courses);
			BigDecimal subscriptionCost = PriceCalculator.calculateTotalCost(courses);
			subscriptionLiked.setCost(subscriptionCost);
		} catch (DaoException e) {
			throw new ServiceException("course search error", e);
		}
		return Optional.of(subscriptionLiked);
	}

	@Override
	public Optional<Subscription> takeSubscriptionById(String subscriptionId) throws ServiceException {
		if (!IdValidator.isValidId(subscriptionId)) {
			return Optional.empty();
		}
		Optional<Subscription> subscriptionOptional;
		try {
			subscriptionOptional = subscriptionDao.findSubscriptionById(subscriptionId);
			if (subscriptionOptional.isPresent()) {
				Subscription subscription = subscriptionOptional.get();
				List<Course> courses = subscriptionCourseConnectionDao.findBySubscriptionId(Long.valueOf(subscriptionId));
				subscription.setCourses(courses);
				if (subscription.getSubscriptionStatus() == SubscriptionStatus.LIKED) {
					subscription.setCost(PriceCalculator.calculateTotalCost(courses));
				}
			}
		} catch (DaoException e) {
			throw new ServiceException("subscription search error", e);
		}
		return subscriptionOptional;
	}

	@Override
	public List<Subscription> takeSubscriptionsByLogin(String login) throws ServiceException {
		if (!UserInfoValidator.isValidLogin(login)) {
			return Collections.emptyList();
		}
		List<Subscription> subscriptions;
		try {
			subscriptions = subscriptionDao.findSubscriptionByLogin(login);
			if (!subscriptions.isEmpty()) {
				Collections.reverse(subscriptions);
				for (Subscription subscription : subscriptions) {
					List<Course> courses = subscriptionCourseConnectionDao.findBySubscriptionId(subscription.getSubscriptionId());
					subscription.setCourses(courses);
				}
			}
		} catch (DaoException e) {
			throw new ServiceException("subscriptions search error", e);
		}
		return subscriptions;
	}
	@Override
	public List<Subscription> takeSubscriptionsByStatus(String subscriptionStatus) throws ServiceException {
		if (!SubscriptionInfoValidator.isValidSubscriptionStatus(subscriptionStatus)) {
			return Collections.emptyList();
		}
		List<Subscription> subscriptions;
		try {
			subscriptions = subscriptionDao.findSubscriptionsByStatus(subscriptionStatus);
			if (!subscriptions.isEmpty()) {
				Collections.reverse(subscriptions);
				for (Subscription subscription : subscriptions) {
				List<Course> courses = subscriptionCourseConnectionDao.findBySubscriptionId(subscription.getSubscriptionId());
					subscription.setCourses(courses);
					if (SubscriptionStatus.valueOf(subscriptionStatus.toUpperCase()) == SubscriptionStatus.LIKED) {
						subscription.setCost(PriceCalculator.calculateTotalCost(courses));
					}
				}
			}
		} catch (DaoException e) {
			throw new ServiceException("subscriptions search error", e);
		}
		return subscriptions;
	}

	@Override
	public boolean cancelSubscription(String subscriptionId, String subscriptionStatus, UserRole role) throws ServiceException {
	if (!IdValidator.isValidId(subscriptionId) || !SubscriptionInfoValidator.isValidSubscriptionStatus(subscriptionStatus)
			|| role == null) {
		return false;
	}
	SubscriptionStatus statusFrom = SubscriptionStatus.valueOf(subscriptionStatus.toUpperCase());
	if ((role == UserRole.STUDENT && statusFrom != SubscriptionStatus.COURSE_ADDED)
			|| (role == UserRole.ADMIN && statusFrom == SubscriptionStatus.COURSE_COMPLITED)) {
		return false;
	}
	boolean subscriptionCanceled;
	try {
		subscriptionCanceled = subscriptionDao.updateStatus(subscriptionId, statusFrom, SubscriptionStatus.CANCELED);
		if (subscriptionCanceled) {
			List<Course> courses = subscriptionCourseConnectionDao.findBySubscriptionId(Long.valueOf(subscriptionId));
			
		}
	} catch (DaoException e) {
		throw new ServiceException("subscriptions changing status error", e);
	}
	return subscriptionCanceled;
}
	
	}


