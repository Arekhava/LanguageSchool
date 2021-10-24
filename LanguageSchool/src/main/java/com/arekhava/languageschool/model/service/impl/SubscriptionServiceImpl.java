package com.arekhava.languageschool.model.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections4.MapUtils;

import com.arekhava.languageschool.controller.command.ParameterAndAttribute;
import com.arekhava.languageschool.entity.Course;
import com.arekhava.languageschool.entity.Subscription;
import com.arekhava.languageschool.entity.SubscriptionCourseConnection;
import com.arekhava.languageschool.entity.SubscriptionStatus;
import com.arekhava.languageschool.entity.UserRole;
import com.arekhava.languageschool.entity.builder.impl.SubscriptionBuilder;
import com.arekhava.languageschool.model.dao.CourseDao;
import com.arekhava.languageschool.model.dao.DaoException;
import com.arekhava.languageschool.model.dao.SubscriptionCourseConnectionDao;
import com.arekhava.languageschool.model.dao.SubscriptionDao;
import com.arekhava.languageschool.model.dao.impl.CourseDaoImpl;
import com.arekhava.languageschool.model.dao.impl.SubscriptionCourseConnectionDaoImpl;
import com.arekhava.languageschool.model.dao.impl.SubscriptionDaoImpl;
import com.arekhava.languageschool.model.service.InvalidDataException;
import com.arekhava.languageschool.model.service.ServiceException;
import com.arekhava.languageschool.model.service.SubscriptionService;
import com.arekhava.languageschool.model.service.validator.IdValidator;
import com.arekhava.languageschool.model.service.validator.SubscriptionInfoValidator;
import com.arekhava.languageschool.model.service.validator.UserInfoValidator;
import com.arekhava.languageschool.model.service.validator.impl.IdValidatorImpl;
import com.arekhava.languageschool.model.service.validator.impl.SubscriptionInfoValidatorImpl;
import com.arekhava.languageschool.model.service.validator.impl.UserInfoValidatorImpl;
import com.arekhava.languageschool.util.PriceCalculator;






/**
 * The service is responsible for operations with the subscriptions
 * 
 * @author N
 * @see SubscriptionService
 */
public class SubscriptionServiceImpl implements SubscriptionService {
	private SubscriptionDao subscriptionDao = new SubscriptionDaoImpl();
	private SubscriptionCourseConnectionDao subscriptionCourseConnectionDao = new SubscriptionCourseConnectionDaoImpl();
	private CourseDao courseDao = new CourseDaoImpl();
	private IdValidator idValidator = new IdValidatorImpl();
	private SubscriptionInfoValidator subscriptionInfoValidator = new SubscriptionInfoValidatorImpl();
	private UserInfoValidator userInfoValidator = new UserInfoValidatorImpl();

	@Override
	public Optional<Subscription> addCourseToLiked(Long userId, Long courseLikedId, String courseId)
			throws ServiceException {
		if (userId == null || !idValidator.isValidId(courseId)) {
			return Optional.empty();
		}
		Subscription subscriptionLiked;
		try {
			if (courseLikedId == null) {
				courseLikedId = takeCourseToLikedId(userId);
			}
			SubscriptionCourseConnection subscriptionCourseConnection = new SubscriptionCourseConnection(courseLikedId,
					Long.valueOf(courseId));
			
			subscriptionCourseConnectionDao.create(subscriptionCourseConnection);
	
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
		if (subscriptionId == null || !idValidator.isValidId(courseId)) {
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
				|| !idValidator.isValidId(subscriptionInfo.get(ParameterAndAttribute.SUBSCRIPTION_LIKED_ID))) {
			return false;
		}
		List<String> errorMessageList = subscriptionInfoValidator.findInvalidData(subscriptionInfo);
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
		if (!idValidator.isValidId(subscriptionId) || !subscriptionInfoValidator.isValidSubscriptionStatus(subscriptionStatus)) {
			return false;
		}
		SubscriptionStatus subscriptionFrom = SubscriptionStatus.valueOf(subscriptionStatus.toUpperCase());
		SubscriptionStatus statusTo;
		switch (subscriptionFrom) {
		case ADDED_COURSE:
			statusTo = SubscriptionStatus.ACCEPTED_TO_COURSE;
			break;
		case ACCEPTED_TO_COURSE:
			statusTo = SubscriptionStatus.ACTIVE_COURSE;
			break;
		case ACTIVE_COURSE:
			statusTo = SubscriptionStatus.COMPLETED_COURSE;	
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
		if (!idValidator.isValidId(subscriptionId)) {
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
		if (!userInfoValidator.isValidLogin(login)) {
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
		if (!subscriptionInfoValidator.isValidSubscriptionStatus(subscriptionStatus)) {
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
						subscription.setCost(PriceCalculator.calculateTotalCost(courses));//fixme
					}
				}
			}
		} catch (DaoException e) {
			throw new ServiceException("subscriptions search error", e);
		}
		return subscriptions;
	}
	
	@Override
	public List<Subscription> findAllSubscriptions() throws ServiceException {
		List<Subscription> subscriptions;
		try {
			subscriptions = subscriptionDao.findAll();
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
	public boolean cancelSubscription(String subscriptionId, String subscriptionStatus, UserRole role) throws ServiceException {
	if (!idValidator.isValidId(subscriptionId) || !subscriptionInfoValidator.isValidSubscriptionStatus(subscriptionStatus)
			|| role == null) {
		return false;
	}
	SubscriptionStatus statusFrom = SubscriptionStatus.valueOf(subscriptionStatus.toUpperCase());
	if ((role == UserRole.STUDENT && statusFrom != SubscriptionStatus.ADDED_COURSE)
			|| (role == UserRole.ADMIN && statusFrom == SubscriptionStatus.NOT_ACCEPTED_TO_COURSE)) {
		return false;
	}
	boolean subscriptionCanceled;
	try {
		subscriptionCanceled = subscriptionDao.updateStatus(subscriptionId, statusFrom, SubscriptionStatus.NOT_ACCEPTED_TO_COURSE);
		if (subscriptionCanceled) {
			List<Course> courses = subscriptionCourseConnectionDao.findBySubscriptionId(Long.valueOf(subscriptionId));
			
		}
	} catch (DaoException e) {
		throw new ServiceException("subscriptions changing status error", e);
	}
	return subscriptionCanceled;
}
	
	}




